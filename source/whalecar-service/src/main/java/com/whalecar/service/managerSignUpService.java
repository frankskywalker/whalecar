package com.whalecar.service;

import com.whalecar.domain.ManagerSignUp;
import com.whalecar.persistence.ManagerSignUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wufei on 14-8-27.
 */
@Controller
public class ManagerSignUpService {
    @Autowired
    private ManagerSignUpMapper managerSignUpMapper;

    /**
     * 获取所有 报名信息
     * @return
     */

   // @RequestMapping(method = RequestMethod.GET,value = "/manager/managerGetCarModelLv1")
    @RequestMapping(method = RequestMethod.GET,value = "/manager/getSignUp")
    public @ResponseBody
    List<ManagerSignUp> getSignUp() {
        return managerSignUpMapper.queryGolf7();
    }
}
