var service = require("./tools/service-header");

exports.query = function(req, res) {
    var type = req.query.type;
    if (type == 'CarModelLv1') {
        getCarModelLv1ByBrand(req, res);
    } else if (type == 'DicCity') {
        getDicCity(req, res);
    } else {
        req.send("{error:'type is not funod!!'}");
    }
};

// Get CarModel By BrandId
function getCarModelLv1ByBrand(request, response) {
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

function getDicCity(request, response) {
    service.client.get("/getAllDicCity", function(err, req, res, data) {
        if (err) {
            next(err);
        } else {
            response.send(data);
        }
    });
}
