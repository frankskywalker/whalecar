package com.whalecar.persistence;

import com.whalecar.domain.CarBrand;
import com.whalecar.domain.UserCar;


import java.util.List;
import java.util.Map;

/**
 *
 * 
 * @author sf
 * 
 */
public interface UserCarMapper {

    //记录有购车意向的用户信息
    public int addUserCar(UserCar userCar);


}