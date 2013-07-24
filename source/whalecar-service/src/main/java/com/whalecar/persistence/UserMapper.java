package com.whalecar.persistence;

import java.util.Map;

import com.whalecar.domain.User;

/**
 * 用户相关 Table = User
 * 
 * @author ruihuang
 * 
 */
public interface UserMapper {

	/**
	 * 根据用户名密码查询用户信息 如果没有查询到，说明用户名或密码错误
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public User queryByNameAndPsw(Map<String, Object> conditionMap);

	/**
	 * 创建新用户
	 * 
	 * @param userMap
	 */
	public int createUser(Map<String, Object> userMap);
}
