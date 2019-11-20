<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员页面</title>

	
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<!-- 	<link rel="stylesheet" type="text/css"
	href="/resource/open-iconic/font/css/open-iconic-bootstrap.css" /> -->
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>


<script type="text/javascript"
	src="/resource/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row" style="margin-top: 2px; min-height: 50px">
			<div class="col-md-12" style="background-color: #000">
				<img alt="" src="/camera/logo.jpg" class="rounded-circle">
					<a class="navbar-brand mr-1" href="index.html">CMS系统后台</a>

				<ul>
				
					<c:choose>
						<%-- 登录显示用户菜单 --%>
						<c:when test="${sessionScope.admin != null}">
							<li class="nav-item">

								<div class="dropdown" style="padding-top: 0.1rem;">
									<a href="#" class="nav-link dropdown-toggle" role="button"
										id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <c:out
											value="${admin.username}" default="cms-User" />
									</a>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<a class="dropdown-item" href="/passport/logout">退出</a>
									</div>
								</div>
							</li>
						</c:when>
						<c:otherwise>
							<%-- 未登录显示登录注册链接 --%>
							<li class="nav-item"><a class="nav-link"
								href="/passport/reg">注册</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/passport/login">登录</a></li>
						</c:otherwise>
					</c:choose>
				</ul>


			</div>
		</div>
		<div class="row"
			style="margin-top: 2px; min-height: 300px; font-size: 20px; text-align: center;">
			<div class="col-md-2"
				style="background-color: white; margin-top: 50px">
				<div class="nav flex-column nav-pills" id="v-pills-tab"
					role="tablist" aria-orientation="vertical">
					<a class="nav-link active" id="v-pills-home-tab" data-toggle="pill"
						href="#v-pills-home" role="tab" aria-controls="v-pills-home"
						aria-selected="true" onclick="users()">用户管理</a> <a
						class="nav-link" id="v-pills-profile-tab" data-toggle="pill"
						href="#v-pills-profile" role="tab" aria-controls="v-pills-profile"
						aria-selected="false" onclick="articles()">文章管理</a> <a
						class="nav-link" id="v-pills-messages-tab" data-toggle="pill"
						href="#v-pills-messages" role="tab"
						aria-controls="v-pills-messages" aria-selected="false">Messages</a>
					<a class="nav-link" id="v-pills-settings-tab" data-toggle="pill"
						href="#v-pills-settings" role="tab"
						aria-controls="v-pills-settings" aria-selected="false">Settings</a>
				</div>
			</div>
			<div class="col-md-10" style="background-color: white;" id="center">
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function articles() {
		$("#center").load("/article/articles");
	}
		function users() {
			$("#center").load("/user/users");
		}
	</script>
</body>
</html>