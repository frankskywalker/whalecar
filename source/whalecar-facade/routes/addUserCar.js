var service = require("./tools/service-header");
var async = require("async");
var moment = require('moment');

exports.addUserCar = function(req, res, next){
    <!--req.body.username,username取的是页面input里的name属性-->
    var userName = req.body.username;
    var phoneNum = req.body.phoneNum;
    var createDate =moment(new Date()).format("YYYY-MM-DD HH:mm:ss");
    var idAndCname = req.body.idAndCname;
    var carID = idAndCname.split(",")[0];
    var carName =idAndCname.split(",")[1];
    <!--userName:userName 前一个是数据库字段，后者是var的对象，/addUserCar后台对应方法名-->
    service.client.post("/addUserCar",{userName:userName,phoneNum:phoneNum,createDate:createDate,carID:carID,carName:carName},function(error, request, response, data){
        <!--跳转页面-->
        res.redirect("/index");
    });
}