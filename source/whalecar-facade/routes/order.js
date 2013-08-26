/**
 * User: ruihuang
 * Date: 13-8-22
 * Time: 上午2:20
 */

var service = require("./tools/service-header");
var async = require("async");

exports.carorder = function(req,res,next){
    var type =  req.query.type;
    var stockId = req.query.id;
    if(type == "init"){
        service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,sData){
           res.render("car_order",{shopStockView:sData});
        });
    }
    else if (type == "create"){
        async.waterfall([
            function(callback){
                service.client.post("/getShopStockViewById",{id:stockId},function(sError1,sReq1,sRes1,sData1){

                });
            },
            function(callback){
                service.client.post("/createUserOrder",{id:stockId},function(sError2,sReq2,sRes2,sData2){
                    res.render("car_order",{shopStockView:sData});
                });
            }
        ]);
    }
}

exports.offprice = function(req,res,next){

}

exports.submitprice = function(req,res,next){

}