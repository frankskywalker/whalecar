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
            service.client.get("/getAllDicCity", function(err, req, res, data) {
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
        //init conditionParams,否则不是从首页进入会发现不了conditionParams
        if(!req.session.conditionParams){
            req.session.conditionParams = {};
        }
        next();
    });
};
