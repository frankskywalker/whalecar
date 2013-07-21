package com.whalecar.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whalecar.domain.User;
import com.whalecar.persistence.UserMapper;

@Controller
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	/**
	 * 根据用户名密码查询用户信息
	 * 如果没有查询到，说明用户名或密码错误
	 * @param loginName
	 * @param password
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="queryByNameAndPsw")
	public @ResponseBody User queryByNameAndPsw(@RequestBody Map<String,Object> conditionMap){
		return userMapper.queryByNameAndPsw(conditionMap);
	}
	
	/**
	 * 创建新用户
	 */
	@RequestMapping(method=RequestMethod.POST,value="createUser")
	public @ResponseBody Boolean createUser(@RequestBody Map<String,Object> userMap){
		int i = userMapper.createUser(userMap);
		if(i == 1){
			return true;
		}
		else{
			return false;
		}
	}

}
