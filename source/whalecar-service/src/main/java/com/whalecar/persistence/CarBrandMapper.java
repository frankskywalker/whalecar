package com.whalecar.persistence;

import com.whalecar.domain.CarBrand;
import com.whalecar.domain.CarBrandWithSubBrandView;
import com.whalecar.domain.CarSubBrand;

import java.util.List;

/**
 * 汽车品牌相关，Table = Car_Brand
 * 
 * @author ruihuang
 * 
 */
public interface CarBrandMapper {

	public List<CarBrand> queryAllBrand();

	public List<CarSubBrand> querySubBrandByBrandId(int id);

    public List<CarBrandWithSubBrandView> queryAllBrandAndSubBrand();

}