package com.xiaochuang.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.domain.UserVo;

public interface UserMapper {
	List<User> selects(User user);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
	User selectByName(@Param("username")String username);
}