package com.whalecar.service;

import com.whalecar.domain.DicArea;
import com.whalecar.domain.DicCity;
import com.whalecar.domain.DicColor;
import com.whalecar.persistence.DicMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
     * 返回所有宁波市区，县市
     *
     * @return
     */
    @RequestMapping("/queryDicCityAndArea")
    public @ResponseBody
    List<DicCity> queryDicCityAndArea() {
        List<DicCity> cityList=dicMapper.queryAllDicCity();
        for(DicCity c:cityList){
            c.setAreaList(dicMapper.queryAllDicArea(c.getId()));
        }
        return cityList;
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
			return dicMapper.queryOutsideDicColorBycarModelLv3(carModelLv3Id);
		} else if (StringUtils.equals(outOrInColor, "in")) {
            return dicMapper.queryInsideDicColorBycarModelLv3(carModelLv3Id);
		} else {
			return null;
		}
	}
}
