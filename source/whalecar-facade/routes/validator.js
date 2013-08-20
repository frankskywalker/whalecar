/**
 * User: ruihuang
 * Date: 13-8-20
 * Time: 上午10:54
 */

var service = require("./tools/service-header");
var expressValidator = require('express-validator');

exports.validate = function(req,res,next){
    var type = req.query.type;
    var formId = req.query.formId;
    if(type == "validateLoginName" && formId == "registForm"){
        validateLoginName(req,res,next);
    }

}

function validateLoginName(req,res,next){
    service.client.post("/userIsExist", {loginName:req.body.loginName},
        function(error, request, response,data) {
            if (error) {
                next(error);
            } else {
                res.send({
                    regitSuc: data
                });
            }
        }
    );
}