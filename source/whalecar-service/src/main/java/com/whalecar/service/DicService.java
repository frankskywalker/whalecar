package com.whalecar.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.DicCity;
import com.whalecar.domain.DicColor;
import com.whalecar.persistence.DicMapper;

/**
 * Dic Sercvice
 * 
 * @author ruihuang
 * 
 */
@Controller
public class DicService {

	@Autowired
	DicMapper dicMapper;

	/**
	 * 返回所有城市
	 * 
	 * @return
	 */
	@RequestMapping("/getAllDicCity")
	public @ResponseBody
	List<DicCity> getAllDicCity() {
		return dicMapper.queryAllDicCity();
	}

	/**
	 * 返回所有颜色
	 * 
	 * @return
	 */
	@RequestMapping("/getAllDicColor")
	public @ResponseBody
	List<DicColor> getAllDicColor() {
		return dicMapper.queryAllDicColor();
	}

	/**
	 * 
	 * @param carModelLv3Id
	 * @param outOrInColor
	 *            eumn : "out"/"in"
	 * @return
	 */
	@RequestMapping("/getDicColorByCarModelLv3")
	public @ResponseBody
	List<DicColor> getDicColorByCarModelLv3(Integer carModelLv3Id,
			String outOrInColor) {
		if (StringUtils.equals(outOrInColor, "out")) {
			return dicMapper.queryDicColorBycarModelLv3(carModelLv3Id);
		} else if (StringUtils.equals(outOrInColor, "in")) {
			// TODO 补充内饰颜色查询
			return null;
		} else {
			return null;
		}
	}
}
