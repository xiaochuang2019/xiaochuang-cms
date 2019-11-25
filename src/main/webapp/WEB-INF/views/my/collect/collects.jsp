<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>收藏列表</title>
<script type="text/javascript">

//分页
	function goPage(page) {
	var url = "/my/collects?page=" + page 
	$("#center").load(url);
	}

	function deleteCollect(id){
	$.post("/my/deleteCollect",{id:id},function(flag){
		if(flag.code==0){
			alert(flag.msg)
			$("#center").load("/my/collects");
		}else{
			alert(flag.msg)
		}
	})
	}
</script>
</head>
<body class="container">

	<ul class="list-group">
		<li class="list-group-item list-group-item-action list-group-item-info" aria-disabled="true"><h3>我的收藏夹</h3></li>
		<c:forEach items="${info.list}" var="c">
		<li class="list-group-item" style="font-size: 17px;">
		 <a href="${c.url}" target="_blank">${c.text }</a><br>
		 <fmt:formatDate value="${c.created}" pattern="yyyy-MM-dd HH:mm:ss" />
		 &nbsp;
		 <button class="btn btn-info btn-sm" onclick="deleteCollect(${c.id})">删除</button>
		 </li>
		</c:forEach>
	</ul>
	<jsp:include page="/WEB-INF/views/common/pages.jsp"></jsp:include>
</body>
</html>