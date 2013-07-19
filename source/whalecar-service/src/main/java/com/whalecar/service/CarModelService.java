package com.whalecar.service;

import java.util.ArrayList;
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
	@RequestMapping(method=RequestMethod.POST,value="getCarModelLv1ByBrandId")
	public @ResponseBody List<CarModelLv1> getCarModelLv1ByBrandId(@RequestBody Map<String,Object> conditionMap){
		Integer brandId = Integer.valueOf((String)conditionMap.get("brandId"));
		return carModelMapper.queryCarModelLv1ByBrandId(brandId);
	}

	/**
	 * 分页查询CarModelView对象
	 * @param conditionMap 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="getModelView")
	public @ResponseBody PaginationResult<CarModelView> getModelView(@RequestBody Map<String,Object> conditionMap){
		
		//init pageIndex
		int pageIndex = Integer.valueOf(String.valueOf(conditionMap.get("pageIndex")));
		
		//init pageSize
		int pageSize = PaginationUtils.DEFAULT_PAGESIZE;//default pagesize
		if(conditionMap.get("pageSize") != null){
			pageSize =  (int) conditionMap.get("pageSize");
		}
		
		//convert pagesize to recordIndex
		conditionMap.put("startIndex", PaginationUtils.getStartIndex(pageIndex, pageSize));
		conditionMap.put("pageSize", pageSize);
		
		//query db
		int resultCount = carModelMapper.queryModelViewCount(conditionMap);
		List<CarModelView> resultList = null;
		if(resultCount != 0){
			resultList = carModelMapper.queryModelView(conditionMap);
		}
		else{
			resultList = new ArrayList<CarModelView>();
		}
		
		//fill PaginationResult
		PaginationResult<CarModelView> result = new PaginationResult<CarModelView>(resultList,resultCount,PaginationUtils.getStartIndex(pageIndex, pageSize));
		
		return result;
	}
}
