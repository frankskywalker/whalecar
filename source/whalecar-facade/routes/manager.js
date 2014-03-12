var service = require("./tools/service-header");
var moment = require('moment');

exports.userOffTicket = function(req,res){
    service.client.get("/manager/queryUserOffTicket",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}

exports.userOrder = function(req,res){
    service.client.get("/manager/queryUserOrder",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.userSubmitPrice = function(req,res){
    service.client.get("/manager/queryUserSubmitPrice",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}

exports.managerUser = function(req,res){
    service.client.get("/manager/managerUser",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.getCarData = function(req,res){
    res.render("layout/managerFrame");
}


exports.managerGetCarBrand = function(req,res){
    service.client.get("/manager/managerGetCarBrand",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.managerGetCarSubBrand = function(req,res){
    service.client.get("/manager/managerGetCarSubBrand",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}

exports.updateManagerGetCarBrand = function(req,res){
    var id = req.body.id;
    var cname = req.body.cname;
    var ename = req.body.ename;
    var logoPath = req.body.logoPath;
    var flagUseable = req.body.flagUseable;
    service.client.post("/manager/updateManagerGetCarBrand",{id:id,cname:cname,ename:ename,logoPath:logoPath,flagUseable:flagUseable},function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.managerGetCarModelLv1 = function(req,res){
    service.client.get("/manager/managerGetCarModelLv1",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.updateManagerGetCarModelLv1 = function(req,res){
    var id = req.body.id;
    var carBrandName = req.body.carBrandName;
    var carSubBrandName = req.body.carSubBrandName;
    var cname = req.body.cname;
    var ename = req.body.ename;
    if(req.body.orderIndex){
        var orderIndex = req.body.orderIndex;
    }
    var flagUseable = req.body.flagUseable;
    var offprice = req.body.offprice;
    var hotmodel = req.body.hotmodel;
    var description = req.body.description;
    service.client.post("/manager/updateManagerGetCarModelLv1",{id:id,carBrand:carBrandName,carSubBrand:carSubBrandName,cname:cname,
    ename:ename,orderIndex:orderIndex,flagUseable:flagUseable,offprice:offprice,hotmodel:hotmodel,description:description},function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.managerGetCarModelLv1Image = function(req,res){
    service.client.get("/manager/managerGetCarModelLv1Image",function(sErr,sReq,sRes,sData){
       res.json(sData);
    });
}


exports.updateManagerGetCarModelLv1Image = function(req,res){
    var id = req.body.id;
    var carModelLv1Name = req.body.carModelLv1Name;
    var imgPath = req.body.imgPath;
    var description = req.body.description;
    service.client.post("/manager/updateManagerGetCarModelLv1Image",{id:id,carModelLv1:carModelLv1Name,imgPath:imgPath,description:description},function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.managerGetCarModelLv2 = function(req,res){
    service.client.get("/manager/managerGetCarModelLv2",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.updateManagerGetCarModelLv2 = function(req,res){
    var id = req.body.id;
    var carModelLv1Name = req.body.carModelLv1Name;
    var shortName = req.body.shortName;
    var fullName = req.body.fullName;
    var orderIndex = req.body.orderIndex;
    var flagUseable = req.body.flagUseable;
    service.client.post("/manager/updateManagerGetCarModelLv2",{id:id,carModelLv1:carModelLv1Name,shortName:shortName,fullName:fullName,orderIndex:orderIndex,flagUseable:flagUseable},function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.managerGetCarModelLv3 = function(req,res){
    service.client.get("/manager/managerGetCarModelLv3",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}

exports.managerGetDicColor = function(req,res){
    service.client.get("/manager/managerGetDicColor",function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}


exports.addManagerCarBrand = function(req,res){
    console.log(req.body);
}



exports.updateManagerGetCarModelLv3 = function(req,res){
//    console.log(req.body);
    var id = req.body.id;
    var carModelLv2Name = req.body.carModelLv2Name;
    var carModelLv2 = req.body.carModelLv2;
    var shortName = req.body.shortName;
    var fullName = req.body.fullName;
    var colorOutsideCollectionName = req.body.colorOutsideCollectionName;
    var colorInsideCollectionName = req.body.colorInsideCollectionName;
    var factoryPrice = req.body.factoryPrice;
    var orderIndex = req.body.orderIndex;
    var flagUseable = req.body.flagUseable;
    var driveType = req.body.driveType;
    var gearType = req.body.gearType;

    service.client.post("/manager/updateManagerGetCarModelLv3",{id:id,carModelLv2Name:carModelLv2Name,carModelLv2:carModelLv2,shortName:shortName,fullName:fullName,colorOutsideCollection:colorOutsideCollectionName,
    colorInsideCollection:colorInsideCollectionName,factoryPrice:factoryPrice,orderIndex:orderIndex,flagUseable:flagUseable,driveType:driveType,gearType:gearType},function(sErr,sReq,sRes,sData){
        res.json(sData);
    });
}