/**
 * User: ruihuang
 * Date: 13-8-22
 * Time: 上午2:20
 */

var service = require("./tools/service-header");
var async = require("async");

exports.submitprice = function(req,res,next){
    var type =  req.query.type;
    //type==init:订单初始确认页面
    if(type == "init"){
        var stockId = req.query.id;
        service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,sData){
           console.log(sData);
           res.render("user_submit_price",{shopStockView:sData});
        });
    }
    //type==create:订单保存，并进入订单review页面
    else if (type == "create"){
        var stockId = req.body.id;
        async.waterfall([
            //1.先根据stockId查询shopStockView
            function(callback){
                service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,shopStockView){
                    callback(sError,shopStockView);
                });
            },
            //2.根据shopStockView来组装userSubmitPrice
            function(shopStockView,callback){
                var userPrice = req.body.userPrice;
                var userSubmitPrice = {
                    userPrice : userPrice,
                    originalPrice:shopStockView.carPrice,
                    shopStock : shopStockView.id,
                    shop : shopStockView.shop,
                    user : req.session.currentUser.id
                };
                service.client.post("/saveUserSubmitPrice",userSubmitPrice,function(sError,sReq,sRes,sData){
                    callback(sError,{});
                });
            }
        ],function(err, result){
            if(err){
                next(err);
            }
            res.render("user_submit_price_finish");
        });
    }
}

exports.changeUserSubmitPriceState =  function(req,res,next){
    var id = req.body.id;
    var state = req.body.state;
    var shopPrice = req.body.shopPrice;

    //验证id和state参数合法
    if(!id || !state){
        res.json({changeResult:false});
        return;
    }

    var currentUserId = !req.session.currentUser? null : req.session.currentUser.id;
    var currentShopId = !req.session.currentShop? null : req.session.currentShop.id;

    //根据id查出UserSubmitPrice对象
    service.client.get("/getUserSubmitPriceById?id=" + id ,function(err,request, response,userSubmitPrice){
        if(!!err || !userSubmitPrice){
            if(!!err){
                console.log(err);
            }
            res.json({changeResult:false});
            return;
        }

        var params = {};


        //多种情况判断:
        //1. 4s店提出议价
        if(state == "shop_commit"){
            //0.验证前置状态
            if(userSubmitPrice.state != "user_commit"){
                console.warn("changeUserSubmitPriceState前置状态错误(id:%s,currentState:%s,newState:%s)",id,userSubmitPrice.state,"user_commit");
                res.json({changeResult:false});
                return;
            }

            //1.验证当前用户是否为可操作的shopid
            if(currentShopId == null || userSubmitPrice.shop != currentShopId){
                console.warn("changeUserSubmitPriceState权限异常(id:%s,newState:%s,shopId:%s)",id,"shop_commit",currentShopId);
                res.json({changeResult:false});
                return;
            }
            //2.验证价格是否存在
            if(!shopPrice || shopPrice == ""){
                console.warn("changeUserSubmitPriceState参数异常:shopPrice缺失(id:%s)",id);
                res.json({changeResult:false});
                return;
            }
            params = {id : id,state:state,shopPrice:shopPrice };
        }
        //2. 4s店确认价格，生成订单
        else if(state == "shop_agree"){
            //0.验证前置状态
            if(userSubmitPrice.state != "user_commit"){
                console.warn("changeUserSubmitPriceState前置状态错误(id:%s,currentState:%s,newState:%s)",id,userSubmitPrice.state,"shop_agree");
                res.json({changeResult:false});
                return;
            }
            //1.验证当前用户是否为可操作的shopid
            if(currentShopId == null || userSubmitPrice.shop != currentShopId){
                console.warn("changeUserSubmitPriceState权限异常(id:%s,newState:%s,shopId:%s)",id,"shop_commit",currentShopId);
                res.json({changeResult:false});
                return;
            }
            params = {id : id,state:state};
        }
        //3. 用户同意4s店议价，生成订单
        else if(state == "price_success"){
            //0.验证前置状态
            if(userSubmitPrice.state != "shop_commit"){
                console.warn("changeUserSubmitPriceState前置状态错误(id:%s,currentState:%s,newState:%s)",id,userSubmitPrice.state,"price_success");
                res.json({changeResult:false});
                return;
            }
            //1.验证当前用户是否为可操作的userId
            if(currentUserId == null || userSubmitPrice.user != currentUserId){
                console.warn("changeUserSubmitPriceState权限异常(id:%s,newState:%s,userId:%s)",id,"shop_commit",currentUserId);
                res.json({changeResult:false});
                return;
            }
            //2.保存价格，并跳转到价格页面
            params = {id : id,state:state};
        }
        //4. 用户不同意4s店议价，结束订单
        else if(state == "price_fail"){
            //0.验证前置状态
            if(userSubmitPrice.state != "shop_commit"){
                console.warn("changeUserSubmitPriceState前置状态错误(id:%s,currentState:%s,newState:%s)",id,userSubmitPrice.state,"price_fail");
                res.json({changeResult:false});
                return;
            }
            //1.验证当前用户是否为可操作的userId
            if(currentUserId == null || userSubmitPrice.user != currentUserId){
                console.warn("changeUserSubmitPriceState权限异常(id:%s,newState:%s,userId:%s)",id,"shop_commit",currentUserId);
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

        service.client.post("/changeUserSubmitPriceState",params,function(sError){
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
