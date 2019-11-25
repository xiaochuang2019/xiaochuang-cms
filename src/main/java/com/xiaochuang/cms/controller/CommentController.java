package com.xiaochuang.cms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaochuang.cms.domain.Article;
import com.xiaochuang.cms.domain.Comment;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.ArticleService;
import com.xiaochuang.cms.service.CommentService;

@RequestMapping("comm")
@Controller
public class CommentController {
	@Resource
	private ArticleService articleService;
	
	@Resource
	private CommentService commentService;
	/**
	 * 
	 * @Title: addComments 
	 * @Description: 添加评论
	 * @param comment
	 * @param id
	 * @param session
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("comment")
	@ResponseBody
	public boolean addComments(Comment comment,@RequestParam("id")Integer id,HttpSession session){
		User author = (User) session.getAttribute("user");
		comment.setAuthor(author);
		comment.setCreated(new Date());
		Article article = new Article();
		/**
		 * 前台获取文章ID,并存入评论表中
		 */
		article.setId(id);
		comment.setArticle(article );
		boolean flg = commentService.addComments(comment);
		return flg;
	}
}
