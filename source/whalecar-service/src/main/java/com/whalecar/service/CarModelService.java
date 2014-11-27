package com.whalecar.service;

import com.whalecar.domain.*;
import com.whalecar.persistence.CarModelMapper;
import com.whalecar.persistence.ShopMapper;
import com.whalecar.persistence.tools.PaginationResult;
import com.whalecar.persistence.tools.PaginationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * CarModel Service
 * 
 * @author ruihuang
 * 
 */
@Controller
public class CarModelService {

	Logger logger = LoggerFactory.getLogger(CarBrandSerivce.class);

	@Autowired
	private CarModelMapper carModelMapper;

	@Autowired
	private ShopMapper shopMapper;

	/**
	 * 根据brandId获取所有第1级carModel
	 * 
	 * @param conditionMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getCarModelLv1ByBrand")
	public @ResponseBody
	List<CarModelLv1> getCarModelLv1ByBrand(
			@RequestBody Map<String, Object> conditionMap) {
		return carModelMapper.queryCarModelLv1ByBrand(conditionMap);
	}

    @RequestMapping(method = RequestMethod.GET, value = "getCarModelLv1ByBrand_2")
    public @ResponseBody
    List<CarModelLv1> getCarModelLv1ByBrand_2(Integer carBrand,Integer carSubBrand) {
        List<CarModelLv1> carModelLv1List  =  carModelMapper.queryCarModelLv1ByBrandId(carBrand, carSubBrand);
		for(CarModelLv1 carModelLv1:carModelLv1List){
			Integer id = carModelLv1.getId();
			List<CarModelLv2> carModelLv2List = carModelMapper.queryCarModelLv2ByLv1Id(id);
			carModelLv1.setCarModelLv2List(carModelLv2List);
//			for( CarModelLv2 carModelLv2:carModelLv2List){
//				Integer LV2Id = carModelLv2.getId();
//			}
		}
		return carModelLv1List;
    }

	@RequestMapping(method = RequestMethod.GET, value = "getCarModelLv1ByBrand_4")
	public @ResponseBody
	List<WxCarModel> getCarModelLv1ByBrand_4(Integer carBrand,Integer carSubBrand) {
		List<WxCarModel> wxCarModelList =  carModelMapper.queryWxCarmodel(carBrand,carSubBrand);
		for(WxCarModel wxCarModel:wxCarModelList){
			List<WxLV2> Lv2List = carModelMapper.queryCarLV2Wx(wxCarModel.getLV1Id());
			wxCarModel.setWxLV2List(Lv2List);
			for(WxLV2 wxLv2: Lv2List){
				List<WxLv3> LV3List = carModelMapper.queryCarLv3Wx(wxLv2.getCarLv2Id());
				wxLv2.setWxLv3List(LV3List);
			}
		}
		wxCarModelList = processList(wxCarModelList);
		wxCarModelList = processRemoveList(wxCarModelList);
		return wxCarModelList;
	}


	private List<WxCarModel> processRemoveList(List<WxCarModel> wxCarModelList) {
		for(Iterator<WxCarModel> iterator = wxCarModelList.iterator();iterator.hasNext();){
			WxCarModel wxCarModel = iterator.next();
			if(wxCarModel.getWxLV2List().size() > 0){

			}else {
				iterator.remove();
			}
		}
		return wxCarModelList;
	}



	@RequestMapping(method = RequestMethod.GET, value = "getCarBrandName")
	public @ResponseBody
	WxCarBrandName getCarBrandName(Integer carBrand,Integer carSubBrand) {
		return carModelMapper.queryCarBrandName(carSubBrand,carBrand);
	}


	@RequestMapping(method = RequestMethod.GET, value = "getDetailCarBrandName")
	public @ResponseBody
	WxCarBrandName getDetailCarBrandName(Integer carModelLv1) {
		return carModelMapper.queryDetailCarBrandName(carModelLv1);
	}

	@RequestMapping(method = RequestMethod.GET, value = "getDetailCarSubBrandName")
	public @ResponseBody
	WxCarBrandName getDetailCarSubBrandName(Integer carModelLv1) {
		return carModelMapper.queryDetailCarSubBrandName(carModelLv1);
	}

	@RequestMapping(method = RequestMethod.GET, value = "getDetailCarType")
	public @ResponseBody
	WxCarTypeAndColor getDetailCar(Integer carModelLv1) {
		WxCarTypeAndColor wxCarTypeAndColor = new WxCarTypeAndColor();
		List<WxLV2> wxLV2List =  carModelMapper.queryCarLV2Wx(carModelLv1);
		String colorRgb = null;
		for (WxLV2 wxLV2 : wxLV2List) {
				int Lv2Id = wxLV2.getCarLv2Id();
				WxLV2 wxLV21 = carModelMapper.queryWxColorNum(Lv2Id);
				wxLV2.setColorNum(wxLV21.getColorNum());
		}
		if(wxLV2List.size() > 0){
			WxLV2 wxLV22 = wxLV2List.get(0);
			if(wxLV22 != null){
				String[] colorNum = wxLV22.getColorNum().split(",");
				for(int i = 0; i < colorNum.length; i++){
					int colorId = Integer.valueOf(colorNum[i]).intValue();
					WxLV2 wxLV3 =carModelMapper.queryWxColorRgb(colorId);
					colorRgb = wxLV3.getColorNum()+ "," +colorRgb ;
				}
				List<String> colorList = new ArrayList<String>();
				String[] colorRGB = colorRgb.split(",");
				for(int j = 0; j< colorRGB.length; j ++){
					colorList.add(colorRGB[j]);
				}
				wxCarTypeAndColor.setWxLV2List(wxLV2List);
				if(colorList.size() > 1){
					wxCarTypeAndColor.setColorList(colorList);
				}
			}
		}

		return wxCarTypeAndColor;
	}


	@RequestMapping(method = RequestMethod.GET, value = "getDetailCarPrice")
	public @ResponseBody
	List<WxCarDetailPrice> getDetailCarPrice(Integer carModelLv1) {
		WxCarDetailPrice wxDetailPrice = new WxCarDetailPrice();
		List<WxCarModelLv2ID> wxCarModelLv2IDList = carModelMapper.queryWxLV2Id(carModelLv1);
		wxDetailPrice.setWxCarModelLv2IDList(wxCarModelLv2IDList);
		for(WxCarModelLv2ID wxCarModelLv2ID: wxCarModelLv2IDList){
			List<WxCarFPrice> wxCarFPriceList = carModelMapper.queryWxFactoryPrice(wxCarModelLv2ID.getLV2Id());
			wxCarModelLv2ID.setWxCarFPriceList(wxCarFPriceList);
			for(WxCarFPrice wxCarFPrice:wxCarFPriceList){
				Integer Lv3Id = wxCarFPrice.getLv3Id();
				List<WxWebCarPrice> wxWebCarPriceList = carModelMapper.queryWxCarPrice(Lv3Id);
				wxCarFPrice.setWxWebCarPriceList(wxWebCarPriceList);
			}
		}
		List<WxCarDetailPrice> wxCarDetailPriceList = new ArrayList<>();
		wxCarDetailPriceList.add(wxDetailPrice);

		//获取市场最高最低价格
		wxCarDetailPriceList = processFPriceMax(wxCarDetailPriceList);
		wxCarDetailPriceList = processFPriceMin(wxCarDetailPriceList);
		wxCarDetailPriceList = processWebPriceMin(wxCarDetailPriceList);
		wxCarDetailPriceList = processWebPriceMax(wxCarDetailPriceList);
		return  wxCarDetailPriceList;
	}




	@RequestMapping(method = RequestMethod.GET, value = "getCarModelLv3ByBrand_2")
	public @ResponseBody
	List<CarModelLv3> returnMap(Integer carModelLv1) {
		Map result = new HashMap<String, Objects>();
		List<CarModelLv2> carModelLv2List = carModelMapper.queryCarModelLv2ByLv1Id(carModelLv1);
		List<CarModelLv3> carModelLv3List = new ArrayList<CarModelLv3>();

		for(CarModelLv2 carLv2:carModelLv2List){
			int id = carLv2.getId();
			carModelLv3List = carModelMapper.queryLv3BycarModelLv2ID(id);
		}
		return carModelLv3List;
	}



    /**
	 * 根据id查询 CarModelLv1
	 * 
	 * @param conditionMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getCarModelLv1ViewById")
	public @ResponseBody
	CarModelLv1View getCarModelLv1ViewById(
			@RequestBody Map<String, Object> conditionMap) {
		Integer carModelLv1Id = Integer.valueOf((String) conditionMap
				.get("carModelLv1Id"));
		return carModelMapper.queryCarModelLv1ViewById(carModelLv1Id);
	}

	/**
	 * 根据lv1id查询carModelLv2
	 * 
	 * @param {carModelLv1Id:value}
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getCarModelLv2ByLv1Id")
	public @ResponseBody
	List<CarModelLv2> getCarModelLv2ByLv1Id(
			@RequestBody Map<String, Object> conditionMap) {
		Integer carModelLv1Id = Integer.valueOf((String) conditionMap
				.get("carModelLv1Id"));
		return carModelMapper.queryCarModelLv2ByLv1Id(carModelLv1Id);
	}

	/**
	 * 根据lv1id查询carModelLv2WithStockView
	 * 
	 * @param {carModelLv1Id:value}
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getCarModelLv2WithStockViewByLv1Id")
	public @ResponseBody
	List<CarModelLv2WithStockView> getCarModelLv2WithStockViewByLv1Id(
			@RequestBody Map<String, Object> conditionMap) {
		// 1.根据lv1Id获取CarModelLv2的List
		Integer carModelLv1Id = Integer.valueOf((String) conditionMap
				.get("carModelLv1Id"));
        Integer city = StringUtils.isBlank((String) conditionMap.get("city"))? 0 :Integer.valueOf(String.valueOf(conditionMap
                .get("city")));
        Integer shop = StringUtils.isBlank((String) conditionMap.get("shop"))? 0 :Integer.valueOf(String.valueOf(conditionMap
                .get("shop")));
		List<CarModelLv2> carModelLv2List = carModelMapper
				.queryCarModelLv2ByLv1Id(carModelLv1Id);

		// 2.遍历CarModelLv2的List
		List<CarModelLv2WithStockView> resultList = new ArrayList<CarModelLv2WithStockView>();
		if (carModelLv2List != null && !carModelLv2List.isEmpty()) {
			for (CarModelLv2 carModelLv2 : carModelLv2List) {
				// 定义新对象，并将Lv2的值copy过去
				CarModelLv2WithStockView carModelLv2WithStockView = new CarModelLv2WithStockView();
				BeanUtils.copyProperties(carModelLv2, carModelLv2WithStockView);
				// 根据lv2Id查询下面的ShopStock
				List<ShopStockView> shopStockViewList = shopMapper
						.queryShopStockViewByCarModelLv2(carModelLv2.getId(),city,shop);
				// 计算金额最大最小值和库存总数
				BigDecimal factoryPriceMin = null;
				BigDecimal factoryPriceMax = null;
				BigDecimal carPriceMin = null;
				BigDecimal carPriceMax = null;
				Map<String,DicColor> outsideColorMap = new HashMap<String,DicColor>();
				Set<String> insideColorSet = new HashSet<String>();
				Set<String> carModelLv3NameSet = new HashSet<String>();
				// 遍历shopStockViewList,开始计算
				for (int i = 0; i < shopStockViewList.size(); i++) {
                    carModelLv2WithStockView.setPriceOff(shopStockViewList.get(i).getPriceOff());
					if (i == 0) {
						factoryPriceMin = shopStockViewList.get(i)
								.getFactoryPrice();
						factoryPriceMax = shopStockViewList.get(i)
								.getFactoryPrice();
						carPriceMin = shopStockViewList.get(i).getCarPrice();
						carPriceMax = shopStockViewList.get(i).getCarPrice();
					} else {
						// 当前最小的出厂价格 大于 i位置的价格
						if (factoryPriceMin.compareTo(shopStockViewList.get(i)
								.getFactoryPrice()) == 1) {
							factoryPriceMin = shopStockViewList.get(i)
									.getFactoryPrice();
						}
						// 当前最大的出厂价格 小于 i位置的价格
						if (factoryPriceMax.compareTo(shopStockViewList.get(i)
								.getFactoryPrice()) == -1) {
							factoryPriceMax = shopStockViewList.get(i)
									.getFactoryPrice();
						}
						// 当前最小的商店价格 大于 i位置的价格
						if (carPriceMin.compareTo(shopStockViewList.get(i)
								.getCarPrice()) == 1) {
							carPriceMin = shopStockViewList.get(i)
									.getCarPrice();
						}
						// 当前最大的商店价格 小于 i位置的价格
						if (carPriceMax.compareTo(shopStockViewList.get(i)
								.getCarPrice()) == -1) {
							carPriceMax = shopStockViewList.get(i)
									.getCarPrice();
						}
					}
					// 遍历过程汇集所有可能的颜色和lv3车型集合
                    outsideColorMap.put(shopStockViewList.get(i).getCarOutsideColorRgb(),new DicColor(shopStockViewList.get(i)
							.getCarOutsideColorName(),shopStockViewList.get(i).getCarOutsideColorRgb()));
                    insideColorSet.add(shopStockViewList.get(i)
                            .getCarInsideColorName());
					carModelLv3NameSet.add(shopStockViewList.get(i)
							.getCarModelLv3ShortName());
				}
				// 计算完成，开始赋值
				carModelLv2WithStockView.setFactoryPriceMin(factoryPriceMin);
				carModelLv2WithStockView.setFactoryPriceMax(factoryPriceMax);
				carModelLv2WithStockView.setCarPriceMin(carPriceMin);
				carModelLv2WithStockView.setCarPriceMax(carPriceMax);
				carModelLv2WithStockView
						.setStockCount(shopStockViewList.size());
				carModelLv2WithStockView.setShopStockList(shopStockViewList);
				// 汇集所有可能的颜色和lv3车型集合
				carModelLv2WithStockView.getOutsideColorList().addAll(
                        outsideColorMap.values());
                carModelLv2WithStockView.getInsideColorList().addAll(
                        insideColorSet);
				carModelLv2WithStockView.getCarModelLv3NameList().addAll(
						carModelLv3NameSet);
				// 将包装好的对象放入新List，做为返回List
				resultList.add(carModelLv2WithStockView);
			}
		}

		// 3.返回
		return resultList;
	}

	/**
	 * 根据lv2id查询carModelLv3
	 * 
	 * @param {carModelLv2Id:value}
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getCarModelLv3ByLv2Id")
	public @ResponseBody
	List<CarModelLv3> getCarModelLv3ByLv2Id(
			@RequestBody Map<String, Object> conditionMap) {
		Integer carModelLv2Id = Integer.valueOf((String) conditionMap
				.get("carModelLv2Id"));
		return carModelMapper.queryCarModelLv3ByLv2Id(carModelLv2Id);
	}

	/**
	 * 分页查询CarModelView对象
	 * 
	 * @param conditionMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getModelView")
	public @ResponseBody
	PaginationResult<CarModelView> getModelView(
			@RequestBody Map<String, Object> conditionMap) {

		// init pageIndex
		int pageIndex = Integer.valueOf(String.valueOf(conditionMap
				.get("pageIndex")));

		// init pageSize
		int pageSize = PaginationUtils.DEFAULT_PAGESIZE;// default pagesize
		if (conditionMap.get("pageSize") != null) {
			pageSize = (int) conditionMap.get("pageSize");
		}

		// convert pagesize to recordIndex
		conditionMap.put("startIndex",
				PaginationUtils.getStartIndex(pageIndex, pageSize));
		conditionMap.put("pageSize", pageSize);

		// query db
		int resultCount = carModelMapper.queryModelViewCount(conditionMap);
		List<CarModelView> resultList = null;
		if (resultCount != 0) {
			resultList = carModelMapper.queryModelView(conditionMap);
		} else {
			resultList = new ArrayList<CarModelView>();
		}

		// fill PaginationResult
		PaginationResult<CarModelView> result = new PaginationResult<CarModelView>(
				resultList, resultCount, pageSize,
				PaginationUtils.getStartIndex(pageIndex, pageSize));

		return result;
	}

    /**
     * 根据carModelLv1Id查询carModelImg
     * @param carModelLv1
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "queryCarModelLv1ImgById")
    public @ResponseBody List<CarModelLv1Image> queryCarModelLv1ImgById(Integer carModelLv1){
        return carModelMapper.queryCarModelLv1ImgById(carModelLv1);
    }


    /**
     * 根据价格查询相关车型
     * @param price
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value ="/getSimilarCarModelLv1ByPrice")
    public @ResponseBody List<CarModelLv1> getSimilarCarModelLv1ByPrice(BigDecimal price){
        return carModelMapper.querySimilarCarModelLv1ByPrice(price);
    }


    /**
     * 根据shop id查询shop 下的车型以及库存
     * @param shopId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value="/getIncludeCarModelLv1ByShop")
    public @ResponseBody List<CarModelLv1View> getIncludeCarModelLv1ByShop(Integer shopId){
        return carModelMapper.queryIncludeCarModelLv1ByShop(shopId);
    }

    /**
     * 查询优惠车型信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value="/getPriceOffCarModelLv1")
    public @ResponseBody List<CarModelLv1View> getPriceOffCarModelLv1(){
        return carModelMapper.queryPriceOffCarModelLv1();
    }


    /**
     * 根据Car_Model_Lv1的id查找Car_Brand
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/getCarBrand")
    public @ResponseBody
    CarBrand getCarBrand(Integer id){
        CarModelLv1 cml1 = carModelMapper.queryCarModelLv1ById(id);
        return carModelMapper.queryCarBrandById(cml1.getCarBrand());
    }

    /**
     * 查询CarModelLv1中的id和cname
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value="/queryCarBrandAndIdAndCname")
    public @ResponseBody List<CarBrand> queryCarBrandAndIdAndCname(){
        List<CarBrand> carBrandList = carModelMapper.queryAllCarBrand();
        for(CarBrand c:carBrandList){
            c.setCarModelLv1List(carModelMapper.queryIdAndCname(c.getId()));
        }
        return carBrandList;
    }

    /**
     * 查询所有carBrand
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value="/queryAllCarBrand")
    public @ResponseBody List<CarBrand> queryAllCarBrand(){
        return carModelMapper.queryAllCarBrand();
    }


    /**
     * 根据carModelLv1Id查询carModellv2
     * @param carModelLv1
     * @return
     */
	@RequestMapping(method = RequestMethod.GET, value = "/queryCarModel")
    public @ResponseBody List queryCarModel(Integer carModelLv1){
        List<CarModelLv2> returnList = new ArrayList();
        List<CarModelLv2> lv2List =  new ArrayList();
        List<CarModelLv3> lv3List =  new ArrayList();
        lv2List = carModelMapper.queryLv2BycarModelLv1(carModelLv1);
        for (int i = 0; i < lv2List.size() ;i++){
            CarModelLv2 modelLv2 = lv2List.get(i);
            int lv2Id = modelLv2.getId();
            List<CarModelLv3> newList = carModelMapper.queryLv3BycarModelLv2(lv2Id);
            CarModelLv2 newLv2 = new CarModelLv2();

            newLv2.setId(modelLv2.getId());
            newLv2.setCarModelLv1(modelLv2.getCarModelLv1());
            newLv2.setCarModelLv1Name(modelLv2.getCarModelLv1Name());
            newLv2.setShortName(modelLv2.getShortName());
            newLv2.setFullName(modelLv2.getFullName());
            newLv2.setOrderIndex(modelLv2.getOrderIndex());
            newLv2.setFlagUseable(modelLv2.getFlagUseable());
            newLv2.setCarModelLv3List(newList);
            returnList.add(newLv2);
        }
        return returnList;
    }


	private List<WxCarDetailPrice> processWebPriceMax(List<WxCarDetailPrice> wxCarDetailPriceList) {
		Integer price = 0;
		for(WxCarDetailPrice wxCarDetailPrice:wxCarDetailPriceList)
		{
			List<WxCarModelLv2ID> wxCarModelLv2IDList = wxCarDetailPrice.getWxCarModelLv2IDList();
			for(WxCarModelLv2ID wxCarModelLv2ID: wxCarModelLv2IDList){
				List<WxCarFPrice> wxCarFPriceList = wxCarModelLv2ID.getWxCarFPriceList();
				for(WxCarFPrice wxCarFPrice: wxCarFPriceList){
					List<WxWebCarPrice> wxWebCarPriceList = wxCarFPrice.getWxWebCarPriceList();
					for(WxWebCarPrice wxWebCarPrice:wxWebCarPriceList){
						if(price < wxWebCarPrice.getWebPrice() && wxWebCarPrice.getWebPrice() != 0 && wxWebCarPrice.getWebPrice() != null){
							price = wxWebCarPrice.getWebPrice();
						}else {
							price = 0;
						}
					}
				}
			}
			wxCarDetailPrice.setWebPriceMax(price);
		}
		return wxCarDetailPriceList;
	}


	private List<WxCarDetailPrice> processWebPriceMin(List<WxCarDetailPrice> wxCarDetailPriceList) {
		Integer price = 100000;
		for(WxCarDetailPrice wxCarDetailPrice:wxCarDetailPriceList)
		{
			List<WxCarModelLv2ID> wxCarModelLv2IDList = wxCarDetailPrice.getWxCarModelLv2IDList();
			if(wxCarModelLv2IDList != null){
				for(WxCarModelLv2ID wxCarModelLv2ID: wxCarModelLv2IDList){
					List<WxCarFPrice> wxCarFPriceList = wxCarModelLv2ID.getWxCarFPriceList();
					if(wxCarFPriceList != null){
						for(WxCarFPrice wxCarFPrice: wxCarFPriceList){
							List<WxWebCarPrice> wxWebCarPriceList = wxCarFPrice.getWxWebCarPriceList();
							if(wxWebCarPriceList.size() > 0){
								for(WxWebCarPrice wxWebCarPrice:wxWebCarPriceList){
									if(price > wxWebCarPrice.getWebPrice()){
										price = wxWebCarPrice.getWebPrice();
									}
								}
							}
						}
					}else {
						price = 0;
					}
				}
			}else {
				price = 0;

			}
			wxCarDetailPrice.setWebPriceMin(price);
		}
		return wxCarDetailPriceList;
	}


	private List<WxCarDetailPrice> processFPriceMin(List<WxCarDetailPrice> wxCarDetailPriceList) {
		Integer price = 100000;
		for(WxCarDetailPrice wxCarDetailPrice:wxCarDetailPriceList)
		{
			List<WxCarModelLv2ID> wxCarModelLv2IDList = wxCarDetailPrice.getWxCarModelLv2IDList();
			if(wxCarModelLv2IDList !=null){
				for(WxCarModelLv2ID wxCarModelLv2ID: wxCarModelLv2IDList){
					List<WxCarFPrice> wxCarFPriceList = wxCarModelLv2ID.getWxCarFPriceList();
					if(wxCarFPriceList !=null){
						for(WxCarFPrice wxCarFPrice: wxCarFPriceList){
							if(price > wxCarFPrice.getFactoryPrice()){
								price = wxCarFPrice.getFactoryPrice();
							}
						}
					}else {
						price	= 0 ;
					}
				}
			}else {
				price = 0 ;
			}
			wxCarDetailPrice.setFactoryPriceMin(price);
		}
		return wxCarDetailPriceList;
	}

	private List<WxCarDetailPrice> processFPriceMax(List<WxCarDetailPrice> wxCarDetailPriceList) {
		Integer price = 0;
		for(WxCarDetailPrice wxCarDetailPrice:wxCarDetailPriceList)
		{
			List<WxCarModelLv2ID> wxCarModelLv2IDList = wxCarDetailPrice.getWxCarModelLv2IDList();
			for(WxCarModelLv2ID wxCarModelLv2ID: wxCarModelLv2IDList){
				List<WxCarFPrice> wxCarFPriceList = wxCarModelLv2ID.getWxCarFPriceList();
				for(WxCarFPrice wxCarFPrice: wxCarFPriceList){
					if(price < wxCarFPrice.getFactoryPrice() && wxCarFPrice.getFactoryPrice() != 0 && wxCarFPrice.getFactoryPrice() != null){
						price = wxCarFPrice.getFactoryPrice();
					}else {
						price = 0;
					}
				}
			}
			wxCarDetailPrice.setFactoryPriceMax(price);
		}
		return wxCarDetailPriceList;
	}



	private List<WxCarModel> processList(List<WxCarModel> wxCarModelList) {
		Integer price = null;
		for(WxCarModel wxCarModel:wxCarModelList)
		{
			price = 10000;
			List<WxLV2> wxLv2List = wxCarModel.getWxLV2List();
			if(wxLv2List !=null){
				for(WxLV2 wxLV2:wxLv2List){
					List<WxLv3> wxLv3List = wxLV2.getWxLv3List();
					if(wxLv3List != null){
						for(WxLv3 wxLv3:wxLv3List){
							if(price > wxLv3.getPrice()){
								price = wxLv3.getPrice();
							}
						}
					}else {
						price = 0;
					}
				}
			}else {
				price = 0;
			}
			wxCarModel.setWxCarPrice(price);
		}
		return wxCarModelList;
	}



}