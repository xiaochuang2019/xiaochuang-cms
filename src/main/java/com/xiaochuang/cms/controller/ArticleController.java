package com.xiaochuang.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.Article;
import com.xiaochuang.cms.domain.ArticleWithBLOBs;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.ArticleService;
@RequestMapping("article")
@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	/**
	 * 
	 * @Title: articles 
	 * @Description: 查询并前往管理员文章页面
	 * @param article
	 * @param model
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Article article,Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		PageInfo<Article> articles =	articleService.articles(article,page,pageSize);
		model.addAttribute("articles", articles.getList());
		model.addAttribute("article", article);
		int[] nums = articles.getNavigatepageNums();
		model.addAttribute("info", articles);
		model.addAttribute("num",nums);
		return "admin/article/articles";
	}
	/**
	 * 
	 * @Title: article 
	 * @Description: 查询文章详情
	 * @param article
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("article")
	public	String article(Article article,Model model) {
		ArticleWithBLOBs bs=articleService.article(article);
		model.addAttribute("article",bs);
		return "admin/article/article";
	}
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改文章状态
	 * @param article
	 * @return
	 * @return: String
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(ArticleWithBLOBs article) {
		int num=articleService.update(article);
		return num+"";
	}
}
