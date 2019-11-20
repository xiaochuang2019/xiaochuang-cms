package com.xiaochuang.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.Article;
import com.xiaochuang.cms.domain.ArticleWithBLOBs;
import com.xiaochuang.cms.domain.Category;
import com.xiaochuang.cms.domain.Channel;
import com.xiaochuang.cms.service.ArticleService;
import com.xiaochuang.cms.service.CategoryService;
import com.xiaochuang.cms.service.ChannelService;

@RequestMapping("index")
@Controller
public class IndexController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;

	@GetMapping(value = { "", "/", "index" })
	public String index(Article article, Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<Article> articles = null;
		List<Channel> selects = channelService.selects();
		model.addAttribute("channels", selects);
		if (article.getChannelId() == null) {
			article.setHot(1);// 热门
			article.setStatus(1);// 审核过的
			articles = articleService.articles(article, page, pageSize);
		} else {
			// 显示分类
			List<Category> categories = categoryService.selectsByChannelId(article.getChannelId());
			model.addAttribute("categorys", categories);
			// 2.显示分类下的文章
			articles = articleService.articles(article, page, pageSize);
		}
		model.addAttribute("info", articles);

		// 右侧边栏显示最新的5遍文章
		Article article2 = new Article();
		article2.setStatus(1);
		PageInfo<Article> lastInfo = articleService.articles(article2, 1, 5);
		model.addAttribute("lastInfo", lastInfo);
		//封装查询条件
		model.addAttribute("article", article);
		return "/index/index";
	}
	@RequestMapping("article")
	public	String article(Article article,Model model) {
		ArticleWithBLOBs bs=articleService.article(article);
		model.addAttribute("article",bs);
		return "/index/article";
	}
}
