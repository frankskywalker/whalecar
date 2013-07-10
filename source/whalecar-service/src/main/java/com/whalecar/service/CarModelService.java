package com.whalecar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.CarModelLv1;
import com.whalecar.domain.CarModelView;
import com.whalecar.persistence.CarModelMapper;
import com.whalecar.persistence.tool.PaginationResult;

@Controller
public class CarModelService {
	
	@Autowired
	CarModelMapper carModelMapper;
	
	@RequestMapping("getCarModelLv1ByBrandId")
	public @ResponseBody List<CarModelLv1> getCarModelLv1ByBrandId(int brandid){
		return carModelMapper.queryCarModelLv1ByBrandId(brandid);
	}

	/**
	 * 分页查询CarModelView对象
	 * @param conditionMap 
	 * @return
	 */
	@RequestMapping("getModelView")
	public @ResponseBody PaginationResult<CarModelView> getModelView(Map<String,Object> conditionMap,int pageIndex,int pageSize){
		if(conditionMap == null){
			conditionMap = new HashMap<String,Object>();
		}
		conditionMap.put("startIndex", pageIndex * pageSize);
		conditionMap.put("endIndex", (pageIndex + 1)  * pageSize - 1);
		List<CarModelView> resultList = carModelMapper.queryModelView(conditionMap);
		int resultCount = carModelMapper.queryModelViewCount(conditionMap);
		PaginationResult<CarModelView> result = new PaginationResult<CarModelView>(resultList,resultCount,pageIndex * pageSize);
		return result;
	}
}
