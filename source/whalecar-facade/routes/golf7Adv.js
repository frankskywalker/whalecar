/**
 * Created by ruihuang on 14-3-3.
 */
var service = require("./tools/service-header");

exports.page = function(req,res){
    res.render("golf7_adver");
}

exports.page2 = function(req,res){
    res.render("vw_adver");
}

exports.page3 = function(req,res){
    res.render("vw2_adver");
}

exports.save = function(req,res,next){
    var golf7Adv = req.body;
    var attentionModelStr = "";
    if(golf7Adv.attentionModel){
        for(var i = 0;i < golf7Adv.attentionModel.length;i++){
            attentionModelStr += golf7Adv.attentionModel[i];
            if(i != golf7Adv.attentionModel.length - 1){
                attentionModelStr += ",";
            }
        }
        golf7Adv.attentionModel = attentionModelStr;
    }
    golf7Adv.type = 1;
    service.client.post("/saveGolf7Adv",golf7Adv,function(error){
        if(error){
            next(error);
            return;
        }
        res.render("golf7_adver");
    });
}

exports.save2 = function(req,res,next){
    var golf7Adv = req.body;
    var attentionModelStr = "";
    if(golf7Adv.attentionModel){
        for(var i = 0;i < golf7Adv.attentionModel.length;i++){
            attentionModelStr += golf7Adv.attentionModel[i];
            if(i != golf7Adv.attentionModel.length - 1){
                attentionModelStr += ",";
            }
        }
        golf7Adv.attentionModel = attentionModelStr;
    }
    golf7Adv.type = 2;
    service.client.post("/saveGolf7Adv",golf7Adv,function(error){
        if(error){
            next(error);
            return;
        }
        res.render("vw_adver");
    });
}

exports.save3 = function(req,res,next){
    var golf7Adv = req.body;
    golf7Adv.type = 3;
    service.client.post("/saveGolf7Adv",golf7Adv,function(error){
        if(error){
            next(error);
            return;
        }
        res.render("vw2_adver");
    });
}