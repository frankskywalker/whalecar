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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

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

}