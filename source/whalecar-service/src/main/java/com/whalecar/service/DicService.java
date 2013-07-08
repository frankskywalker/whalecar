package com.whalecar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.whalecar.domain.DicCity;
import com.whalecar.domain.DicColor;
import com.whalecar.persistence.DicMapper;

@Controller
public class DicService {
	
	@Autowired
	DicMapper dicMapper;
	
	/**
	 * 返回所有城市
	 * @return
	 */
	public List<DicCity> getAllDicCity(){
		return dicMapper.getAllDicCity();
	}
	
	/**
	 * 返回所有颜色
	 * @return
	 */
	public List<DicColor> getAllDicColor(){
		return dicMapper.getAllDicColor();
	}

}
