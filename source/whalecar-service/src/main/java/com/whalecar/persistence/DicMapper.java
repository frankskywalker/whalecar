package com.whalecar.persistence;

import java.util.List;

import com.whalecar.domain.DicCity;
import com.whalecar.domain.DicColor;

/**
 * 字典表相关，Table = Dic***
 * @author ruihuang
 *
 */
public interface DicMapper {

	public List<DicCity> queryAllDicCity();
	
	public List<DicColor> queryAllDicColor();
}