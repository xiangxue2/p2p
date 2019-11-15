package com.xiangxuepf.p2p.dataservice.mapper.user;

import com.xiangxuepf.p2p.exterface.model.user.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Long selectAllUserCount();
}