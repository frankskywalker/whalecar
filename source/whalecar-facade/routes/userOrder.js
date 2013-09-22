/**
 * User: ruihuang
 * Date: 13-8-22
 * Time: 上午2:20
 */

var service = require("./tools/service-header");
var async = require("async");

exports.userorder = function(req,res,next){
    var type =  req.query.type;
    var stockId = req.query.id;
    //type==init:订单初始确认页面
    if(type == "init"){
        service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,sData){
           res.render("user_order",{shopStockView:sData});
        });
    }
    //type==create:订单保存，并进入订单review页面
    else if (type == "create"){
        async.waterfall([
            //1.先根据stockId查询shopStockView
            function(callback){
                service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,shopStockView){
                    callback(sError,shopStockView);
                });
            },
            //2.根据shopStockView来组装userOrder
            function(shopStockView,callback){
                var userOrder = {
                    orderTitle : shopStockView.carBrandCname + " " + shopStockView.carModelLv1Cname  + " "  + shopStockView.carModelLv3FullName,
                    orderPrice : shopStockView.carPrice,
                    shopStock : shopStockView.id,
                    shop : shopStockView.shop,
                    user : req.session.currentUser.id
                };
                service.client.post("/createUserOrder",userOrder,function(sError,sReq,sRes,sData){
                    callback(sError,{shopStockView:shopStockView,userOrder:sData.resultMap.userOrder});
                });
            }
        ],function(err, result){
            if(err){
                next(err);
            }
            res.render("user_order_finish",{orderSn:result.userOrder.orderSn});
        });
    }
}

exports.changeProcessState = function(req,res,next){
    var id = req.body.id;
    var state = req.body.state;
    service.client.post("/changeUserOrderProcessState",{id:id,state:state},function(sError){
        if(sError){
            console.error(sError);
            res.json({updateSucc:false});
        }
        else{
            res.json({updateSucc:true});
        }
    });
}
