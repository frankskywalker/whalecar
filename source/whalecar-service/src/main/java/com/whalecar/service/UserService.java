package com.whalecar.service;

import com.whalecar.domain.User;
import com.whalecar.persistence.UserMapper;
import com.whalecar.service.tools.BooleanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 根据用户名密码查询用户信息 如果没有查询到，说明用户名或密码错误
	 * 
	 * @param conditionMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "queryByNameAndPsw")
	public @ResponseBody
	User queryByNameAndPsw(@RequestBody Map<String, Object> conditionMap) {
		return userMapper.queryByNameAndPsw(conditionMap);
	}

	/**
	 * 创建新用户
	 * 
	 * @param userMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "createUser")
	public @ResponseBody
	Boolean createUser(@RequestBody Map<String, Object> userMap) {
		int i = userMapper.createUser(userMap);
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

    /**
     * 根据条件查询用户数量，可用于验证
     * @param userMap
     * @return 符合条间总数
    */
    @RequestMapping(method = RequestMethod.POST, value = "userIsExist")
    public @ResponseBody
    BooleanResult userIsExist(@RequestBody Map<String, Object> userMap){
         int count =  userMapper.queryCountByCondition(userMap);
         if(count == 0){
             return new BooleanResult(false);
         }
         else{
             return new BooleanResult(true);
         }
    }
}
