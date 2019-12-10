package com.xiangxuepf.p2p.timer.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author mhw
 * @create 2019-11-28 17:31
 */
@Component
public class TimerManager {

    private Logger logger = LogManager.getLogger(TimerManager.class);

    @Scheduled(cron = "0/5 * * * * ?") //每5秒执行一次
    public void test(){
        logger.info("--测试开启--");
        logger.info("--测试结束--");
    }

}
