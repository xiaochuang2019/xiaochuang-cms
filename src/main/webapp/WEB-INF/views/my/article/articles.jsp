<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title><!-- 
<link href="/resource/css/bootstrap.min.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="/resource/js/turnpage.js"></script> -->
<link type="text/css" rel="stylesheet" href="/resource/css/jquery/screen.css">
</head>
<script type="text/javascript">
	$(function() {
	$("#status").val('${article.status}');
	})
	function goPage(page) {
	var url = "/my/articles?page=" + page + "&title="
			+ $("[name='title']").val()
	$("#center").load(url);
	}
	function query() {
		$("#center").load("/my/articles?title="+$("#title").val()+"&status="+$("#status").val());
	}
	
	function detail(id) {
		$("#center").load("/my/article?id="+id);
	}
</script>
<body>
	<div class="form-group form-inline"
		style="margin-top: 5px; float: right; margin-right: 5px">
		<label for="title"> 文章标题:</label> <input id="title" type="text"
			class="form-control form-control-sm" name="title"
			value="${article.title }">&nbsp;&nbsp; 文章状态: <select
			class="form-control form-control-sm" name="status" id="status">
			<option value="0">待审</option>
			<option value="1">已审</option>
			<option value="-1">驳回</option>
			<option value="">全部</option>
		</select>&nbsp;
		<button type="button" class="btn btn-success btn-sm" onclick="query()">查询</button>
	</div>
	<table class="table table-hover table-bordered"
		style="text-align: center;">
		<thead class="thead-dark">
			<tr>
				<th>序号</th>
				<th>文章标题</th>
				<th>作者</th>
				<th>是否热门</th>
				<th>所属栏目</th>
				<th>所属分类</th>
				<th>更新时间</th>
				<th>文章状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${articles}" var="a" varStatus="i">
			<tr>
				<td>${(info.pageNum-1) * info.pageSize+i.index+1 }</td>
				<td>${a.title }</td>
				<td>${a.user.username}</td>
				<td><c:if test="${a.hot==1}">
						热门
					</c:if> <c:if test="${a.hot==0}">
						冷门
					</c:if></td>
				<td>${a.category.name}</td>
				<td>${a.channel.name}</td>
				<td><fmt:formatDate value="${a.updated }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${a.status==0?'待审':a.status==1?'已审':'驳回'}</td>	
				<td><button type="button" class="btn btn-warning"
						onclick="detail(${a.id})">详情</button></td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="/WEB-INF/views/common/pages.jsp"><jsp:param
			value="articles" name="url" /></jsp:include>
</body>
</html>