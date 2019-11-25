package com.xiaochuang.cms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.dao.CommentMapper;
import com.xiaochuang.cms.domain.Comment;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.CommentService;



@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper commentMapper;
	
	
	
	@Override
	public boolean addComments(Comment comment) {
		return commentMapper.save(comment)>0?true:false;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Comment> gets(Comment comment) {
		List<Comment> comments = commentMapper.selects(comment);
		return comments;
	}

	@Override
	public PageInfo<Comment> selectCommentByUserId(User user, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		Comment comment=new Comment();
		comment.setAuthor(user);
		List<Comment> selects = commentMapper.selects(comment);
		PageInfo<Comment> info=new PageInfo<>(selects);
		return info;
	}


}
