/*
 * GET home page.
 */
var service = require("./tools/service-header");
var async = require("async");

//index page
exports.page = function(req, res) {
  
  //get request Params
  var pageIndex = req.query.pageIndex;
  var carBrand = req.query.carBrand;
  var carModelLv1 = req.query.carModelLv1;
  var priceMin = req.query.priceMin;
  var priceMax = req.query.priceMax;
  var city = req.query.city;
  
  //set default Params
  if(!pageIndex) pageIndex = 1;//默认为1
  if(!carBrand) carBrand = '';
  if(!carModelLv1) carModelLv1 = '';
  if(!priceMin) priceMin = '';
  if(!priceMax) priceMax = '';
  if(!city) city = '';
  
  
  
  var modelViewConditionParams =  {pageIndex:pageIndex,carBrand:carBrand,carModelLv1:carModelLv1,priceMin:priceMin,priceMax:priceMax,city:city};
  
  //call service
  async.parallel(
  {
    allDicCity:function(callback){
      service.client.get("/getAllDicCity",function(err, req, res, data){
        console.log("getAllDicCity ok");
        callback(err, data);
      });
    },
    allBrand:function(callback){
      service.client.get("/getAllBrand",function(err, req, res, data){
        console.log("getAllBrand ok");
        callback(err, data);
      });
    },
    carModelViews:function(callback){
      service.client.post("/getModelView",modelViewConditionParams,function(err, req, res, data){
        console.log("getModelView ok");
        callback(err, data);
      });
    }
  },
  function(err, results){
	//render peage
	res.render("index",{allDicCity:results.allDicCity,allBrand:results.allBrand,carModelViews:results.carModelViews,conditionParams:modelViewConditionParams});
  });
};
