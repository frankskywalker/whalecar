/**
 * User: ruihuang
 * Date: 13-8-22
 * Time: 上午2:13
 */
var async = require("async");
var service = require("./tools/service-header");

exports.action = function(req,res,next){
    var type = req.query.type;
    if (type == "saveShopStock") {
        saveShopStock(req, res, next);
    }
};

exports.stockeditor = function(req, res, next) {
    var id = req.query.id;
    async.parallel({
            carModelLv1: function(callback) {
                service.client.post("/getCarModelLv1ByBrand", {
                    carBrand: req.session.currentShop.carBrand,
                    carSubBrand: req.session.currentShop.carSubBrand
                }, function(err, req, res, data) {
                    callback(err, data);
                });
            },
            shopStockView: function(callback){
                if(!id){
                    callback(null, {});
                }
                else{
                    service.client.post("/getShopStockViewById", {
                        id: id
                    }, function(err, req, res, data) {
                        callback(err, data);
                    });

                }
            }
        },
        function(err, results) {
            if (err) {
                next(err);
            } else {
                res.render("shop_stock_editor", {
                    carModelLv1: results.carModelLv1
                    ,shopStockView : results.shopStockView
                });
            }
        }
    );
};

function saveShopStock(req, res, next) {
    var shopStock = req.body;
    shopStock.shop = req.session.currentShop.id;
    service.client.post("/saveOrUpdateShopStock", shopStock,
        function(error,request, response, data) {
            if (error) {
                next(error);
            } else {
                res.send({
                    saveSuc: data
                });
            }
    });
}