package com.whalecar.persistence;

import java.util.List;

import com.whalecar.domain.CarBrand;
import com.whalecar.domain.CarSubBrand;

/**
 * 汽车品牌相关，Table = Car_Brand
 * @author ruihuang
 *
 */
public interface CarBrandMapper{
	
	public List<CarBrand> queryAllBrand();
	
	public List<CarSubBrand> querySubBrandByBrandId(int id);

}