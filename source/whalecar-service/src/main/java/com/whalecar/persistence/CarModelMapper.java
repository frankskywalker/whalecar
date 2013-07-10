package com.whalecar.persistence;

import java.util.List;
import java.util.Map;

import com.whalecar.domain.CarModelLv1;
import com.whalecar.domain.CarModelView;

public interface CarModelMapper {
	
	public List<CarModelLv1> queryCarModelLv1ByBrandId(int brandid);
	
	/**
	 * 分页查询ModelView
	 * @param conditionMap 
	 * @return
	 */
	public List<CarModelView> queryModelView(Map<String,Object> conditionMap);
	
	/**
	 * 查询ModelView总数
	 * @param conditionMap 
	 * @return
	 */
	public int queryModelViewCount(Map<String,Object> conditionMap);
}
