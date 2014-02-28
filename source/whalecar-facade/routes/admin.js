/**
 * 后台登陆页面
 * @param req
 * @param res
 * @param next
 */
exports.loginPage = function(req,res,next){
    res.render("admin_login");
}



exports.login = function(req,res){
    var name = req.body.name;
    var password = req.body.password;

    //验证
    if(name == "admin" && password == 'teecar2014'){
        req.session.isAdmin = true;
        res.json({
            loginSuc: true
        });
    }
    else{
        res.json({
            loginSuc: false
        });
    }
}