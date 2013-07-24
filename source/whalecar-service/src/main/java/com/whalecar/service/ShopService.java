package com.whalecar.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.ShopStock;
import com.whalecar.domain.ShopStockView;
import com.whalecar.persistence.ShopMapper;

/**
 * Shop service
 * 
 * @author ruihuang
 * 
 */
@Controller
public class ShopService {

	@Autowired
	private ShopMapper shopMapper;

	/**
	 * 根据carModelLv2Id查询ShopStockView
	 * 
	 * @param conditionMap
	 *            ｛carModelLv2Id:value｝
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getShopStockByCarModelLv2")
	public @ResponseBody
	List<ShopStockView> getShopStockByCarModelLv2(
			@RequestBody Map<String, Object> conditionMap) {
		Integer carModelLv2Id = Integer.valueOf(String.valueOf(conditionMap
				.get("carModelLv2Id")));
		return shopMapper.queryShopStockViewByCarModelLv2(carModelLv2Id);
	}

	/**
	 * 更新或者添加ShopStock，如果id为空：save操作，id不为空：update操作
	 * 
	 * @param shopStock
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getShopStockByCarModelLv2")
	public @ResponseBody
	boolean saveOrUpdateShopStock(@RequestBody Map<String, Object> shopStock) {
		String shopStockId = String.valueOf(shopStock.get("id"));
		int result;
		if (StringUtils.isBlank(shopStockId)) {
			result = shopMapper.saveShopStock(shopStock);
		} else {
			result = shopMapper.updateShopStock(shopStock);
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
}
