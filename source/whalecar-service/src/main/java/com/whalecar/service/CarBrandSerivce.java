package com.whalecar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.CarBrand;
import com.whalecar.domain.CarSubBrand;
import com.whalecar.persistence.CarBrandMapper;

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
}