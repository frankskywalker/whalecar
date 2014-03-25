package com.whalecar.persistence;

import com.whalecar.domain.DicArea;
import com.whalecar.domain.DicCity;
import com.whalecar.domain.DicColor;

import java.util.List;

/**
 * 字典表相关，Table = Dic***
 * 
 * @author ruihuang
 * 
 */
public interface DicMapper {

	public List<DicCity> queryAllDicCity();//查询所有城市

	public List<DicColor> queryAllDicColor();

	public List<DicColor> queryOutsideDicColorBycarModelLv3(Integer carModelLv3Id);

    public List<DicColor> queryInsideDicColorBycarModelLv3(Integer carModelLv3Id);

    public List<DicArea> queryAllDicArea(Integer city_id);//查询所有城市下辖县市区
}