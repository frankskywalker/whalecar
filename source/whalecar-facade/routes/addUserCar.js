var service = require("./tools/service-header");
var async = require("async");
var moment = require('moment');

exports.addUserCar = function(req, res, next){
    var userName = req.body.userName;
    var phoneNum = req.body.phoneNum;
    var createDate =moment(new Date()).format("YYYY-MM-DD HH:mm:ss");
    var idAndCname = req.body.idAndCname;
    var carID = idAndCname.split(",")[0];
    var carName =idAndCname.split(",")[1];
    service.client.post("/addUserCar",{userName:userName,phoneNum:phoneNum,createDate:createDate,carID:carID,carName:carName},function(error, request, response, data){
        res.redirect("/index");
    });
}