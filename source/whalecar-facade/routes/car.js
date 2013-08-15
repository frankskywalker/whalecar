/**
 * Car Info Page
 */

var service = require("./tools/service-header");
var async = require("async");

exports.action = function(req, res, next) {
    var type = req.query.type;

    if (type == "getCarModelPagination") {
        getCarModelPagination(req, res, next);
    }
};

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
                "/getCarModelLv2WithStockViewByLv1Id", {
                carModelLv1Id: carModelLv1Id
            }, function(err, req, res,
            carModelLv2WithStockView) {
                callback(err, carModelLv2WithStockView);
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

function getCarModelPagination(req, res, next) {
    // get request Params
    var pageIndex = req.query.pageIndex;
    var carBrand = req.query.carBrand;
    var carModelLv1 = req.query.carModelLv1;
    var priceMin = req.query.priceMin;
    var priceMax = req.query.priceMax;
    var city = req.query.city;
    var orderByName = req.query.orderByName;
    var orderType = req.query.orderType;

    // set default Params
    if (!pageIndex) pageIndex = 1; // 默认为1
    if (!carBrand) carBrand = '';
    if (!carModelLv1) carModelLv1 = '';
    if (!priceMin) priceMin = '';
    if (!priceMax) priceMax = '';
    if (!city) city = '';
    if (!orderByName) orderByName = 'sellNum';
    if (!orderType) orderType = 'asc';

    var modelViewConditionParams = {
        pageIndex: pageIndex,
        carBrand: carBrand,
        carModelLv1: carModelLv1,
        priceMin: priceMin,
        priceMax: priceMax,
        city: city,
        orderByName: orderByName,
        orderType: orderType,
        pageSize: 30
    };

    async.parallel({
        allDicCitys: function(callback) {
            service.client.get("/getAllDicCity", function(err, req, res, data) {
                callback(err, data);
            });
        },
        allBrands: function(callback) {
            service.client.get("/getAllBrandView", function(err, req, res, data) {
                callback(err, data);
            });
        },
        carModels: function(callback) {
            if (carBrand !== "") {
                service.client.post("/getCarModelLv1ByBrand", {
                    carBrand: carBrand
                }, function(err, req, res, data) {
                    callback(err, data);
                });
            } else {
                callback(null, []);
            }
        },
        carModelViews: function(callback) {
            service.client.post("/getModelView", modelViewConditionParams,

            function(err, req, res, data) {
                callback(err, data);
            });
        }
    }, function(err, results) {
        if (err) {
            next(err);
        } else {
            res.render("fragment/carModelViewList", {
                carModelViews: results.carModelViews,
                allDicCitys: results.allDicCitys,
                allBrands: results.allBrands,
                carModels: results.carModels,
                conditionParams: modelViewConditionParams
            });
        }
    });
}
