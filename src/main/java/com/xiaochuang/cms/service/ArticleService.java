package com.xiaochuang.cms.service;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.Article;
import com.xiaochuang.cms.domain.ArticleWithBLOBs;

public interface ArticleService {

	PageInfo<Article> articles(Article article, Integer page, Integer pageSize);

	int update(ArticleWithBLOBs article);

	ArticleWithBLOBs article(Article article);

	int insertSelective(ArticleWithBLOBs article);

}
