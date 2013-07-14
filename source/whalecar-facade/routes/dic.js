

exports.query = function(req,res){
  var type = req.query.type;
  if(type == 'CarModelLv1'){
    getCarModelLv1ByBrandId(req,res);
  }
  else{
    req.send("{error:'type is not funod!!'}");
  }
};

//Get CarModel By BrandId
function getCarModelLv1ByBrandId(req,res){
	service.client.post("/getCarModelLv1ByBrandId",{brandId:req.query.brandId},function(err, req, res, data){
        console.log("getCarModelLv1ByBrandId ok");
        res.send(data);
    });
}