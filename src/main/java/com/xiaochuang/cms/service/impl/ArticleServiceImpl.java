package com.xiaochuang.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.dao.ArticleMapper;
import com.xiaochuang.cms.domain.Article;
import com.xiaochuang.cms.domain.ArticleWithBLOBs;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public PageInfo<Article> articles(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> articles=articleMapper.selects(article);
		PageInfo<Article> info=new PageInfo<>(articles);
		return info;
	}

	@Override
	public int update(ArticleWithBLOBs article) {
		return articleMapper.updateByPrimaryKeySelective(article);
	}

	@Override
	public ArticleWithBLOBs article(Article article) {
		articleMapper.updateHits(article.getId());
		return articleMapper.selectByPrimaryKey(article.getId());
	}

	@Override
	public int insertSelective(ArticleWithBLOBs article) {
		return articleMapper.insertSelective(article);
	}
	
}
