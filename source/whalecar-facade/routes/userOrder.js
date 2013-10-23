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
    if(type == "init" || type == "init-userprice"){
        service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,sData){
           if(type == "init-userprice"){
               var userSubmitPriceId = req.query.userSubmitPriceId;
               var userId = req.session.currentUser.id;
               //通过当前用户和之前的userpriceId，查询出议价的最终价格，如果结果为空，说明议价已经提价了订单或价格过期
               service.client.get("/getUserSubmitPriceByUserAndId?id=" + userSubmitPriceId + "&userId=" + userId,function(sError2,sReq2,sRes2,sData2){
                   if(!!sData2){
                       //finalPrice为空说明状态不对
                       if(!!sData2.finalPrice && sData2.finalPrice != ''){
                           sData.carPrice = sData2.finalPrice;
                       }
                   }
                   res.render("user_order",{shopStockView:sData,userPrice:true,userSubmitPriceId:userSubmitPriceId});
               });
           }
           else{
               res.render("user_order",{shopStockView:sData,userPrice:false,userSubmitPriceId:""});
           }
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
                    user : req.session.currentUser.id,
                    orderType : req.query.orderType
                };
                service.client.post("/createUserOrder?userPrice=" + req.query.userPrice + "&userSubmitPriceId=" + req.query.userSubmitPriceId,userOrder,function(sError,sReq,sRes,sData){
                    callback(sError,{shopStockView:shopStockView,userOrder:sData.resultMap.userOrder});
                });
            }
        ],function(err, result){
            if(err){
                next(err);
            }
            res.render("user_order_finish",{orderSn:result.userOrder.orderSn,orderType:result.userOrder.orderType});
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
