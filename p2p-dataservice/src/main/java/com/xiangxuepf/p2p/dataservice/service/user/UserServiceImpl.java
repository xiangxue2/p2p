package com.xiangxuepf.p2p.dataservice.service.user;

import com.xiangxuepf.p2p.common.Constants;
import com.xiangxuepf.p2p.dataservice.mapper.user.UserMapper;
import com.xiangxuepf.p2p.exterface.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author mhw
 * @create 2019-11-13 14:11
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Override
    public Long queryAllUerCount() {
        //首先去redis查询，有，直接用；
        //更改redis中key值的序列化方式；
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //获取指定操作某一个key的操作对象；    //@ 另一种操作redis方式
        BoundValueOperations<Object, Object> boundValueOps =
                redisTemplate.boundValueOps(Constants.ALL_USER_COUNT);
        //获取指定key的value值；
        Long allUserCount = (Long)boundValueOps.get();
        if (null == allUserCount) {
            //去数据库查询；
            allUserCount = userMapper.selectAllUserCount();
            //存在redis中；
            boundValueOps.set(allUserCount,15, TimeUnit.MINUTES);
        }

        return allUserCount;
    }
}
