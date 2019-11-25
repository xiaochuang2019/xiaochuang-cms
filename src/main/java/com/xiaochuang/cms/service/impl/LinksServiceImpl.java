package com.xiaochuang.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuang.common.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.dao.LinksMapper;
import com.xiaochuang.cms.domain.Links;
import com.xiaochuang.cms.service.LinksService;
import com.xiaochuang.cms.utils.CMSAjaxException;
@Service
public class LinksServiceImpl implements LinksService {
	@Autowired
	private LinksMapper linksMapper;

	@Override
	public boolean insert(Links links) {
			boolean b = StringUtil.isHttpUrl(links.getUrl());
			if (b) {
				int insert = linksMapper.insert(links);
				return true;
			}else {
				throw new CMSAjaxException(1,"不是有效的url");
			}
	}

	@Override
	public PageInfo<Links> selects(Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Links> selects = linksMapper.selects();
		PageInfo<Links> info=new PageInfo<>(selects);
		return info;
	}
}
