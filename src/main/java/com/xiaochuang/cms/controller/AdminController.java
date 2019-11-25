package com.xiaochuang.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("admin")
@Controller
public class AdminController {
	/**
	 * 
	 * @Title: index 
	 * @Description: 前往管理员首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		return "admin/index";
	}
}
