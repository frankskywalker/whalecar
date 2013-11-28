package com.whalecar.service;

import com.whalecar.domain.CarAddition;
import com.whalecar.persistence.CarAdditionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ruihuang on 13-11-28.
 */
@Controller
public class CarAdditionService {

    @Autowired
    private CarAdditionMapper carAdditionMapper;

    @RequestMapping("/getAllCarAddition")
    public @ResponseBody List<CarAddition> getAll(){
        return carAdditionMapper.selectAll();
    }
}
