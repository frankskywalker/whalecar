package com.whalecar.persistence;

import com.whalecar.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 汽车型号相关 Table = Car_Model_**
 * 
 * @author ruihuang
 * 
 */
public interface CarModelMapper {

	public List<CarModelLv1> queryCarModelLv1ByBrand(
			Map<String, Object> conditionMap);

	/**
	 * 分页查询ModelView
	 * 
	 * @param conditionMap
	 * @return
	 */
	public List<CarModelView> queryModelView(Map<String, Object> conditionMap);

	/**
	 * 查询ModelView总数
	 * 
	 * @param conditionMap
	 * @return
	 */
	public int queryModelViewCount(Map<String, Object> conditionMap);

	/**
	 * 根据id查询 CarModelLv1
	 * 
	 * @param id
	 * @return
	 */
	public CarModelLv1View queryCarModelLv1ViewById(Integer id);

	/**
	 * 根据lv1id查询carModelLv2
	 * 
	 * @param lv1Id
	 * @return
	 */
	public List<CarModelLv2> queryCarModelLv2ByLv1Id(Integer lv1Id);

	/**
	 * 根据lv2id查询carModelLv3
	 * 
	 * @param lv2Id
	 * @return
	 */
	public List<CarModelLv3> queryCarModelLv3ByLv2Id(Integer lv2Id);

    /**
     * 根据carModelLv1查询Image
     * @param carModelLv1
     * @return
     */
    public List<CarModelLv1Image> queryCarModelLv1ImgById(Integer carModelLv1);

    /**
     * 根据价格差价查询相关车型
     * @param price
     * @return
     */
    public List<CarModelLv1> querySimilarCarModelLv1ByPrice(BigDecimal price);

    /**
     * 根据shop查询包含的车型
     * @param shopId
     * @return
     */
    public List<CarModelLv1View> queryIncludeCarModelLv1ByShop(Integer shopId);

    /**
     * 查询特惠车型
     * @return
     */
    public List<CarModelLv1View> queryPriceOffCarModelLv1();
}
