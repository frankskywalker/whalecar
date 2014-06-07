package com.whalecar.service;

import com.whalecar.domain.User;
import com.whalecar.domain.UserCarFavorite;
import com.whalecar.persistence.UserMapper;
import com.whalecar.service.tools.BooleanResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserService {

	@Autowired
	private UserMapper userMapper;

    /**
     *  根据邮箱密码查询用户信息 如果没有查询到，说明邮箱或密码错误
     *
     * @param conditionMap
     * @return
     */
	@RequestMapping(method = RequestMethod.POST, value = "queryByEmailAndPsw")
	public @ResponseBody
	User queryByEmailAndPsw(@RequestBody Map<String, Object> conditionMap) {
		return userMapper.queryByEmailAndPsw(conditionMap);
	}

    /**
     *  根据邮箱密码查询用户信息 如果没有查询到，说明邮箱或密码错误
     *
     * @param conditionMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "isExistUserForWeixin")
    public @ResponseBody
    BooleanResult isExistUserForWeixin(@RequestBody Map<String, Object> conditionMap) {
        User user = userMapper.queryByEmailAndPsw(conditionMap);
        if(user == null){
            return new BooleanResult(false);
        }
        else{
            userMapper.updateUserWxOpenId(user.getId(),String.valueOf(conditionMap.get("wxOpenId")));
            return new BooleanResult(true);
        }
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
     * 根据ID修改用户信息
     *
     * @param userMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "ChangeUserInf")
    public
    @ResponseBody
    Boolean ChangeUserInf(@RequestBody Map<String, Object> userMap) {
        int i = userMapper.ChangeUserInf(userMap);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据ID修改用户密码
     *
     * @param userMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "ChangeUserPsw")
    public
    @ResponseBody
    Boolean ChangeUserPsw(@RequestBody Map<String, Object> userMap) {
        int i = userMapper.ChangeUserPsw(userMap);
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

    /**
     * 存储用户收藏
     * @param condition
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value="saveUserFavorite")
    public @ResponseBody
    BooleanResult saveUserFavorite(@RequestBody Map<String,Object> condition){
        long count = userMapper.queryUserCarFavoriteCount(condition);
        if(count == 0){
            long updateCount = userMapper.createUserCarFavorite(condition);
            if(updateCount != 1){
                return new BooleanResult(false);
            }
        }
        return new BooleanResult(true);
    }

    /**
     * 删除用户收藏
     * @param condition
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value="removeUserFavorite")
    public @ResponseBody
    BooleanResult removeUserFavorite(@RequestBody Map<String,Object> condition){
        userMapper.deleteUserCarFavorite(condition);
        return new BooleanResult(true);
    }

    /**
     * 查询
     * @param userId
     * @return
     */
    @RequestMapping(method =RequestMethod.GET,value="getUserCarFavorite")
    public @ResponseBody
    List<UserCarFavorite> getUserCarFavorite(Integer userId){
        return userMapper.queryUserCarFavorite(userId);
    }

    /**
     * 查询
     * @param wxOpenId
     * @return
     */
    @RequestMapping(method =RequestMethod.GET,value="/user/isExistByWxOpenId")
    public @ResponseBody
    BooleanResult isExistByWxOpenId(String wxOpenId){
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("wxOpenId",wxOpenId);
        int count = userMapper.queryCountByCondition(condition);
        if(count == 0){
            return new BooleanResult(false);
        }
        else{
            return new BooleanResult(true);
        }
    }

}