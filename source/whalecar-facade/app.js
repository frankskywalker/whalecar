var express = require('express'),
    http = require('http'),
    path = require('path');
var util = require('util');
var expressValidator = require('express-validator');

var index = require('./routes/index'),
    dic = require('./routes/dic'),
    user = require('./routes/user'),
    car = require('./routes/car'),
    shop = require('./routes/shop'),
    shopstock = require('./routes/shopstock'),
    navbar = require('./routes/navbar'),
    userOrder = require('./routes/userOrder'),
    userOffTicket = require('./routes/userOffTicket'),
    userSubmitPrice = require('./routes/userSubmitPrice'),
    validator = require('./routes/validator'),
    manager = require('./routes/manager'),
    admin = require('./routes/admin'),
    golf7Adv = require('./routes/golf7Adv'),
    addUserCar = require('./routes/addUserCar'),
    weixinAdver = require('./routes/weixinAdver');

var app = express();
// all environments
app.set('port', process.env.PORT || 8001);
app.set('views', __dirname + '/views');
app.set('view engine', 'jade');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(express.cookieParser('whale car'));
app.use(express.session());

// add session to jade template
app.use(function(req, res, next) {
    res.locals.session = req.session;
    //初始化管理员权限
    if(!req.session.isAdmin){
        req.session.isAdmin = false;
    }
    next();
});

//validator middleware start
//添加fail方法，用于自定义检查
expressValidator.Validator.prototype.fail = function() {
    this.error(this.msg);
};

//isTel判断是否为号码
expressValidator.Validator.prototype.isTel = function() {
    //电话：1开头，并且11位
    if(!this.str.match(/^1\d{10}$/)){
        this.error(this.msg);
    }
};


//isValidPassword判断是否是合法密码
expressValidator.Validator.prototype.isValidPassword = function() {
    //密码：6位及以上
    if(!!this.str &&  this.str.length < 6){
        this.error(this.msg);
    }
};

app.use(expressValidator());
//validator middleware end
app.use(navbar.initData);
app.use(app.router);
// process error
app.use(function(err, req, res, next) {
    res.status(500);
    console.error(err.stack);
    res.render('error', {
        error: err
    });
});

app.use(express.static(path.join(__dirname, 'public')));


//mode configure
app.configure('development', function(){
    console.log("development mode now!");
    app.use(express.errorHandler({ dumpExceptions: true, showStack: true })); 
});

app.configure('production', function(){
    console.log("production mode now!");
    app.use(express.errorHandler()); 
});

//权限控制
function requireRole(role) {
    //用户权限
    if(role == "user"){
        return function(req,res,next){
            if(!req.session.currentUser){
                res.locals.prePage = req.url;
                user.loginpage(req,res,next);
            }
            else{
                next();
            }
        }
    }
    else if(role == "shop"){
        //shop权限
        return function(req, res, next) {
            if(!req.session.currentShop)
                res.send("请使用4S店用户登陆后重新访问页面！");
            else
                next();
        }
    }
    else if(role == "admin"){
        //admin权限
        return function(req, res, next) {
            if(!req.session.isAdmin){
                res.locals.prePage = req.url;
                admin.loginPage(req,res,next);
            }
            else
                next();
        }
    }
}


// ===============================
// router~
// ===============================
app.all('/ros*',function(req,res,next){
    var path = req.originalUrl;
    path = path.substr(4);
    console.log(path);
    res.redirect(path);
});
app.all('/', index.page);
app.all("/index",index.page);
app.all('/dic', dic.query);
app.all('/user', user.router);
app.all('/car', car.page);
app.all('/cardata',car.action);
app.all('/shophome',requireRole("shop"), shop.homepage);
app.all('/shop',shop.action);
app.all('/stockeditor',requireRole("shop"),shopstock.stockeditor);
app.all('/shopstock',requireRole("shop"),shopstock.action);
app.all('/shoplist',shop.shoplist);
app.all('/shopinfo',shop.shopinfo);
app.all('/userorder',requireRole("user"),userOrder.userorder);
app.all('/changeUserOrderProcessState',requireRole("shop"),userOrder.changeProcessState);
app.all('/usersubmitprice',requireRole("user"),userSubmitPrice.submitprice);
app.all('/changeUserSubmitPriceState',userSubmitPrice.changeUserSubmitPriceState);
app.all('/useroffticket',requireRole("user"),userOffTicket.offticket);
app.all('/useroffticketConfirm',requireRole("user"),userOffTicket.offticketConfirm);
app.all('/changeUserOffTicketState',userOffTicket.changeUserOffTicketState);
app.all('/userhome',requireRole("user"),user.homepage);
app.all('/validator',validator.validate);
app.all('/managerUserOffTicket',requireRole("admin"),manager.userOffTicket);
app.all('/managerUserOrder',requireRole("admin"),manager.userOrder);
app.all('/managerUserSubmitPrice',requireRole("admin"),manager.userSubmitPrice);
app.all('/managerUser',requireRole("admin"),manager.managerUser);
app.all('/getCarData',requireRole("admin"),manager.getCarData);
app.all("/admin_login",admin.login);
app.all("/managerGetCarBrand",manager.managerGetCarBrand);
app.all("/managerGetCarSubBrand",manager.managerGetCarSubBrand);
app.all("/updateManagerGetCarBrand",manager.updateManagerGetCarBrand);
app.all("/managerGetCarModelLv1",manager.managerGetCarModelLv1);
app.all("/updateManagerGetCarModelLv1",manager.updateManagerGetCarModelLv1);
app.all("/managerGetCarModelLv1Image",manager.managerGetCarModelLv1Image);
app.all("/updateManagerGetCarModelLv1Image",manager.updateManagerGetCarModelLv1Image);
app.all("/managerGetCarModelLv2",manager.managerGetCarModelLv2);
app.all("/updateManagerGetCarModelLv2",manager.updateManagerGetCarModelLv2);
app.all("/managerGetCarModelLv3",manager.managerGetCarModelLv3);
app.all("/managerGetDicColor",manager.managerGetDicColor);
app.all("/addManagerCarBrand",manager.addManagerCarBrand);
app.all("/updateManagerGetCarModelLv3",manager.updateManagerGetCarModelLv3);
app.all("/addUserCar",addUserCar.addUserCar);
app.all("/cartest",function(req,res){
    res.render("cartest")
});


app.all('/advertisement0',function(req,res){
    res.render("./advertisement/adver");
}) ;

app.all('/advertisement1',function(req,res){
    res.render("./advertisement/adver1");
}) ;

app.all('/advertisement2',function(req,res){
    res.render("./advertisement/adver2");
}) ;

app.all('/advertisement3',function(req,res){
    res.render("./advertisement/adver3");
}) ;

app.all('/advertisement4',function(req,res){
    res.render("./advertisement/adver4");
}) ;

app.all('/advertisement5',function(req,res){
    res.render("./advertisement/adver5");
}) ;

app.all('/advertisement6',function(req,res){
    res.render("./advertisement/adver6");
}) ;

app.all('/advertisement7',function(req,res){
    res.render("./advertisement/adver7");
}) ;

app.all('/advertisement8',function(req,res){
    res.render("./advertisement/adver8");
}) ;

app.all('/advertisement9',function(req,res){
    res.render("./advertisement/adver9");
}) ;

app.all('/weixinAdver',weixinAdver.page);
app.all('/weixinAdver/save',weixinAdver.save);
app.all('/weixinAdver/updateForwardCont',weixinAdver.updateForwardCount);
app.all('/weixinRank',weixinAdver.rankList);
app.all('/weixinLogin',weixinAdver.loginPage);
app.all('/weixinLogin/login',weixinAdver.wxLogin);


app.all('/golf7adver',golf7Adv.page);
app.all('/golf7adverSave',golf7Adv.save);

app.all('/vw_adver',golf7Adv.page2);
app.all('/vwAdverSave',golf7Adv.save2);

app.all('/vw2_adver',golf7Adv.page3);
app.all('/vw2AdverSave',golf7Adv.save3);

app.all('/vw3_adver',golf7Adv.page4);
app.all('/vw3AdverSave',golf7Adv.save4);

app.all('/vw4_adver',golf7Adv.page5);
app.all('/vw4AdverSave',golf7Adv.save5);

app.all('/way1',function(req,res){
    res.render("way1");
}) ;
app.all('/about_us',function(req,res){
    res.render("about_us");
}) ;

http.createServer(app).listen(app.get('port'), function() {
    console.log('Express server listening on port ' + app.get('port'));
});
