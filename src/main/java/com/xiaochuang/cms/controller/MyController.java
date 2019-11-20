package com.xiaochuang.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 
 * @ClassName: MyController 
 * @Description: 注册用户的个人中心
 * @author: charles
 * @date: 2019年11月14日 上午11:06:11
 */
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.Article;
import com.xiaochuang.cms.domain.ArticleWithBLOBs;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.ArticleService;
@RequestMapping("my")
@Controller
public class MyController {
	@Autowired
	private ArticleService articleService;
	/**
	 * 
	 * @Title: index 
	 * @Description: 去个人中心首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {

		return  "my/index";

	}
	/**
	 * 
	 * @Title: publish 
	 * @Description: 增加文章/发布文章
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {

		return "my/article/publish";

	}

	@PostMapping("publish")
	@ResponseBody
	public String publish(ArticleWithBLOBs article,MultipartFile file,HttpServletRequest request) throws Exception {

		if (!file.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
			String filename = file.getOriginalFilename();
			String lastpath =UUID.randomUUID()+ filename.substring(filename.indexOf("."));
			File file2=new File(path,lastpath);
			file.transferTo(file2);
			article.setPicture(lastpath);

		}
		article.setStatus(0);//待审核
		article.setUserId(160);//暂时写死...
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		int num=articleService.insertSelective(article);
		return num+"";
	}
	@RequestMapping("articles")
	public String articles(Article article,Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize,HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if (session!=null) {
			User user = (User) session.getAttribute("user");
			Integer id = user.getId();
			System.out.println(id);
			article.setUserId(id);
		}
		PageInfo<Article> articles =	articleService.articles(article,page,pageSize);
		model.addAttribute("articles", articles.getList());
		model.addAttribute("article", article);
		int[] nums = articles.getNavigatepageNums();
		model.addAttribute("info", articles);
		model.addAttribute("num",nums);
		return "my/article/articles";
	}
	@RequestMapping("article")
	public	String article(Article article,Model model) {
		ArticleWithBLOBs bs=articleService.article(article);
		model.addAttribute("article",bs);
		return "my/article/article";
	}
}
