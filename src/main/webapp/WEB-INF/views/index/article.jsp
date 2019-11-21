<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/resource/css/bootstrap.min.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/js/turnpage.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container" style="text-align: center;">
	<dl>
		<dt>
			<h2>${article.title }</h2>
		</dt>

		<hr>
		<dd>
			作者:${article.user.username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布日期:<fmt:formatDate value="${article.updated }"
				pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;浏览量:${article.hits}
		</dd>
		<dd>${article.content }</dd>

	</dl>
</div>
</body>	
</html>