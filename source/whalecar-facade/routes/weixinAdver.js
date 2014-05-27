var service = require("./tools/service-header");

exports.page = function(req,res,next){
    var openId = req.query.openId;
    if(!!openId){
        service.client.get("/wiexininfo/queryByOpenId?openId=" + openId,function(serviceError,serviceRequest,seviceResponse,data){
            if(serviceError){
                next(serviceError);
                return;
            }
            res.render("weixinAdver",{weixinInfo:data});
        });
    }
    else{
        res.render("weixinAdver",{weixinAdver:{}});
    }

}

exports.save = function(req,res,next){
    var weixinInfo = req.body;
    service.client.post("/wiexininfo/save",weixinInfo,function(serviceError,serviceRequest,seviceResponse,data){
        if(serviceError){
            next(serviceError);
            return;
        }
        res.json(data);
    });
}

exports.updateForwardCount = function(req,res,next){
    var openId = req.query.openId;
    service.client.get("/wiexininfo/updateForwardCount?openId=" + openId,function(serviceError,serviceRequest,seviceResponse,data){
        if(serviceError){
            next(serviceError);
            return;
        }
        res.json(data);
    });
}

exports.rankList = function(req,res,next){
    service.client.get("/weixininfo/queryWithRank",function(serviceError,serviceRequest,seviceResponse,data){
        if(serviceError){
            next(serviceError);
            return;
        }
        var total = 0;
        for(var i in data){
            total += data[i].forwardCount;
        }
        res.render("weixinRank",{weixinList:data,total:total});
    });
}

