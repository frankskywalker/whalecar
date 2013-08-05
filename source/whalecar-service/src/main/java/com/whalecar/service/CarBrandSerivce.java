package com.whalecar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.CarBrand;
import com.whalecar.domain.CarBrandView;
import com.whalecar.domain.CarModelLv1;
import com.whalecar.domain.CarSubBrand;
import com.whalecar.persistence.CarBrandMapper;
import com.whalecar.persistence.CarModelMapper;

/**
 * CarBrand Service
 * 
 * @author ruihuang
 * 
 */
@Controller
public class CarBrandSerivce {

	@Autowired
	CarBrandMapper carBrandMapper;
	@Autowired
	CarModelMapper carModelMapper;

	/**
	 * 获取所有 Brand
	 * 
	 * @return
	 */
	@RequestMapping("/getAllBrand")
	public @ResponseBody
	List<CarBrand> getAllBrand() {
		return carBrandMapper.queryAllBrand();
	}

	/**
	 * 根据Brand id获取子Brand
	 * 
	 * @param carBrandId
	 * @return
	 */
	@RequestMapping("/getBrand")
	public @ResponseBody
	List<CarSubBrand> getSubBrand(int carBrandId) {
		return carBrandMapper.querySubBrandByBrandId(carBrandId);
	}

	/**
	 * 获取所有CarBrandView
	 * 
	 * @return
	 */
	@RequestMapping("/getAllBrandView")
	public @ResponseBody
	List<CarBrandView> getAllBrandView() {
		List<CarBrandView> carBrandViewList = new ArrayList<CarBrandView>();
		// 1.先查询出所有CarBrand
		List<CarBrand> carBrandList = carBrandMapper.queryAllBrand();
		// 2.遍历所有CarBrand，根据id查询出所属的CarModelLv1,并组装carBrandViewList
		for (CarBrand carBrand : carBrandList) {
			Integer brandId = carBrand.getId();
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			conditionMap.put("carBrand", brandId);
			List<CarModelLv1> carModelLv1List = carModelMapper
					.queryCarModelLv1ByBrand(conditionMap);
			CarBrandView carBrandView = new CarBrandView();
			BeanUtils.copyProperties(carBrand, carBrandView);
			carBrandView.setCarModelLv1List(carModelLv1List);
			carBrandViewList.add(carBrandView);
		}
		return carBrandViewList;
	}
}