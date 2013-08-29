package com.whalecar.persistence;

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

	public List<DicCity> queryAllDicCity();

	public List<DicColor> queryAllDicColor();

	public List<DicColor> queryDicColorBycarModelLv3(Integer carModelLv3Id);
}