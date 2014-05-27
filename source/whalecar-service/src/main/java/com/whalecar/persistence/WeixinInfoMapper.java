package com.whalecar.persistence;

import com.whalecar.domain.WeixinInfo;

import java.util.List;

/**
 * Created by ruihuang on 14-5-26.
 */
public interface WeixinInfoMapper {

    public int save(WeixinInfo weixinInfo);

    public int updateForwardCount(String openId);

    public WeixinInfo queryByOpenId(String openId);

    public List<WeixinInfo> queryAllWithRank();
}
