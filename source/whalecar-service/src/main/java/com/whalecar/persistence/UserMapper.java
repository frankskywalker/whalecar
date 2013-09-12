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
     * 根据用户名密码查询用户信息 如果没有查询到，说明用户名或密码错误
     *
     * @param conditionMap
     * @return
     */
	public User queryByNameAndPsw(Map<String, Object> conditionMap);

	/**
	 * 创建新用户
	 * 
	 * @param userMap
	 */
	public int createUser(Map<String, Object> userMap);


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
    public List<UserCarFavorite> queryUserCarFavorite(int userId);
}
