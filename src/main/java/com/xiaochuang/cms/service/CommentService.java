package com.xiaochuang.cms.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.Comment;
import com.xiaochuang.cms.domain.User;



public interface CommentService {

	boolean addComments(Comment comment);
	
	/**
	 * 功能说明：<br>
	 * @param comment
	 * @param page
	 * @param orders  排序，默认按创建时间倒排序
	 * @return
	 * List<Article>
	 */
	public abstract List<Comment> gets(Comment comment);


	PageInfo<Comment> selectCommentByUserId(User user, Integer page, Integer pageSize);
	
}
