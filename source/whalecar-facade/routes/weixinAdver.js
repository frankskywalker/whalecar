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

exports.loginPage = function(req,res){
    service.client.get("/queryDicCityAndArea", function(err, sreq, sres, data) {
        if(err){
            next(err);
            return;
        }
        res.render("weixinLogin",{allDicCitys:data,wxOpenId:req.query.wxOpenId});
    });
}

// 用户登陆
exports.wxLogin = function(req, res, next) {
    var condition = {
        userEmail: req.body.userEmail,
        password: req.body.password,
        wxOpenId: req.body.wxOpenId
    };
    service.client.post("/isExistUserForWeixin", condition, function(error,
                                                                   request, response, data) {
        if (error) {
            next(error);
            return;
        } else {
            if (!data.processResult) {
                res.send({
                    loginSuc: false
                });
            } else {
                res.send({
                    loginSuc: true
                });
            }
        }
    });
}

exports.save = function(req,res,next){
    var wxOpenId = req.body.wxOpenId;
    service.client.get("/wiexininfo/save?wxOpenId=" + wxOpenId,function(serviceError,serviceRequest,seviceResponse,data){
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

