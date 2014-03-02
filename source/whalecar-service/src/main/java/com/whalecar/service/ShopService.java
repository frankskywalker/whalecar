package com.whalecar.service;

import com.whalecar.domain.*;
import com.whalecar.persistence.CarModelMapper;
import com.whalecar.persistence.Golf7AdvMapper;
import com.whalecar.persistence.ShopMapper;
import com.whalecar.persistence.tools.PaginationResult;
import com.whalecar.persistence.tools.PaginationUtils;
import com.whalecar.service.tools.BooleanResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CarModelMapper carModelMapper;
    @Autowired
    private Golf7AdvMapper golf7AdvMapper;

    /**
     * 根据用户名密码查询Shop信息 如果没有查询到，说明用户名或密码错误
     *
     * @param conditionMap
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
        Integer city = StringUtils.isBlank((String)conditionMap.get("city"))? 0 :Integer.valueOf(String.valueOf(conditionMap
                .get("city")));
        Integer shop = StringUtils.isBlank((String)conditionMap.get("shop"))? 0 :Integer.valueOf(String.valueOf(conditionMap
                .get("shop")));
		return shopMapper.queryShopStockViewByCarModelLv2(carModelLv2Id,city,shop);
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
		if(shopStock.get("id") == null || StringUtils.isBlank(String.valueOf(shopStock.get("id")))) {
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
     * @param shop
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

    /**
     * 根据id查询ShopStockView列表
     *
     * @param shopStockondition 结构：{id:@shopStockId}
     * @return shopStockView列表
     */
    @RequestMapping(method = RequestMethod.POST,value="/getShopStockViewById")
    public @ResponseBody
    ShopStockView getShopStockViewById(@RequestBody Map<String, Object> shopStockondition){
        int id = Integer.valueOf(String.valueOf(shopStockondition
                .get("id")));
        return shopMapper.queryShopStockViewById(id);
    }

    /**
     * 根据条件分页查询ShopView
     *
     * @param shopCondition
     * @return
     */
	@RequestMapping(method = RequestMethod.POST, value = "/getShopView")
	public @ResponseBody
	PaginationResult<ShopView> getShopView(
			@RequestBody Map<String, Object> shopCondition) {

		// init pageIndex
		int pageIndex = Integer.valueOf(String.valueOf(shopCondition
				.get("pageIndex")));

		// init pageSize
		int pageSize = PaginationUtils.DEFAULT_PAGESIZE;// default pagesize
		if (shopCondition.get("pageSize") != null) {
			pageSize = (int) shopCondition.get("pageSize");
		}

		// convert pagesize to recordIndex
		shopCondition.put("startIndex",
				PaginationUtils.getStartIndex(pageIndex, pageSize));
		shopCondition.put("pageSize", pageSize);

		// 查询db
		// 查询count
		int resultCount = shopMapper.queryShopCount(shopCondition);

		// 填充shopstock,组装 shopViewList
		List<ShopView> shopViewList = new ArrayList<ShopView>();

		// 查询shop列表
		List<Shop> shopList = shopMapper.queryShop(shopCondition);
		for (Shop shop : shopList) {
			ShopView shopView = new ShopView();
			BeanUtils.copyProperties(shop, shopView);
			shopView.setShopStockViewList(shopMapper
					.queryShopStockViewByShop(shop.getId()));
			shopViewList.add(shopView);
		}

        //查询carModelLv1列表
        for (ShopView shopView : shopViewList) {
            List<CarModelLv1View> carModelLv1Views = carModelMapper.queryIncludeCarModelLv1ByShop(shopView.getId());
            shopView.setCarModelLv1(carModelLv1Views);
        }

		// fill PaginationResult
		PaginationResult<ShopView> result = new PaginationResult<ShopView>(
				shopViewList, resultCount, pageSize,
				PaginationUtils.getStartIndex(pageIndex, pageSize));

		return result;
	}

    /**
     * 插入golf7活动数据
     *
     * @param golf7Adv
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveGolf7Adv")
    public @ResponseBody
    BooleanResult saveGolf7Adv(@RequestBody Map<String, String> golf7Adv){
        if(golf7AdvMapper.insert(golf7Adv) == 1){
            return new BooleanResult(true);
        }
        else{
            return new BooleanResult(false);
        }
    }
}
