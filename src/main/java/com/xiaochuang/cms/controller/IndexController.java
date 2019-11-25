package com.xiaochuang.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.xiaochuang.cms.domain.Comment;
import com.xiaochuang.cms.domain.Links;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.ArticleService;
import com.xiaochuang.cms.service.CategoryService;
import com.xiaochuang.cms.service.ChannelService;
import com.xiaochuang.cms.service.CollectService;
import com.xiaochuang.cms.service.CommentService;
import com.xiaochuang.cms.service.LinksService;
import com.xiaochuang.cms.utils.ArticleEnum;

@RequestMapping("index")
@Controller
public class IndexController {
	@Autowired
	private LinksService linksService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Resource
	private CollectService collectService;//收藏

	@GetMapping(value = { "", "/", "index" })
	public String index(Article article, Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		
		Long l1=System.currentTimeMillis();
		Thread t1=null;
		Thread t2=null;
		Thread t3=null;
		Thread t4=null;
		
		t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<Channel> selects = channelService.selects();
				model.addAttribute("channels", selects);
			}
		});
		/**
		 * 判断查询文章状态是否为空,为空查询热门,不为空按条件查询
		 */
		t2=new Thread(new Runnable() {
			
			@Override
			public void run() {		
				PageInfo<Article> articles = null;

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
			}
		});
		
		t3=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// 右侧边栏显示最新的5遍文章
				Article article2 = new Article();
				article2.setContentType(ArticleEnum.HTML.getCode());
				article2.setStatus(1);
				PageInfo<Article> lastInfo = articleService.articles(article2, 1, 5);
				model.addAttribute("lastInfo", lastInfo);
			}
		});
		
		//友情链接
		t4=new Thread(new Runnable() {
			
			@Override
			public void run() {
				PageInfo<Links> selects = linksService.selects(1, 10);
				model.addAttribute("linkInfo", selects);
			}
		});
		//封装查询条件
		model.addAttribute("article", article);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long l2=System.currentTimeMillis();
		System.out.println("用时:=====>"+(l2-l1)+"ms");
		return "/index/index";
	}
	@RequestMapping("article")
	public	String article(Article article,Model model,HttpServletRequest request) {
		//查看评论
				Comment comment = new Comment();
				comment.setArticle(article);
				List<Comment> comments = commentService.gets(comment);
				model.addAttribute("comments", comments);

				ArticleWithBLOBs bs=articleService.article(article);
				//检查当前点击人是否登录.如果登录则根据标题和登录人查询是否收藏该文章
				HttpSession session = request.getSession(false);
				if(null!=session) {
			       //
					User user = (User) session.getAttribute("user");
					int i = collectService.selectByText(bs.getTitle(), user);
					model.addAttribute("isCollect", i);
				}
		model.addAttribute("article",bs);
		return "/index/article";
	}
}
