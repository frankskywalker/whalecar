/*
 * GET home page.
 */
var service = require("./tools/service-header");

// index page
exports.page = function(req, res, next) {
    service.client.get("/getPriceOffCarModelLv1",
        function(error, request, response, data) {
            if (error) {
                next(error);
                return;
            }
            res.render("index",{priceOffCarModelLv1:data});
        });
};