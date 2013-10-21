package com.whalecar.scheduler;

import com.whalecar.persistence.UserOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by ruihuang on 13-10-21.
 */
@Service
public class CarOrderScheduler {

    private Logger logger = LoggerFactory.getLogger(CarOrderScheduler.class);

    @Autowired
    private UserOrderMapper userOrderMapper;

    /**
     * 5分钟执行一次
     * 用户订单过期处理
     */
    @Scheduled(fixedDelay=300000)
    public void processCarOrderOutOfDate() {
        logger.info("CarOrderScheduler start : processCarOrderOutOfDate");
        userOrderMapper.processUserOrderOutOfDate();
        logger.info("CarOrderScheduler end : processCarOrderOutOfDate");
    }
}
