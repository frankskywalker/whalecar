/**
 * User: ruihuang
 * Date: 13-8-22
 * Time: 上午2:20
 */

var service = require("./tools/service-header");
var async = require("async");

exports.submitprice = function(req,res,next){
    var type =  req.query.type;
    //type==init:订单初始确认页面
    if(type == "init"){
        var stockId = req.query.id;
        service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,sData){
           console.log(sData);
           res.render("user_submit_price",{shopStockView:sData});
        });
    }
    //type==create:订单保存，并进入订单review页面
    else if (type == "create"){
        var stockId = req.body.id;
        async.waterfall([
            //1.先根据stockId查询shopStockView
            function(callback){
                service.client.post("/getShopStockViewById",{id:stockId},function(sError,sReq,sRes,shopStockView){
                    callback(sError,shopStockView);
                });
            },
            //2.根据shopStockView来组装userSubmitPrice
            function(shopStockView,callback){
                var userPrice = req.body.userPrice;
                var userSubmitPrice = {
                    userPrice : userPrice,
                    originalPrice:shopStockView.carPrice,
                    shopStock : shopStockView.id,
                    shop : shopStockView.shop,
                    user : req.session.currentUser.id
                };
                service.client.post("/saveUserSubmitPrice",userSubmitPrice,function(sError,sReq,sRes,sData){
                    callback(sError,{});
                });
            }
        ],function(err, result){
            if(err){
                next(err);
            }
            res.render("user_submit_price_finish");
        });
    }
}

exports.changeUserSubmitPriceState =  function(req,res,next){
    var id = req.body.id;
    var state = req.body.state;
    var shopPrice = req.body.shopPrice;

    //验证id和state参数合法
    if(!id || !state){
        res.json({changeResult:false});
        return;
    }

    var params = {};

    //多种情况判断:
    //1. 4s电提出议价
    if(state == "shop_commit"){
        //1.验证当前用户是否为可操作的shopid
        // TODO 验证代码
        //2.验证价格是否存在
        if(!shopPrice){
            res.json({changeResult:false});
            return;
        }
        params = {id : id,state:state,shopPrice:shopPrice };
    }
    //2. 4s店确认价格，生成订单
    else if(state == "shop_agree"){
        //1.验证当前用户是否为可操作的shopid
        // TODO 验证代码
        params = {id : id,state:state};
    }
    //3. 用户同意4s店议价，生成订单
    else if(state == "price_success"){
        //1.验证当前用户是否为可操作的userid
        // TODO 验证代码
        //2.保存价格，并跳转到价格页面
        params = {id : id,state:state};
    }
    //4. 用户不同意4s店议价，结束订单
    else if(state == "price_fail"){
        //1.验证当前用户是否为可操作的shopid
        // TODO 验证代码
        params = {id : id,state:state};
    }
    else {
        res.json({changeResult:false});
        return ;
    }

    service.client.post("/changeUserSubmitPriceState",params,function(sError){
        if(sError){
            console.error(sError);
            res.json({changeResult:false});
        }
        else{
            res.json({changeResult:true});
        }
    });
}
