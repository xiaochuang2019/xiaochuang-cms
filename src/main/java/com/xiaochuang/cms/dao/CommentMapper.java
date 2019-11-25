package com.xiaochuang.cms.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaochuang.cms.domain.Comment;




public interface CommentMapper {

	int save(Comment comment);

	List<Comment> selects(@Param("comment")Comment comment);

	int count(Comment comment);

}
