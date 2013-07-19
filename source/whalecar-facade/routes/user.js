/**
 * User Controller
 */

var service = require("./tools/service-header");

exports.router = function(req,res,next){
    if(req.query.type == "login"){
        login(req,res,next);
    }
    else if(req.query.type == "regist"){
        regist(req,res,next);
    }
};

//用户登陆
function login(req,res,next){
    var condition = {loginName:req.query.loginName,password:req.query.password};
    req.session.currentUser = null;
	service.client.post("/queryByNameAndPsw",condition,function(err, request, response, data){
        console.log("queryByNameAndPsw ok");
        if(err){
             next(err);
        }
        else{
            if(!data.length){
                 req.session.currentUser = null;
                 res.send("{loginSuc:false}");
            }
            else{
                req.session.currentUser = data;
                res.send("{loginSuc:true}");
            }
        }
    });
}

//注册新用户
function regist(req,res,next){
	var user = {loginName : req.query.loginName,
                password : req.query.password,
                userCity : req.query.password,
                userEmail : req.query.userEmail,
                userName : req.query.userName,
                userTel : req.query.userTel};
	service.client.post("/createUser",user,function(err, req, res, data){
        console.log("createUser ok");
        if(err){
            next(err);
        }
        else{
            res.send("{regitSuc:data}");
        }
    });
}