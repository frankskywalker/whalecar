package com.whalecar.persistence;

import com.whalecar.domain.ManagerSignUp;

import java.util.List;
import java.util.Map;

/**
 * Created by ruihuang on 14-3-3.
 */
public interface Golf7AdvMapper {
    public int insert(Map<String,String> golf7Adv);

    public List<ManagerSignUp> queryGolf7();

    public List<ManagerSignUp> queryGolf7Type9();

    public List<ManagerSignUp> queryGolf7Type10();
}
