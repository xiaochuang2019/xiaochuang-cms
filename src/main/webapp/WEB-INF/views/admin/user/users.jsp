<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resource/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/js/turnpage.js"></script>
</head>
<script type="text/javascript">
	function query() {
		$("#center").load("/user/users?username="+$("#username").val());
	}
	function goPage(page) {
		var url = "/user/users?page=" + page + "&username="
				+ $("[name='username']").val()
		$("#center").load(url);
		}
	function update(id,locked,flag) {
		$.post(
			"/user/update",
			{id:id,locked:locked},
			function(obj) {
				var str="/user/users?page="+${info.pageNum};
				if (${user.username!=null}) {
					str+="&username=${user.username}";
				}
				if (obj!=0) {
					$("#center").load(str);
				}else{
					alert("操作失败");
				}
			}
			
		)
	}
</script>
<body>
	<div class="form-group form-inline" style="margin-top: 5px;float: right;margin-right: 5px">
			<label for="username"> 用户名:</label> <input id="username" type="text"
				class="form-control form-control-sm" name="username"
				value="${user.username }">&nbsp;
			<button type="button" class="btn btn-success btn-sm"
				onclick="query()">查询</button>
		</div>
	<table class="table table-hover table-bordered"  style="text-align: center;">
		<thead class="thead-dark">
		<tr>
			<th>姓名</th>
			<th>昵称</th>
			<th>性别</th>
			<th>生日</th>
			<th>创建时间</th>
			<th>注册时间</th>
			<th>更新时间</th>
			<th>操作</th>
		</tr>
		</thead>
		<c:forEach items="${users}" var="u" varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${u.username}</td>
			<td>${u.nickname}</td>
			<td>${u.gender==1?'男':'女'}</td>
			<td><fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd"/>
			</td>
			<td><fmt:formatDate value="${u.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${u.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>
				<c:if test="${u.locked==1}">
				<button type="button" class="btn btn-danger" id="qy" onclick="update(${u.id},0,this)">禁用</button>
				</c:if>
				<c:if test="${u.locked==0}">
				<button type="button" class="btn btn-success" id="jy" onclick="update(${u.id},1,this)">启用</button>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<jsp:include page="/WEB-INF/views/common/pages.jsp" ><jsp:param value="users" name="url"/></jsp:include>
</body>
</html>