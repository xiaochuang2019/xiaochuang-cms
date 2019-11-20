package com.xiaochuang.cms.dao;

import java.util.List;

import com.xiaochuang.cms.domain.Channel;

public interface ChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);

	List<Channel> selects();
}