package com.xiaochuang.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.domain.UserVo;

public interface UserService {
	PageInfo<User> users(User user,Integer page,Integer pageSize);

	int update(User user);

	boolean insertUser(UserVo vo);

	User login(User user);
}
