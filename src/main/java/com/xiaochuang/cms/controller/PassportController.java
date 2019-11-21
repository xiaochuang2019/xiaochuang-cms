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

	@GetMapping("login")
	public String login() {
		return "/passport/login";
	}

	@PostMapping("login")
	public String login(User user, Model model, HttpSession session) {
		try {
			User u = userService.login(user);
			if(u.getLocked()==0) {
				model.addAttribute("message", "该用户已被禁用,有问题请联系管理员");
				return "passport/login";
			}
			if (u.getRole().equals("1")) {
				session.setAttribute("admin", u);
				return "/admin/index";
			}
			else {
				session.setAttribute("user", u);
				return "/my/index";
			}
		}catch (CMSException e) {
		e.printStackTrace();
		model.addAttribute("message",e.getMessage());
		} catch (Exception e) {
			model.addAttribute("message", "登录系统异常,请联系管理员");
		}
		return "passport/login";
	}

	@GetMapping("reg")
	public String reg() {
		return "/passport/reg";
	}

	@PostMapping("reg")
	public String reg(UserVo vo, RedirectAttributes redirectAttributes, Model model) {
		try {
			boolean b = userService.insertUser(vo);
			if (b) {
				redirectAttributes.addFlashAttribute("username", vo.getUsername());
				redirectAttributes.addFlashAttribute("message", "注册成功");

				return "redirect:/passport/login";
			}
		} catch (CMSException e) {
			model.addAttribute("message", e.getMessage());
		} catch (Exception e) {
			model.addAttribute("message", "系统故障请联系管理员");
		}
		return "/passport/reg";
	}
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session!=null) {
			session.invalidate();
		}
		return "/passport/login";
	}
}
