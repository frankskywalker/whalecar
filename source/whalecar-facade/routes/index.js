/*
 * GET home page.
 */
var service = require("./tools/service-header");
var async = require("async");

//index page
exports.index = function(req, res) {
  
  //set Param
  var modelViewConditionParams =  {pageIndex:0};
  
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
	res.render("index",{allDicCity:results.allDicCity,allBrand:results.allBrand,carModelViews:results.carModelViews});
  });
};

//Get CarModel By BrandId
exports.getCarModelByBrandId = function(req,res){
	service.client.post("/getCarModelLv1ByBrandId",{brandId:req.params.brandId},function(err, req, res, data){
        console.log("getCarModelLv1ByBrandId ok");
        res.send(data);
    });
};
