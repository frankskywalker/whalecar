var service = require("./tools/service-header");

exports.query = function(req,res){
  var type = req.query.type;
  if(type == 'CarModelLv1'){
    getCarModelLv1ByBrandId(req,res);
  }
  else if(type == 'DicCity'){
	getDicCity(req,res);
  }
  else{
    req.send("{error:'type is not funod!!'}");
  }
};

//Get CarModel By BrandId
function getCarModelLv1ByBrandId(request,response){
  service.client.post("/getCarModelLv1ByBrandId",{brandId:req.query.brandId},function(err, req, res, data){
	if(err){
      console.log("getCarModelLv1ByBrandId err");
      next(err);
	}
	else{
      console.log("getCarModelLv1ByBrandId ok");
      response.send(data);
	}
  });
}

function getDicCity(request,response){
  service.client.get("/getAllDicCity",function(err, req, res, data){
    if(err){
      console.log("getAllDicCity err");
      next(err);
    }
    else{
      console.log("getAllDicCity ok");
      response.send(data);
    }
  });
}