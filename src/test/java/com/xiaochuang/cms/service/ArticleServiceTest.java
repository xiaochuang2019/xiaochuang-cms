package com.xiaochuang.cms.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chuang.common.utils.StringUtil;

public class ArticleServiceTest extends JunitParent{

	@Test
	public void testArticles() {
		boolean httpUrl = StringUtil.isHttpUrl("https://www.toutiao.com/");
		System.out.println(httpUrl);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertSelective() {
		fail("Not yet implemented");
	}

}
