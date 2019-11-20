<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cms系统</title>
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<link href="/resource/css/bootstrap.min.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
	<div class="container">
		<div class="row" style="margin-top: 5px">
			<!-- 左侧栏目菜单 -->
			<div class="col-md-2">
				<div class="list-group">
					<ul class="list-group text-center">
						<li
							class="list-group-item cms-list-group-item-action ${article.channelId==null?"cms-list-group-item-active":"" }">
							<a href="/index" class="channel"> 热点 </a>
						</li>
						<!--遍历栏目 -->
						<c:forEach items="${channels}" var="c">
							<li
								class="list-group-item cms-list-group-item-action ${article.channelId==c.id?"cms-list-group-item-active":"" }">
								<a href="/index?channelId=${c.id }" class="channel">
									${c.name } </a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- 中间栏目文章 -->
			<div class="col-md-7 split min_h_500">
				<c:if test="${article.channelId==null }">
					<!-- 轮播图 -->
					<div>
						<div id="carouselExampleCaptions" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carouselExampleCaptions" data-slide-to="0"
									class="active"></li>
								<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
								<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img src="/resource/pic/1.jpg" class="d-block w-100" alt="...">
									<div class="carousel-caption d-none d-md-block">
										<p>图片一</p>
									</div>
								</div>
								<div class="carousel-item">
									<img src="/resource/pic/2.jpg" class="d-block w-100" alt="...">
									<div class="carousel-caption d-none d-md-block">
										<p>图片二</p>
									</div>
								</div>
								<div class="carousel-item">
									<img src="/resource/pic/3.jpg" class="d-block w-100" alt="...">
									<div class="carousel-caption d-none d-md-block">
										<p>图片三</p>
									</div>
								</div>
							</div>
							<a class="carousel-control-prev" href="#carouselExampleCaptions"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
						<hr>
						<c:forEach items="${info.list}" var="c">
							<hr>
							<div class="media">
								<div class="media-body">
									<h5 class="mt-0 mb-1">
										<a href="/index/article?id=${c.id }" target="_blank">${c.title }</a>
									</h5>
									<p>${c.user.username}</p>
									<fmt:formatDate value="${c.updated }"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</div>
								<img src="/resource/pic/${c.picture}" class="ml-3"
									style="height: 101px; width: 156px" alt="...">
							</div>
						</c:forEach>
					</div>
				</c:if>
				<!-- 如果有channelId,展示横向导航栏及选中类型文章 -->
				<c:if test="${article.channelId!=null }">
					<ul class="nav">
						<li class="nav-item"><a
							class="nav-link cms-list-group-item-action ${article.categoryId==null?"
							cms-list-group-item-active":"" }" href="/index?channelId=${article.channelId}">全部</a>
							<c:forEach items="${categorys}" var="c">
								<li class="nav-item"><a
									class="nav-link cms-list-group-item-action ${article.categoryId==c.id?"
									cms-list-group-item-active":"" }" href="/index?channelId=${article.channelId}&categoryId=${c.id}">${c.name}</a>
							</c:forEach>
					</ul>
					<div>
							<hr>
							<c:forEach items="${info.list}" var="h">
								<div class="media">
									<img src="/resource/pic/${h.picture}" class="mr-3" alt="..."
										style="height: 101px; width: 156px">
									<div class="media-body">
										<h3>
											<strong><a href="/index/article?id=${h.id }"
												target="_blank">${h.title }</a></strong>
										</h3>
										<p style="margin-top: 10px">
											${h.user.username}
											<fmt:formatDate value="${h.updated }"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</p>
									</div>
								</div>
								<hr>
							</c:forEach>

						</div>
				</c:if>
				<jsp:include page="/WEB-INF/views/common/pages.jsp"></jsp:include>
			</div>
			<!-- 右侧 -->
			<div class="col-md-3 split min_h_500">
				<!-- 最近发布的5篇文章 -->
				<div class="card" style="width: 18rem;">
					<div class="card-header">最新文章</div>
					<div class="card-body">
						<c:forEach items="${lastInfo.list}" var="a">
							<p>
								<a href="/index/article?id=${a.id }" target="_blank">${a.title }</a>

							</p>
						</c:forEach>
					</div>
				</div>
				</div>
		</div>
		
		<script type="text/javascript">
		//分页
		function goPage(page) {
			var url = "/index?page=" + page
			location = url;
		}
	</script>
</body>
</html>