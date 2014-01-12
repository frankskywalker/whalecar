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
    var city = req.query.city;
    var shop = req.query.shop;
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
                carModelLv1Id: carModelLv1Id,
                city:city,
                shop:shop
            }, function(err, req, res,
            carModelLv2WithStockView) {
                callback(err, carModelLv2WithStockView);
            });
        },
        carModelLv1Img:function(callback){
            service.client.get("/queryCarModelLv1ImgById?carModelLv1=" + carModelLv1Id,
            function(err, req, res,data){
                callback(err,data);
            });
        }
    }, function(err, results) {
        console.timeEnd("CAR_PAGE_SERVICE_LOAD");
        if (err) {
            next(err);
        } else {
            var carExtendInfo = calcCarStatistics(results.carModelLv2);
            service.client.get("/getSimilarCarModelLv1ByPrice?price=" + carExtendInfo.factoryAvgPrice,
                function(serr, sreq, sres,similarCarModelLv1){
                    if (serr) {
                        next(serr);
                        return;
                    }
                    res.render("car", {
                        carModelLv1: results.carModelLv1,
                        carModelLv2: results.carModelLv2,
                        carModelLv1Img:results.carModelLv1Img,
                        carExtendInfo : carExtendInfo,
                        similarCarModelLv1 : similarCarModelLv1
                    });
                }
            );

        }
    });
};

//通过carModelLv2统计最大最小价格以及所有颜色和型号
function calcCarStatistics(carModelLv2List){
    var factoryMaxPrice = 0;
    var factoryMinPrice = 0;
    var carMaxPrice = 0;
    var carMinPrice = 0;
    var colorList = {};
    var carModelLv2NameList = new Array();

    for(var index in carModelLv2List){

        var carModelLv2 = carModelLv2List[index];
        carModelLv2NameList[index] = carModelLv2.fullName;
        if(carModelLv2.shopStockList.length == 0){
            continue;
        }
        if(factoryMaxPrice == 0 || carModelLv2.factoryPriceMax > factoryMaxPrice){
            factoryMaxPrice = carModelLv2.factoryPriceMax
        }

        if(factoryMinPrice == 0 || carModelLv2.factoryPriceMin < factoryMinPrice){
            factoryMinPrice = carModelLv2.factoryPriceMin;
        }

        if(carMaxPrice == 0 || carModelLv2.carPriceMax > carMaxPrice){
            carMaxPrice = carModelLv2.carPriceMax;
        }

        if(carMinPrice == 0 || carModelLv2.carPriceMin < carMinPrice){
            carMinPrice = carModelLv2.carPriceMin
        }

        for(var colorIndex in carModelLv2.outsideColorList){
            var dicColor = carModelLv2.outsideColorList[colorIndex];
            colorList[dicColor.colorRgb] = dicColor;
        }
    }
    return {factoryMaxPrice : factoryMaxPrice,
        factoryMinPrice : factoryMinPrice,
        factoryAvgPrice : (factoryMaxPrice + factoryMinPrice) / 2,
        carMaxPrice : carMaxPrice,
        carMinPrice : carMinPrice,
        colorList : colorList,
        carModelLv2NameList:carModelLv2NameList};
}

function getCarModelPagination(req, res, next) {
    // get request Params
    var pageIndex = req.query.pageIndex;
    var carBrandStr = req.query.carBrand;
    var carModelLv1 = req.query.carModelLv1;
    var priceMin = req.query.priceMin;
    var priceMax = req.query.priceMax;
    var city = req.query.city;
    var shop = req.query.shop;
    var orderByName = req.query.orderByName;
    var orderType = req.query.orderType;
    var user = "";

    // set default Params
    if (!pageIndex) pageIndex = 1; // 默认为1
    if (!carBrandStr) carBrandStr = '';
    if (!carModelLv1) carModelLv1 = '';
    if (!priceMin) priceMin = '';
    if (!priceMax) priceMax = '';
    if (!city) city = '';
    if (!shop) shop = '';
    if (!orderByName) orderByName = 'sellNum';
    if (!orderType) orderType = 'asc';

    //set current user id
    if(!!req.session.currentUser){
        user = req.session.currentUser.id;
    }

    var carBrand = "";
    var carSubBrand = "";
    if(carBrandStr != ''){
        var carBrandArray = carBrandStr.split(",");
        carBrand = carBrandArray[0];
        carSubBrand = carBrandArray[1];
    }

    var modelViewConditionParams = {
        pageIndex: pageIndex,
        carBrand: carBrand,
        carSubBrand: carSubBrand,
        carModelLv1: carModelLv1,
        priceMin: priceMin,
        priceMax: priceMax,
        city: city,
        shop:shop,
        user:user,
        orderByName: orderByName,
        orderType: orderType,
        pageSize: 20
    };


    async.parallel({
        allDicCitys: function(callback) {
            service.client.get("/getAllDicCity", function(err, req, res, data) {
                callback(err, data);
            });
        },
        allBrands: function(callback) {
            service.client.get("/getAllBrandAndSubBrand", function(err, req, res, data) {
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
            //init conditionParams
            if(!req.session.conditionParams){
                req.session.conditionParams = {};
            }

            res.render("fragment/carModelViewList", {
                carModelViews: results.carModelViews,
                allDicCitys: results.allDicCitys,
                allBrands: results.allBrands,
                carModels: results.carModels,
                conditionParams: modelViewConditionParams,
                shide:req.query.shide
            });
        }
    });
}
