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

	<dl>
		<dt>
			<h2>${article.title }</h2>
		</dt>

		<hr>
		<dd>
			<fmt:formatDate value="${article.updated }"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</dd>
		<dd>${article.content }</dd>

	</dl>
</body>	
</html>