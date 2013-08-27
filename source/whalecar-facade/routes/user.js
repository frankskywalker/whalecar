/**
 * User Controller
 */

var service = require("./tools/service-header");

exports.router = function(req, res, next) {
    if (req.query.type == "login") {
        login(req, res, next);
    } else if (req.query.type == "regist") {
        regist(req, res, next);
    } else if(req.query.type == "registerValidator"){
        validator(req, res, next);
    }
};

exports.homepage = function(req,res,next){
    var userId = req.session.currentUser.id;
    service.client.get("/getUserOrderByUser?userId="+ userId, function(error,
                                                                  request, response, data) {
        res.render("user_home",{userOrder:data});
    });
};

// 用户登陆
function login(req, res, next) {
    var condition = {
        loginName: req.body.loginName,
        password: req.body.password
    };
    service.client.post("/queryByNameAndPsw", condition, function(error,
    request, response, data) {
        console.log("queryByNameAndPsw ok");
        if (error) {
            next(error);
        } else {
            if (!data.loginName) {
                req.session.currentUser = null;
                res.send({
                    loginSuc: false
                });
            } else {
                req.session.currentUser = data;
                res.send({
                    loginSuc: true
                });
            }
        }
    });
}

// 注册新用户
function regist(req, res, next) {
    var user = {
        loginName: req.body.loginName,
        loginPassword: req.body.loginPassword,
        userCity: req.body.userCity,
        userEmail: req.body.userEmail,
        userName: req.body.userName,
        userTel: req.body.userTel
    };
    service.client.post("/createUser", user, function(error, request, response,
    data) {
        if (error) {
            next(error);
        } else {
            res.send({
                regitSuc: data
            });
        }
    });
}