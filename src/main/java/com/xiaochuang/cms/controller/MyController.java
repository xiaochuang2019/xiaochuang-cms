package com.xiaochuang.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.google.gson.Gson;
import com.xiaochuang.cms.domain.Article;
import com.xiaochuang.cms.domain.ArticleVO;
import com.xiaochuang.cms.domain.ArticleWithBLOBs;
import com.xiaochuang.cms.domain.Collect;
import com.xiaochuang.cms.domain.Comment;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.ArticleService;
import com.xiaochuang.cms.service.CollectService;
import com.xiaochuang.cms.service.CommentService;
import com.xiaochuang.cms.utils.ArticleEnum;
import com.xiaochuang.cms.utils.Result;
import com.xiaochuang.cms.utils.ResultUtil;
@RequestMapping("my")
@Controller
public class MyController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CollectService collectService;// 我的收藏
	
	@Autowired
	private CommentService commentService;
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
	 * @Title: collects
	 * @Description: 我的收藏
	 * @return
	 * @return: String
	 */
	@GetMapping("collects")
	public String collects(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");

		PageInfo<Collect> info = collectService.selects(page, pageSize, user);

		model.addAttribute("info", info);
		return "/my/collect/collects";

	}
	
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 移除收藏
	 * @param id
	 * @return
	 * @return: Result<Collect>
	 */
	@ResponseBody
	@PostMapping("deleteCollect")
	public Result<Collect> deleteCollect(Integer id){
		collectService.deleteById(id);
		return ResultUtil.success();
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
	/**
	 * 
	 * @Title: publish 
	 * @Description: 实现发布文章
	 * @param article
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@PostMapping("publish")
	@ResponseBody
	public String publish(ArticleWithBLOBs article,MultipartFile file,HttpServletRequest request) throws Exception {
		/**
		 * 将图片存入resource/pic文件下
		 */
		if (!file.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
			String filename = file.getOriginalFilename();
			String lastpath =UUID.randomUUID()+ filename.substring(filename.indexOf("."));
			File file2=new File(path,lastpath);
			file.transferTo(file2);
			article.setPicture(lastpath);

		}
		/**
		 * 获取登录后的用户ID
		 */
		HttpSession session = request.getSession(false);
		if (session!=null) {
			User user = (User) session.getAttribute("user");
			Integer id = user.getId();
			article.setUserId(id);
		}
		/**
		 * 初始化article对象
		 */
		article.setStatus(0);//待审核
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		int num=articleService.insertSelective(article);
		return num+"";
	}
	/**
	 * 
	 * @Title: articles 
	 * @Description: 查询文章列表
	 * @param article
	 * @param model
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Article article,Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize,HttpServletRequest request) {
		/**
		 * 获取登录用户ID,查询该用户发表的所有列表
		 */
		HttpSession session = request.getSession(false);
		if (session!=null) {
			User user = (User) session.getAttribute("user");
			Integer id = user.getId();
			article.setUserId(id);
		}
		PageInfo<Article> articles =	articleService.articles(article,page,pageSize);
		model.addAttribute("articles", articles.getList());
		model.addAttribute("article", article);
		//int[] nums = articles.getNavigatepageNums();
		model.addAttribute("info", articles);
		//model.addAttribute("num",nums);
		return "my/article/articles";
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
		return "my/article/article";
	}
	/**
	 * 
	 * @Title: comment 
	 * @Description: 查询登录后用户的所有评论
	 * @param model
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("comment")
	public String comment(Model model,HttpServletRequest request,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "4")Integer pageSize) {
		HttpSession session = request.getSession(false);
		if (session!=null) {
			User user = (User) session.getAttribute("user");
			PageInfo<Comment> info=commentService.selectCommentByUserId(user,page,pageSize);
			model.addAttribute("info", info);
		}
		
		return "my/article/comment";
	}
	
	@GetMapping("publishpic")
	public String publishpic() {
		return "my/article/publishpic";
	}
	@PostMapping("publishpic")
	@ResponseBody
	public boolean publishpic(ArticleWithBLOBs article,MultipartFile[] files,String[] descr,HttpServletRequest request) {
		String newFileName=null;
		List<ArticleVO> list=new ArrayList<ArticleVO>();
		int i=0;
		for (MultipartFile file : files) {
			ArticleVO vo=new ArticleVO();
			//获取文件上传路径
			String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
			//获取上传文件名
			String oldFileName=file.getOriginalFilename();
			//使用UUID重命名
			newFileName=UUID.randomUUID()+oldFileName.substring(oldFileName.indexOf("."));
			File f=new File(path,newFileName);
			vo.setUrl(newFileName);
			vo.setDescr(descr[i]);
			i++;
			list.add(vo);
			try {
				file.transferTo(f);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Gson gson=new Gson();
		//把list转化成json类型
		String json = gson.toJson(list);
		article.setContent(json);
		HttpSession session = request.getSession(false);
		if (session==null) {
			return false;
		}
		User user=(User) session.getAttribute("user");
		article.setUserId(user.getId());
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		article.setContentType(ArticleEnum.IMAGE.getCode());
		if (articleService.insertSelective(article)!=0) {
			return true;
		}else {
			return false;
		}
	}
}
