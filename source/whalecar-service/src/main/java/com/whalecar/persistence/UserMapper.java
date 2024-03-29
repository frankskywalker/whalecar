package com.whalecar.persistence;

import com.whalecar.domain.User;
import com.whalecar.domain.UserCarFavorite;

import java.util.List;
import java.util.Map;

/**
 * 用户相关 Table = User
 * 
 * @author ruihuang
 * 
 */
public interface UserMapper {

    /**
     * 根据邮箱密码查询用户信息 如果没有查询到，说明邮箱或密码错误
     *
     * @param conditionMap
     * @return
     */
	public User queryByEmailAndPsw(Map<String, Object> conditionMap);

	/**
	 * 创建新用户
	 * 
	 * @param userMap
	 */
	public int createUser(Map<String, Object> userMap);

    /**
     * 更改用户信息
     *
     * @param userMap
     */
    public int ChangeUserInf(Map<String, Object> userMap);

    /**
     * 更改用户密码
     *
     * @param userMap
     */
    public int ChangeUserPsw(Map<String, Object> userMap);

    /**
     * 根据条件查询用户数量，可用于验证
     * @param userMap
     * @return 符合条间总数
     */
    public int queryCountByCondition(Map<String, Object> userMap);

    /**
     * 创建User favorite
     * @param condition
     * @return
     */
    public int createUserCarFavorite(Map<String, Object> condition);

    /**
     * 查询User Favorite总数
     * 查询User Favorite
     * @param condition
     * @return
     */
    public int queryUserCarFavoriteCount(Map<String, Object> condition);

    /**
     * 删除User Favorite
     * @param condition
     * @return
     */
    public int deleteUserCarFavorite(Map<String, Object> condition);

    /**
     * 根据用户id查询用户收藏列表
     * @param userId
     * @return
     */
    public List<UserCarFavorite> queryUserCarFavorite(Integer userId);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    public User queryUserById(Integer userId);

    /**
     * 根据用户id更新用户wxOpenId
     * @param userId
     * @param wxOpenId
     * @return
     */
    public int updateUserWxOpenId(Integer userId,String wxOpenId);

    /**
     * 根据wxOpenId查询用户
     * @param wxOpenId
     * @return
     */
    public User queryUserByWxOpenId(String wxOpenId);
}
