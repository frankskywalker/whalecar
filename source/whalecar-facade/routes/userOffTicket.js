/**
 * User: ruihuang
 * Date: 13-8-22
 * Time: 上午2:20
 */

var service = require("./tools/service-header");
var async = require("async");

exports.offticket = function(req,res,next){
    var type =  req.query.type;
    var stockId = req.body.id;

    async.waterfall([
        //1.先根据stockId查询shopStockView
        function(callback){
            service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,shopStockView){
                console.log(shopStockView);
                callback(sError,shopStockView);
            });
        },
        //2.根据shopStockView来组装userOrder
        function(shopStockView,callback){
            var offticket = {
                shopStock : shopStockView.id,
                shop : shopStockView.shop,
                user : req.session.currentUser.id
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
