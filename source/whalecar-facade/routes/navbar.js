/*
 * GET home page.
 */
var service = require("./tools/service-header");
var async = require("async");

// index page
exports.initData = function(req, res, next) {

    // call service
    async.parallel({
        allDicCitys: function(callback) {
            service.client.get("/queryDicCityAndArea", function(err, req, res, data) {
                callback(err, data);
            });
        },
        allBrands: function(callback) {
            service.client.get("/getAllBrandView", function(err, req, res, data) {
                callback(err, data);
            });
        }
    }, function(err, results) {
        if (err) {
            next(err);
        }
        res.locals.allDicCitys = results.allDicCitys;
        res.locals.allBrands = results.allBrands;
        next();
    });
};
