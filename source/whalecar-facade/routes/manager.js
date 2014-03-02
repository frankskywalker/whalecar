var service = require("./tools/service-header");
var moment = require('moment');

exports.userOffTicket = function(req,res){
    service.client.get("/manager/queryUserOffTicket",function(sErr,sReq,sRes,sData){
        res.render("managerUserOffTicket",{data:sData,moment:moment});
    });
}

exports.userOrder = function(req,res){
    service.client.get("/manager/queryUserOrder",function(sErr,sReq,sRes,sData){
        res.render("managerUserOrder",{data:sData,moment:moment});
    });
}


exports.userSubmitPrice = function(req,res){
    service.client.get("/manager/queryUserSubmitPrice",function(sErr,sReq,sRes,sData){
        res.render("managerUserSubmitPrice",{data:sData,moment:moment});
    });
}

exports.managerUser = function(req,res){
    service.client.get("/manager/managerUser",function(sErr,sReq,sRes,sData){
        res.render("managerUser",{data:sData,moment:moment});
    });
}