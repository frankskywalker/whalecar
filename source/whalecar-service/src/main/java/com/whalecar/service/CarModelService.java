package com.whalecar.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.CarModelLv1;
import com.whalecar.domain.CarModelView;
import com.whalecar.persistence.CarModelMapper;
import com.whalecar.persistence.tools.PaginationResult;
import com.whalecar.persistence.tools.PaginationUtils;

@Controller
public class CarModelService {
	
	Logger logger = LoggerFactory.getLogger(CarBrandSerivce.class);
	
	@Autowired
	CarModelMapper carModelMapper;
	
	/**
	 * 根据brandId获取所有第1级carModel
	 * @param brandid
	 * @return
	 */
	@RequestMapping("getCarModelLv1ByBrandId")
	public @ResponseBody List<CarModelLv1> getCarModelLv1ByBrandId(int brandId){
		return carModelMapper.queryCarModelLv1ByBrandId(brandId);
	}

	/**
	 * 分页查询CarModelView对象
	 * @param conditionMap 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody PaginationResult<CarModelView> getModelView(@RequestBody Map<String,Object> conditionMap){
		
		//init pageIndex
		int pageIndex = (int) conditionMap.get("pageIndex");
		
		//init pageSize
		int pageSize = PaginationUtils.DEFAULT_PAGESIZE;//default pagesize
		if(conditionMap.get("pageSize") != null){
			pageSize =  (int) conditionMap.get("pageSize");
		}
		
		//convert pagesize to recordIndex
		conditionMap.put("startIndex", PaginationUtils.getStartIndex(pageIndex, pageSize));
		conditionMap.put("pageSize", pageSize);
		
		//query db
		List<CarModelView> resultList = carModelMapper.queryModelView(conditionMap);
		int resultCount = carModelMapper.queryModelViewCount(conditionMap);
		
		//fill PaginationResult
		PaginationResult<CarModelView> result = new PaginationResult<CarModelView>(resultList,resultCount,pageIndex * pageSize);
		
		return result;
	}
}
