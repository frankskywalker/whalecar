package com.whalecar.persistence;

import java.util.List;

import com.whalecar.domain.CarBrand;
import com.whalecar.domain.CarSubBrand;

public interface CarBrandMapper{
	
	public List<CarBrand> getAllBrand();
	
	public List<CarSubBrand> getSubBrandByBrandId(int id);

}