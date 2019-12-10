package com.xiangxuepf.p2p.exterface.service.loan;

/**
 * @author mhw
 * @create 2019-12-03 10:10
 */
public interface OnlyNumberService {
    /**
     * 获取redis的全局唯一数字；
     * @return
     */
    Long getOnlyNumber();
}
