package com.whalecar.service;

import com.whalecar.domain.managerSignUp;
import com.whalecar.persistence.managerSignUpMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by wufei on 14-8-27.
 */
@Controller
public class managerSignUpService {
    @Autowired
    private managerSignUpMapper managerSignUpMapper;

    /**
     * 获取所有 报名信息
     * @return
     */

   // @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetCarModelLv1")
    @RequestMapping(method = RequestMethod.GET,value = "/manager/getSignUp")
    public @ResponseBody
    List<managerSignUp> getSignUp() {
        return managerSignUpMapper.queryGolf7();
    }
}
