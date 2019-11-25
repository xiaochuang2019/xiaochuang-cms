package com.xiaochuang.cms.dao;

import java.util.List;

import com.xiaochuang.cms.domain.Links;

public interface LinksMapper {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 添加友情链接
	 * @param links
	 * @return
	 * @return: int
	 */
	int insert(Links links);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询友情连接
	 * @return
	 * @return: List<Links>
	 */
	List<Links> selects();
}
