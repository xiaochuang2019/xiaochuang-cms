package com.xiaochuang.cms.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class MyInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Object object = session.getAttribute("user");
			if (object!=null) {
				return true;
			}
		}
		request.setAttribute("message","请登陆后重试");
		request.getRequestDispatcher("/WEB-INF/views/passport/login.jsp").forward(request, response);
		return false;
	}
}
