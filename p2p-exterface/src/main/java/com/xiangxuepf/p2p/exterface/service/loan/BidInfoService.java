package com.xiangxuepf.p2p.exterface.service.loan;

import com.xiangxuepf.p2p.exterface.vo.BidInfoVO;

import java.util.List;

/**
 * @author mhw
 * @create 2019-11-13 15:57
 */
public interface BidInfoService {
    /**
     * 获取平台累计投资金额；
     * @return
     */
    Double queryAllBidMoney();

    /**
     * 根据产品标识获取产品的所有投资记录；
     * @param id
     * @return
     */
    List<BidInfoVO> queryBidInfoListByLoanId(Integer loanId);
}
