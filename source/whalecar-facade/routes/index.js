/*
 * GET home page.
 */
var service = require("./tools/service-header");
var async = require("async");

// index page
exports.page = function(req, res, next) {

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
        orderType: orderType
    };

    // call service
    async.parallel({
        allDicCitys: function(callback) {
            service.client.get("/getAllDicCity", function(err, req, res, data) {
                callback(err, data);
            });
        },
        allBrands: function(callback) {
            service.client.get("/getAllBrand", function(err, req, res, data) {
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
        }
        //放到session里面，保证每个页面都能获取到当前条件
        res.locals.session.conditionParams = modelViewConditionParams;
        // render peage
        res.render("index", {
            carModelViews: results.carModelViews,
            conditionParams: modelViewConditionParams,
            isRefresh : req.query.isRefresh
        });
    });
};
