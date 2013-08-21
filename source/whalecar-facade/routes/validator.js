/**
 * User: ruihuang
 * Date: 13-8-20
 * Time: 上午10:54
 */

var service = require("./tools/service-header");

exports.validate = function(req,res,next){
    //1.获取type和formId的固定参数
    var type = req.query.type;
    var formId = req.query.formId;

    //2.根据type和formId来router到指定的验证方法
    if(type == "validateLoginName" && formId == "registForm"){
        validateLoginName(req,res,next,function(){
            res.json(req.validationErrors());
        });
    }
    else if(type == "validateUserRegister" && formId == "registForm"){
        validateUserRegister(req,res,next,function(){
            res.json(req.validationErrors());
        });
    }


}

function validateLoginName(req,res,next,callback){
    service.client.post("/userIsExist", {loginName:req.body.loginName},
        function(error, request, response,data) {
            if (error) {
                next(error);
            } else {
                if(data.processResult == true){
                    req.assert('loginName', '用户名已经存在').fail();
                }
            }
            callback();
        }
    );
}

function validateUserRegister(req,res,next,callback){
    validateLoginName(req,res,next,function(){
        req.assert("loginPassword","请输入6位以上密码").notEmpty().isValidPassword();
        req.assert("loginPasswordConfirm","确认密码不一致").equals(req.body.loginPassword);
        req.assert("userName","请输入姓名").notEmpty();
        req.assert("userCity","请选择城市").notEmpty();
        req.assert("userTel","格式不正确").isTel();
        req.assert("userEmail","格式不正确").isEmail();
        callback();
    });

}