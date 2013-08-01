package com.whalecar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.Shop;
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
	 * 根据用户名密码查询Shop信息 如果没有查询到，说明用户名或密码错误
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getShopByNameAndPsw")
	public @ResponseBody
	Shop getShopByNameAndPsw(@RequestBody Map<String, Object> conditionMap) {
		return shopMapper.queryByNameAndPsw(conditionMap);
	}

	/**
	 * 根据carModelLv2Id查询ShopStockView
	 * 
	 * @param conditionMap
	 *            ｛carModelLv2Id:value｝
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getShopStockByCarModelLv2")
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
	@RequestMapping(method = RequestMethod.POST, value = "/saveOrUpdateShopStock")
	public @ResponseBody
	boolean saveOrUpdateShopStock(@RequestBody Map<String, Object> shopStock) {
		int result;
		if (shopStock.get("id") == null) {
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

	/**
	 * 删除ShopStock
	 * 
	 * @param shopStock
	 *            {id:value}
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/delShopStock")
	public @ResponseBody
	boolean delShopStock(@RequestBody Map<String, Object> shopStock) {
		Integer shopStockId = Integer.valueOf(String.valueOf(shopStock
				.get("id")));
		int result = shopMapper.deleteShopStockById(shopStockId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据Id查询Shop
	 * 
	 * @param shop
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getShopById")
	public @ResponseBody
	Shop getShopById(@RequestBody Map<String, Object> shop) {
		Integer shopId = Integer.valueOf(String.valueOf(shop.get("id")));
		return shopMapper.queryShopById(shopId);
	}

	/**
	 * 根据ShopId查询ShopStock
	 * 
	 * @param shopId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getShopStockViewByShop")
	public @ResponseBody
	List<ShopStockView> getShopStockViewByShop(
			@RequestBody Map<String, Object> shop) {
		Integer shopId = Integer.valueOf(String.valueOf(shop.get("id")));
		return shopMapper.queryShopStockViewByShop(shopId);
	}

	/**
	 * 更新shop对象
	 * 
	 * @param shop
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/updateShop")
	public @ResponseBody
	boolean updateShop(@RequestBody Map<String, Object> shop) {
		int updateCount = shopMapper.updateShop(shop);
		if (updateCount == 1) {
			return true;
		} else {
			return false;
		}
	}

}
