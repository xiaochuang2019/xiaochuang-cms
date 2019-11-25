package com.xiaochuang.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuang.common.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.dao.CollectMapper;
import com.xiaochuang.cms.domain.Collect;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.CollectService;
import com.xiaochuang.cms.utils.CMSAjaxException;

@Service
public class CollectServiceImpl implements CollectService {
	@Resource
	private CollectMapper collectMapper;
	/**
	 * 验证是否为有效的URL,并添加
	 */
	@Override
	public boolean insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl()))
			throw new CMSAjaxException(1, "不是有效的URL");
		collect.setCreated(new Date());
		collectMapper.insert(collect);
		return true;
	}
	/**
	 * 用户查看自己收藏的文章
	 */
	@Override
	public PageInfo<Collect> selects(Integer page,Integer pageSize ,User user) {
		PageHelper.startPage(page, pageSize);
		List<Collect> list = collectMapper.selects( user);
		return new PageInfo<Collect>(list);
	}
	/**
	 * 用户删除自己收藏的文章
	 */
	@Override
	public boolean deleteById(Integer id) {
		collectMapper.deleteById(id);
		return true;
	}
	/**
	 * 判断某用户的是否收藏过某文章
	 */
	@Override
	public int selectByText(String text, User user) {
		return collectMapper.selectByText(text, user);
	}

}
