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
           res.render("user_submit_price",{shopStockView:sData});
        });
    }
    //type==create:订单保存，并进入订单review页面
    else if (type == "create"){
        var stockId = req.body.id;
        console.log(stockId);
        async.waterfall([
            //1.先根据stockId查询shopStockView
            function(callback){
                service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,shopStockView){
                    console.log(shopStockView);
                    callback(sError,shopStockView);
                });
            },
            //2.根据shopStockView来组装userSubmitPrice
            function(shopStockView,callback){
                var submitPrice = req.body.submitPrice;
                var userSubmitPrice = {
                    submitPrice : submitPrice,
                    originalPrice:shopStockView.carPrice,
                    shopStock : shopStockView.id,
                    shop : shopStockView.shop,
                    user : req.session.currentUser.id
                };
                console.log(userSubmitPrice);
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