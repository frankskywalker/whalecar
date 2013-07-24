/**
 * Car Info Page
 */

var service = require("./tools/service-header");
var async = require("async");

exports.page = function(req, res, next) {
    var carModelLv1Id = req.query.id;
    if (!carModelLv1Id) {
        console.warn("car page error:param id is null!");
        next();
    }
    console.time("CAR_PAGE_SERVICE_LOAD");
    // call service
    async.parallel({
        carModelLv1: function(callback) {
            service.client.post("/getCarModelLv1ViewById", {
                carModelLv1Id: carModelLv1Id
            }, function(err, req, res, data) {
                callback(err, data);
            });
        },
        carModelLv2: function(callback) {
            service.client.post(
                "/getCarModelLv2ByLv1Id", {
                carModelLv1Id: carModelLv1Id
            },

            function(err, req, res,
            carModeLv2Data) {
                if (err) {
                    callback(err,
                    carModeLv2Data);
                } else {
                    // 根据
                    // carModelLv2的id，获取下面所有的shop_stockView
                    // 对象
                    for (var index in carModeLv2Data) {
                        // init param
                        var factoryPriceMin = 0;
                        var factoryPriceMax = 0;
                        var carPriceMin = 0;
                        var carPriceMax = 0;
                        // 查询服务
                        service.client.post(
                            "/getShopStockByCarModelLv2", {
                            carModelLv2Id: carModeLv2Data[index].id
                        },

                        function(
                        err,
                        req,
                        res,
                        shopStockViewData) {
                            if (err) {
                                // 查询shopstockview出错，中断，跳转
                                next(
                                err,
                                carModeLv2Data);
                            }
                            // 成功，将当前carModelLv2赋值
                            carModeLv2Data[index].shopStockView = shopStockViewData;
                            // 计算当前carModelLv2下面的总数和价格区间
                            for (var i = 0; i < shopStockViewData.length; i++) {
                                if (i === 0) {
                                    factoryPriceMin = shopStockViewData[i].factoryPrice;
                                    factoryPriceMax = shopStockViewData[i].factoryPrice;
                                    shopPriceMin = shopStockViewData[i].carPrice;
                                    shopPriceMax = shopStockViewData[i].carPrice;
                                } else {
                                    if (factoryPriceMin > shopStockViewData[i].factoryPrice) {
                                        factoryPriceMin = shopStockViewData[i].factoryPrice;
                                    }
                                    if (factoryPriceMax < shopStockViewData[i].factoryPrice) {
                                        factoryPriceMax = shopStockViewData[i].factoryPrice;
                                    }
                                    if (carPriceMin > shopStockViewData[i].carPrice) {
                                        carPriceMin = shopStockViewData[i].carPrice;
                                    }
                                    if (carPriceMax < shopStockViewData[i].carPrice) {
                                        carPriceMax = shopStockViewData[i].carPrice;
                                    }
                                }
                            }
                            // 最小出厂价格
                            carModeLv2Data[index].factoryPriceMin = factoryPriceMin;
                            // 最大出厂价格
                            carModeLv2Data[index].factoryPriceMax = factoryPriceMax;
                            // 最小商店价格
                            carModeLv2Data[index].carPriceMin = carPriceMin;
                            // 最大商店价格
                            carModeLv2Data[index].carPriceMax = carPriceMax;
                            // 库存总数
                            carModeLv2Data[index].stockCount = shopStockViewData.length;
                        });
                    }
                    callback(err,
                    carModeLv2Data);
                }
            });
        }
    }, function(err, results) {
        console.timeEnd("CAR_PAGE_SERVICE_LOAD");
        if (err) {
            next(err);
        } else {
            res.render("car", {
                carModelLv1: results.carModelLv1,
                carModelLv2: results.carModelLv2
            });
        }
    });
};
