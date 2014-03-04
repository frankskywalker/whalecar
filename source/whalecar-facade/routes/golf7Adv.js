/**
 * Created by ruihuang on 14-3-3.
 */
var service = require("./tools/service-header");

exports.page = function(req,res){
    res.render("golf7_adver");
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
    service.client.post("/saveGolf7Adv",golf7Adv,function(error){
        if(error){
            next(error);
            return;
        }
        res.render("golf7_adver");
    });
}