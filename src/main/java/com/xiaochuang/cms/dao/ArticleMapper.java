package com.xiaochuang.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaochuang.cms.domain.Article;
import com.xiaochuang.cms.domain.ArticleWithBLOBs;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

	List<Article> selects(Article article);

	void updateHits(@Param("id")Integer id);
}