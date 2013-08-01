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
