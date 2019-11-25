package com.xiaochuang.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.Links;

public interface LinksService {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 添加友情链接
	 * @param links
	 * @return
	 * @return: int
	 */
	boolean insert(Links links);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询友情连接
	 * @return
	 * @return: List<Links>
	 */
	PageInfo<Links> selects(Integer page,Integer pageSize);
}
