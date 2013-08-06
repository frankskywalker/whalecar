var express = require('express'),
    http = require('http'),
    path = require('path');

var index = require('./routes/index'),
    dic = require('./routes/dic'),
    user = require('./routes/user'),
    car = require('./routes/car'),
    shop = require('./routes/shop'),
    navbar = require('./routes/navbar');
    

var app = express();

// all environments
app.set('port', process.env.PORT || 3000);
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

// ===============================
// router~
// ===============================
app.all('/', index.page);
app.all('/dic', dic.query);
app.all('/user', user.router);
app.all('/car', car.page);
app.all('/shophome', shop.homepage);
app.all('/shop',shop.action);
app.all('/stockeditor',shop.stockeditor);
app.all('/shoplist',shop.shoplist);

http.createServer(app).listen(app.get('port'), function() {
    console.log('Express server listening on port ' + app.get('port'));
});
