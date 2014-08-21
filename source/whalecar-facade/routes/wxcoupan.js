/**
 * Created by wufei on 14-8-21.
 */
var service = require("./tools/service-header");

exports.page_wx = function(req, res, next) {
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
                    res.render("wxcoupan",{priceOffCarModelLv1:data1,idAndCname:data2});
                    console.log(data1);
                });
        });
};
