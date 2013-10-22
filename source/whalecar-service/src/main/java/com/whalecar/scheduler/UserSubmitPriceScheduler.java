package com.whalecar.scheduler;

import com.whalecar.domain.UserSubmitPrice;
import com.whalecar.persistence.UserOrderMapper;
import com.whalecar.persistence.UserSubmitPriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by ruihuang on 13-10-21.
 */
@Service
public class UserSubmitPriceScheduler {

    private Logger logger = LoggerFactory.getLogger(UserSubmitPriceScheduler.class);

    @Autowired
    private UserSubmitPriceMapper userSubmitPriceMapper;

    /**
     * 5分钟执行一次
     * 用户提交的议价订单过期处理
     */
    @Scheduled(fixedDelay=300000)
    public void processCarOrderOutOfDate() {
        logger.info("UserSubmitPriceScheduler start : processCarOrderOutOfDate");
        userSubmitPriceMapper.processUserSubmitPriceOutOfDate();
        logger.info("UserSubmitPriceScheduler end : processCarOrderOutOfDate");
    }
}
