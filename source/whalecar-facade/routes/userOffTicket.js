/**
 * User: ruihuang
 * Date: 13-8-22
 * Time: 上午2:20
 */

var service = require("./tools/service-header");
var async = require("async");

exports.offticketConfirm = function(req,res,next){
    res.render("user_off_ticket_confirm",{id:req.query.id});
}

exports.offticket = function(req,res,next){

    var id = req.body.id;
    var phoneNum = req.body.phoneNum;

    async.waterfall([
        //1.先根据stockId查询shopStockView
        function(callback){
            service.client.post("/getShopStockViewById",{id:id},function(sError,sReq,sRes,shopStockView){
                console.log(shopStockView);
                callback(sError,shopStockView);
            });
        },
        //2.根据shopStockView来组装userOrder
        function(shopStockView,callback){
            var offticket = {
                shopStock : shopStockView.id,
                shop : shopStockView.shop,
                user : req.session.currentUser.id,
                phoneNum : phoneNum
            };
            service.client.post("/createUserOffTicket",offticket,function(sError,sReq,sRes,sData){
                callback(sError,{shopStockView:shopStockView,offticket:sData});
            });
        }
    ],function(err, results){
        if(err){
            next(err);
        }
        res.render("user_off_ticket",{offticket:results.offticket});
    });
}

exports.changeUserOffTicketState = function(req,res,next){
    var id = req.body.id;
    var state = req.body.state;
    var billSn = req.body.billSn;

    //验证id和state参数合法
    if(!id || !state){
        res.json({changeResult:false});
        return;
    }

    var currentUserId = !req.session.currentUser? null : req.session.currentUser.id;
    var currentShopId = !req.session.currentShop? null : req.session.currentShop.id;

    //根据id查询原数据
    service.client.get("/getUserOffTicketById?id=" + id ,function(sError,sReq,sRes,userOffTicket){

        if(!!sError || !userOffTicket){
            if(!!sError){
                console.log(sError);
            }
            res.json({changeResult:false});
            return;
        }

        var params = {};

        //多种情况判断:
        //1.上传发票号后变为等待确认
        if(state == "waiting_confirm"){
            //0.验证前置状态
            if(userOffTicket.state != "has_send"){
                console.warn("changeUserOffTicketState前置状态错误(id:%s,currentState:%s,newState:%s)",id,userOffTicket.state,"waiting_confirm");
                res.json({changeResult:false});
                return;
            }

            //1.验证当前用户是否为可操作的userId
            if(currentUserId == null || currentUserId != userOffTicket.user){
                console.warn("changeUserOffTicketState权限异常(id:%s,newState:%s,userId:%s)",id,"waiting_confirm",currentUserId);
                res.json({changeResult:false});
                return;
            }
            //2.验证单据号是否合法
            if(!billSn || billSn == ""){
                console.warn("changeUserSubmitPriceState参数异常:shopPrice缺失(id:%s)",id);
                res.json({changeResult:false});
                return;
            }
            params = {id : id,state:state,billSn:billSn};
        }
        else if(state == "finish"){
            //0.验证前置状态
            if(userOffTicket.state != "waiting_confirm"){
                console.warn("changeUserOffTicketState前置状态错误(id:%s,currentState:%s,newState:%s)",id,userOffTicket.state,"finish");
                res.json({changeResult:false});
                return;
            }
            //1.验证当前用户是否为可操作的shopid
            if(currentShopId == null || userOffTicket.shop != currentShopId){
                console.warn("changeUserOffTicketState权限异常(id:%s,newState:%s,shopId:%s)",id,"finish",currentShopId);
                res.json({changeResult:false});
                return;
            }
            params = {id : id,state:state};
        }
        else {
            console.error("changeUserSubmitPriceState入参异常(id:%s,state:%s,userId:%s,shopId:%s)",id,state,currentUserId,currentShopId);
            res.json({changeResult:false});
            return ;
        }

        service.client.post("/changeUserOffTicketState",params,function(sError){
            if(sError){
                console.error(sError);
                res.json({changeResult:false});
            }
            else{
                res.json({changeResult:true});
            }
        });
    });

}
