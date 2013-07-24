package com.whalecar.persistence;

import java.util.List;
import java.util.Map;

import com.whalecar.domain.ShopStockView;

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
}
