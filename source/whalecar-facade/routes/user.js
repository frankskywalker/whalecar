/**
 * User Controller
 */

var service = require("./tools/service-header");
var async = require("async");
var moment = require('moment');

exports.router = function(req, res, next) {
    if (req.query.type == "login") {
        login(req, res, next);
    }
    else if (req.query.type == "logout") {
        logout(req, res, next);
    }
    else if (req.query.type == "regist") {
        regist(req, res, next);
    }
    else if(req.query.type == "favorite"){
        favorite(req, res, next);
    }
    else if(req.query.type == "ChangeInf"){
        ChangeInf(req,res,next);
    }
    else if(req.query.type == "ChangeUserPsw"){
        ChangeUserPsw(req,res,next);
    }
};

exports.homepage = function(req,res,next){
    var userId = req.session.currentUser.id;
    var userId = req.session.currentUser.id;
    async.parallel({
        userOrder:function(callback){
            service.client.get("/getUserOrderByUser?userId="+ userId,
                function(error,request, response, data) {
                    callback(error,data);
            });
        },
        stockEmptyUserOrder:function(callback){
            service.client.get("/getStockEmptyUserOrderByUser?userId="+ userId,
                function(error,request, response, data) {
                    callback(error,data);
                });
        },
        userSubmitPrice:function(callback){
            service.client.get("/getUserSubmitPriceByUser?userId="+ userId,
                function(error,request, response, data) {
                    callback(error,data);
            });
        },
        userOffTicket:function(callback){
            service.client.get("/getUserOffTicketByUser?userId="+ userId,
                function(error,request, response, data) {
                    callback(error,data);
            });
        },
        userCarFavorite:function(callback){
            service.client.get("/getUserCarFavorite?userId=" + userId,function(error,request, response, data){
                callback(error,data);
            });
        }
    },function(errors,results){
        if(errors){
            next(errors);
            return;
        }
        results.moment = moment;
        res.render("user_home",results);
    });
};

exports.loginpage = function(req,res,next){
    res.render("user_login");
};

function favorite(req,res,next){
    if(!req.session.currentUser){
        res.json({processResult:false,error:"needLogin"});
        return;
    }
    var action =  req.body.action;
    var condition = {userId : req.session.currentUser.id,carModelLv1:req.body.carModelLv1};
    if(action == 'del'){
        service.client.post("/removeUserFavorite", condition,
            function(error,request, response, data) {
            res.json(data);
        });
    }
    else if (action == 'add'){
        service.client.post("/saveUserFavorite", condition,
            function(error,request, response, data) {
            res.json(data);
        });
    }
}

// 用户登陆
function login(req, res, next) {
    var condition = {
        userEmail: req.body.userEmail,
        password: req.body.password
    };
    service.client.post("/queryByEmailAndPsw", condition, function(error,
    request, response, data) {
        if (error) {
            next(error);
        } else {
            if (!data.userEmail) {
                res.send({
                    loginSuc: false
                });
            } else {
                req.session.currentShop = null;
                req.session.currentUser = data;
                res.send({
                    loginSuc: true
                });
            }
        }
    });
}

function logout(req, res, next) {
    req.session.currentUser = null;
    res.send({
        logoutSuc: true
    });
}

// 注册新用户
function regist(req, res, next) {
    var user = {
        //loginName: req.body.loginName,
        userEmail: req.body.userEmail,
        loginPassword: req.body.loginPassword,
        userCity: req.body.userCity,
        userArea: req.body.userArea,    //用户地区
        userName: req.body.userName,
        userTel: req.body.userTel,
        wxOpenId:req.body.wxOpenId
    };
    service.client.post("/createUser", user, function(error, request, response,
    data) {
        if (error) {
            next(error);
        } else {
            res.send({
                registSuc: data
            });
        }
    });
}

function ChangeInf(req,res,next){
    var info={
        userId:req.body.userId,
        userName:req.body.userName,
        userTel:req.body.userTel,
        userEmail:req.body.userEmail
    };
    service.client.post("/ChangeUserInf",info,function(error,request,response,data){
        if(error){
            next(error);
        }else{
            req.session.currentUser.userName=req.body.userName;
            req.session.currentUser.userTel=req.body.userTel;
            req.session.currentUser.userEmail=req.body.userEmail;
            res.send({
                ChangeInfSuc:data
            });
        }
    });
}

function ChangeUserPsw(req,res,next){
    var psw={
        userId:req.body.userId,
        loginPassword:req.body.loginPassword
    }
    service.client.post("/ChangeUserPsw",psw,function(error,request,response,data){
        if(error){
            next(error);
        }else{
            res.send({
                ChangeUserPswSuc:data
            });
        }
    });
}