package com.whalecar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.CarBrand;
import com.whalecar.domain.CarSubBrand;
import com.whalecar.persistence.CarBrandMapper;

@Controller
public class HelloWorld {

	@Autowired
	CarBrandMapper carBrandMapper;

	@RequestMapping("/getAllBrand")
	public @ResponseBody List<CarBrand> getAllBrand(){
		return carBrandMapper.queryAllBrand();
	}

	@RequestMapping("/getBrand")
	public @ResponseBody List<CarSubBrand> getSubBrand(int carBrandId){
		return carBrandMapper.querySubBrandByBrandId(carBrandId);
	}

}