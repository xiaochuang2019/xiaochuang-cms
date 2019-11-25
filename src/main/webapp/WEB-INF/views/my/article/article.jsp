<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			<button type="button" class="btn btn-info" onclick="back()">返回列表</button>
			</dd>
			<dd><fmt:formatDate value="${article.updated }" pattern="yyyy-MM-dd HH:mm:ss"/> </dd>
			<dd>${article.content }</dd>

		</dl>
		</div>
<script type="text/javascript">
	 function back(){
		 $("#center").load("/my/articles")
	 }
	
	</script>
</body>	
</html>