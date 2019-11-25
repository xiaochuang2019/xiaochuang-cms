package com.xiaochuang.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.service.UserService;
@RequestMapping("user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @Title: users 
	 * @Description: 管理员查询用户列表
	 * @param user
	 * @param model
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("users")
	public String users(User user,Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		PageInfo<User> users = userService.users(user, page, pageSize);
		model.addAttribute("users",users.getList());
		model.addAttribute("user",user);
		int[] nums = users.getNavigatepageNums();
		model.addAttribute("info", users);
		model.addAttribute("num",nums);
		return "admin/user/users";
	}
	/**
	 * 
	 * @Title: update 
	 * @Description: 禁用以及启动用户
	 * @param user
	 * @return
	 * @return: String
	 */
	@RequestMapping("update")
	@ResponseBody
	public	String update(User user) {
		int num=userService.update(user);
		return num+"";
	}
}
