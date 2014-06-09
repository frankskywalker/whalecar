var service = require("./tools/service-header");

exports.query = function(req, res,next) {
    var type = req.query.type;
    if (type == 'getCarModelLv1ByBrand') {
        //根据BrandId获取carModel
        getCarModelLv1ByBrand(req, res,next);
    } else if (type == 'getCarModelLv2ByLv1Id') {
        //根据Lv2Id获取所有的Lv3
        getCarModelLv2ByLv1Id(req, res,next);
    } else if (type == 'getCarModelLv3ByLv2Id') {
        //根据Lv2Id获取所有的Lv3
        getCarModelLv3ByLv2Id(req, res,next);
    } else if (type == 'getOutsideDicColorByCarModelLv3') {
        getOutsideDicColorByCarModelLv3(req,res,next);
    } else if (type == 'getInsideDicColorByCarModelLv3') {
        getInsideDicColorByCarModelLv3(req,res,next);
    }else if (type == 'DicCity') {
        //获取所有的dicCity
        getDicCity(req, res,next);
    }else {
	console.log("dic request type is null");
        res.send("{error:'type is not funod!!'}");
    }
};

// Get CarModel By BrandId
function getCarModelLv1ByBrand(request, response, next) {
    service.client.post("/getCarModelLv1ByBrand", {
        carBrand: req.query.brandId
    }, function(err, req, res, data) {
        if (err) {
            next(err);
        } else {
            response.send(data);
        }
    });
}

//Get CarModelLv2 By Lv1Id
function getCarModelLv2ByLv1Id(request, response, next) {
    service.client.post("/getCarModelLv2ByLv1Id", {
        carModelLv1Id: request.query.carModelLv1Id
    }, function(err, req, res, data) {
        if (err) {
            next(err);
        } else {
            response.send(data);
        }
    });
}

//Get CarModelLv3 By Lv2Id
function getCarModelLv3ByLv2Id(request, response, next) {
    service.client.post("/getCarModelLv3ByLv2Id", {
        carModelLv2Id: request.query.carModelLv2Id
    }, function(err, req, res, data) {
        if (err) {
            next(err);
        } else {
            response.send(data);
        }
    });
}

//Get Dic City and area
function getDicCity(request, response, next) {
    service.client.get("/queryDicCityAndArea", function(err, req, res, data) {
        if (err) {
            next(err);
        } else {
            response.send(data);
        }
    });
}


//根据carModelLv3获取dicColor

function getOutsideDicColorByCarModelLv3(request, response, next) {
    var carModelLv3Id = request.query.carModelLv3Id;
    service.client.get("/getDicColorByCarModelLv3?outOrInColor=out&carModelLv3Id=" + carModelLv3Id, function(err, req, res, data) {
        if (err) {
            next(err);
        } else {
            response.send(data);
        }
    });
}

function getInsideDicColorByCarModelLv3(request, response, next) {
    var carModelLv3Id = request.query.carModelLv3Id;
    service.client.get("/getDicColorByCarModelLv3?outOrInColor=in&carModelLv3Id=" + carModelLv3Id, function(err, req, res, data) {
        if (err) {
            next(err);
        } else {
            response.send(data);
        }
    });
}

