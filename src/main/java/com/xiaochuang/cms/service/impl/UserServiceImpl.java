package com.xiaochuang.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuang.common.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.dao.UserMapper;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.domain.UserVo;
import com.xiaochuang.cms.service.UserService;
import com.xiaochuang.cms.utils.CMSException;
import com.xiaochuang.cms.utils.Md5Util;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	public PageInfo<User> users(User user,Integer page,Integer pageSize){
		PageHelper.startPage(page, pageSize);
		List<User> users=userMapper.selects(user);
		PageInfo<User> info=new PageInfo<>(users);
		return info;
	}

	@Override
	public int update(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public boolean insertUser(UserVo vo) {

		if (!StringUtil.hasText(vo.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		if (!StringUtil.hasText(vo.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		if (!StringUtil.hasText(vo.getRepassword())) {
			throw new CMSException("再次输入,密码不能为空");
		}
		if (!vo.getRepassword().equals(vo.getPassword())) {
			throw new CMSException("两次密码必须一致");
		}
		User user=userMapper.selectByName(vo.getUsername());
		if (user!=null) {
			throw new CMSException("用户名已存在");
		}
		vo.setPassword(Md5Util.md5Encoding(vo.getPassword()));
		return userMapper.insertSelective(vo)>0;
	}

	@Override
	public User login(User user) {
		if (!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		if (!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		//查询用户名是否存在
				User u = userMapper.selectByName(user.getUsername());
				if (u==null) {
					throw new CMSException("用户名不存在");
				}else {
					if (!Md5Util.md5Encoding(user.getPassword()).equals(u.getPassword())) {
						throw new CMSException("密码错误");
					}
				}
		return u;
	}
}
