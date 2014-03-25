/*
 * GET home page.
 */
var service = require("./tools/service-header");

// index page
exports.page = function(req, res, next) {
    service.client.get("/getPriceOffCarModelLv1",
        function(error, request, response, data1) {
            if (error) {
                next(error);
                return;
            }
    service.client.get("/queryCarBrandAndIdAndCname",
        function(error, request, response, data2){
            if(error){
                next(error);
                return;
            }
    res.render("index",{priceOffCarModelLv1:data1,idAndCname:data2});

            });
        });

};

