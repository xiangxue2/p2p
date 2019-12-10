package com.xiangxuepf.p2p.exterface.service.loan;

/**
 * @author mhw
 * @create 2019-11-29 10:22
 */
public interface IncomeRecordService {
    /**
     * 生成收益计划；
     */
    void generateIncomePlan();

    /**
     * 生成收益返还
     */
    void generateIncomeBack();
}
