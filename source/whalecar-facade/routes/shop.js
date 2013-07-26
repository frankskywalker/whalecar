/**
 * Shop page
 */

var service = require("./tools/service-header");
var async = require("async");

exports.action = function(req, res, next) {
    var type = req.query.type;
    console.info("type:" + type);
    if (type == "login") {
        login(req, res, next);
    } else if (type == "saveshop") {
        saveshop(req, res, next);
    }
};

exports.stockeditor = function(req, res, next) {
    async.parallel({
        carModelLv1: function(callback) {
            service.client.post("/getCarModelLv1ByBrand", {
                carBrand: req.session.currentShop.carBrand,
                carSubBrand: req.session.currentShop.carSubBrand
            }, function(err, req, res, data) {
                callback(err, data);
            });
        }
    },
    function(err, results) {
        if (err) {
            next(err);
        } else {
            res.render("shop_stock_editor", {
                carModelLv1: results.carModelLv1
            });
        }
    });

};

exports.homepage = function(req, res, next) {
    //判断是否是4S店用户
    if (!req.session.currentShop) {
        //4s店用户未登陆
        res.send("请使用4S店用户登陆后重新访问页面！");
        return;
    }

    // call service
    async.parallel({
        shopStockView: function(callback) {
            service.client.post("/getShopStockViewByShop", {
                id: req.session.currentShop.id
            }, function(err, req, res, data) {
                callback(err, data);
            });
        },
        shop: function(callback) {
            service.client.post("/getShopById", {
                id: req.session.currentShop.id
            }, function(err, req, res, data) {
                callback(err, data);
            });
        }
    }, function(err, results) {
        if (err) {
            next(err);
        } else {
            res.render("shop_home", {
                shopStockView: results.shopStockView,
                shop: results.shop
            });
        }

    });
};

//4s店登陆
function login(req, res, next) {
    var condition = {
        loginName: req.body.loginName,
        loginPassword: req.body.loginPassword
    };
    service.client.post("/getShopByNameAndPsw", condition, function(error,
    request, response, data) {
        if (error) {
            next(error);
        } else {
            if (!data.loginName) {
                req.session.currentShop = null;
                res.send({
                    loginSuc: false
                });
            } else {
                req.session.currentShop = data;
                res.send({
                    loginSuc: true
                });
            }
        }
    });
}

function saveshop(req, res, next) {
    var shopMap = req.body;
    shopMap.id = req.session.currentShop.id;
    service.client.post("/updateShop", shopMap, function(error,
    request, response, data) {
        if (error) {
            next(error);
        } else {
            res.send({
                saveSuc: data
            });
        }
    });
}
