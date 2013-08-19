package com.whalecar.persistence;

import com.whalecar.domain.Shop;
import com.whalecar.domain.ShopStockView;

import java.util.List;
import java.util.Map;

/**
 * 4s店相关 Table = Shop & Shop_Stock
 * 
 * @author ruihuang
 * 
 */
public interface ShopMapper {

	/**
	 * 根据Car_model_Lv2的id,查询ShopStockView列表
	 * 
	 * @param carModelLv2Id
	 * @return
	 */
	public List<ShopStockView> queryShopStockViewByCarModelLv2(
			Integer carModelLv2Id);

	/**
	 * 根据ShopId查询ShopStock
	 * 
	 * @param shopId
	 * @return
	 */
	public List<ShopStockView> queryShopStockViewByShop(Integer shopId);

	/**
	 * 存储ShopStock
	 * 
	 * @param shopStock
	 * @return
	 */
	public int saveShopStock(Map<String, Object> shopStock);

	/**
	 * 更新ShopStock
	 * 
	 * @param shopStock
	 * @return
	 */
	public int updateShopStock(Map<String, Object> shopStock);

	/**
	 * 根据id删除ShopStock
	 * 
	 * @param shopStockId
	 * @return
	 */
	public int deleteShopStockById(Integer shopStockId);

	/**
	 * 根据id查询Shop信息
	 * 
	 * @param shopId
	 * @return
	 */
	public Shop queryShopById(Integer shopId);

	/**
	 * 根据用户名密码查询Shop信息 如果没有查询到，说明用户名或密码错误
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public Shop queryByNameAndPsw(Map<String, Object> conditionMap);

	/**
	 * 更新shop对象
	 * 
	 * @param shop
	 * @return
	 */
	public int updateShop(Map<String, Object> shop);

	/**
	 * 分页查询Shop
	 */
	public List<Shop> queryShop(Map<String, Object> shopCondition);

	/**
	 * 分页查询Shop，求Count
	 * 
	 * @return
	 */
	public int queryShopCount(Map<String, Object> shopCondition);
}
