/**
 * User: ruihuang
 * Date: 13-8-22
 * Time: 上午2:20
 */

var service = require("./tools/service-header")

exports.carorder = function(req,res,next){
    var type =  req.query.type;
    var stockId = req.query.id;
    if(type == "init"){
        service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,sData){
           console.log(sData);
           res.render("car_order",{shopStockView:sData});
        });
    }else if (type == "create"){

    }

}

exports.offprice = function(req,res,next){

}

exports.submitprice = function(req,res,next){

}