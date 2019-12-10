package com.xiangxuepf.p2p.dataservice.test;

/**
 * @author mhw
 * @create 2019-11-29 21:33
 */
public class TestLog4j2 {

    public static void main(String[] args) {
        org.apache.log4j.Logger logger
                = org.apache.log4j.LogManager.getLogger(TestLog4j2.class);
        logger.info("nini好");
        logger.debug("debug");
        logger.warn("warn....");
    }
}
