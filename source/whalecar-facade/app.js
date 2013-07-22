
var express = require('express')
  , http = require('http')
  , path = require('path');

var index = require('./routes/index')
, dic = require('./routes/dic')
, user = require('./routes/user')
, car = require('./routes/car');


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
//add session to jade template
app.use(function(req, res, next) {
  res.locals.session = req.session;
  next();
});
app.use(app.router);
//process error
app.use(function(err, req, res, next){
  res.status(500);
  console.error(err.stack);
  res.render('error', { error: err });
});

app.use(express.static(path.join(__dirname, 'public')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

//===============================
//router~
//===============================
app.get('/', index.page);
app.get('/dic', dic.query);
app.post('/user', user.router);
app.get('/car', car.page);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
