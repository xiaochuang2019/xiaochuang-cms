package com.xiaochuang.cms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.Links;
import com.xiaochuang.cms.service.LinksService;
import com.xiaochuang.cms.utils.Result;
import com.xiaochuang.cms.utils.ResultUtil;
@RequestMapping("links")
@Controller
public class LinksController {
	@Autowired
	private LinksService linksService;
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 维护友情链接--列表
	 * @return
	 * @return: String
	 */
	@GetMapping("selects")
	public String selects(Model model,@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<Links> info = linksService.selects(page, pageSize);	
		model.addAttribute("info", info);
		return "admin/links/links";
	}
	/**
	 * 
	 * @Title: toAdd 
	 * @Description: 前往添加页面
	 * @return
	 * @return: String
	 */
	@GetMapping("toadd")
	public String toAdd() {
		return "admin/links/add";
	}
	@PostMapping("add")
	@ResponseBody
	public Result<Links> add(Links links) {
		String url="";
		links.setCreated(new Date());
		linksService.insert(links);
		return ResultUtil.success();
	}
}
