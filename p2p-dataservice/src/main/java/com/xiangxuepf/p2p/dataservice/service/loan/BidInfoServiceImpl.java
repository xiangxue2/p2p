package com.xiangxuepf.p2p.dataservice.service.loan;

import com.xiangxuepf.p2p.common.Constants;
import com.xiangxuepf.p2p.dataservice.mapper.loan.BidInfoMapper;
import com.xiangxuepf.p2p.exterface.service.loan.BidInfoService;
import com.xiangxuepf.p2p.exterface.vo.BidInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author mhw
 * @create 2019-11-13 15:59
 */
@Service("bidInfoServiceImpl")
public class BidInfoServiceImpl implements BidInfoService {
    @Autowired
    private BidInfoMapper bidInfoMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 获取平台累计投资金额；
     * @return
     */
    @Override
    public Double queryAllBidMoney() {
        //获取指定key的操作对象；
        BoundValueOperations<Object, Object> boundValueOps =
                redisTemplate.boundValueOps(Constants.ALL_BID_MONEY);

        //获取key的value;
        Double allBidMoney = (Double) boundValueOps.get();
        //判断是否有值；
        if (null == allBidMoney) {
            //去数据库查询；
            allBidMoney =  bidInfoMapper.selectAllBidMoney();
            //存放到redis中
            boundValueOps.set(allBidMoney,15, TimeUnit.MINUTES);

        }


        return allBidMoney;
    }

    @Override
    public List<BidInfoVO> queryBidInfoListByLoanId(Integer loanId) {


        return bidInfoMapper.selectBidInfoListByLoanId(loanId);
    }


}
