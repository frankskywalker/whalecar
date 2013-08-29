package com.whalecar.persistence;

import com.whalecar.domain.*;

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

}
