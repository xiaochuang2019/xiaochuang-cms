package com.xiaochuang.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaochuang.cms.domain.User;
import com.xiaochuang.cms.domain.UserVo;
import com.xiaochuang.cms.service.UserService;
import com.xiaochuang.cms.utils.CMSException;

@RequestMapping("passport")
@Controller
public class PassportController {
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @Title: login 
	 * @Description: 前往登陆页面
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login() {
		return "/passport/login";
	}
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 * @return: String
	 */
	@PostMapping("login")
	public String login(User user, Model model, HttpSession session) {
			User u = userService.login(user);
			/**
			 * 判断用户是否被禁用
			 */
			if(u.getLocked()==0) {
				model.addAttribute("message", "该用户已被禁用,有问题请联系管理员");
				return "passport/login";
			}
			/**
			 * 判断用户是否是管理员
			 */
			if (u.getRole().equals("1")) {
				session.setAttribute("admin", u);
				return "/admin/index";
			}
			else {
				session.setAttribute("user", u);
				return "redirect:/index/index";
			}
	}
	/**
	 * 
	 * @Title: reg 
	 * @Description: 前往注册页面
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {
		return "/passport/reg";
	}
	/**
	 * 
	 * @Title: reg 
	 * @Description: 注册
	 * @param vo
	 * @param redirectAttributes
	 * @param model
	 * @return
	 * @return: String
	 */
	@PostMapping("reg")
	public String reg(UserVo vo, RedirectAttributes redirectAttributes, Model model) {
				userService.insertUser(vo);
				redirectAttributes.addFlashAttribute("username", vo.getUsername());
				redirectAttributes.addFlashAttribute("message", "注册成功");

				return "redirect:/passport/login";
	}
	/**
	 * 
	 * @Title: logout 
	 * @Description: 退出登陆,清除session域
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session!=null) {
			session.invalidate();
		}
		return "/passport/login";
	}
}
