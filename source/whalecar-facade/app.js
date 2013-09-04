var express = require('express'),
    http = require('http'),
    path = require('path'),
    expressValidator = require('express-validator');

var index = require('./routes/index'),
    dic = require('./routes/dic'),
    user = require('./routes/user'),
    car = require('./routes/car'),
    shop = require('./routes/shop'),
    shopstock = require('./routes/shopstock'),
    navbar = require('./routes/navbar'),
    userOrder = require('./routes/userOrder'),
    userOffTicket = require('./routes/userOffTicket');
    userSubmitPrice = require('./routes/userSubmitPrice'),
    validator = require('./routes/validator');
    

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
}

// ===============================
// router~
// ===============================
app.all('/', index.page);
app.all('/index', index.page);
app.all('/dic', dic.query);
app.all('/user', user.router);
app.all('/car', car.page);
app.all('/cardata',car.action);
app.all('/shophome',requireRole("shop"), shop.homepage);
app.all('/shop',shop.action);
app.all('/stockeditor',requireRole("shop"),shopstock.stockeditor);
app.all('/shoplist',shop.shoplist);
app.all('/shopinfo',shop.shopinfo);
app.all('/userorder',requireRole("user"),userOrder.userorder);
app.all('/usersubmitprice',requireRole("user"),userSubmitPrice.submitprice);
app.all('/useroffticket',requireRole("user"),userOffTicket.offticket);
app.all('/userhome',requireRole("user"),user.homepage);
app.all('/validator',validator.validate);

http.createServer(app).listen(app.get('port'), function() {
    console.log('Express server listening on port ' + app.get('port'));
});
