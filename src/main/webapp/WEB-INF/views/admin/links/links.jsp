<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>友情链接管理</title>
</head>
<body>
	<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">链接ID</th>
      <th scope="col">链接名称</th>
      <th scope="col">链接地址</th>
      <th scope="col">添加时间<a href="#" onclick="add()" style="float: right;font-size: 20px;">添加链接</a></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${info.list}" var="l" varStatus="i">
    <tr>
      <th scope="row">${(info.pageNum-1)*(info.pageSize)+i.index+1}</th>
      <td>${l.text}</td>
      <td>${l.url}</td>
      <td>
      <fmt:formatDate value="${l.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
      </td>
    </tr>
   </c:forEach>
  </tbody>
</table>
  <jsp:include page="/WEB-INF/views/common/pages.jsp"></jsp:include>
  <script type="text/javascript">
  function goPage(page) {
		var url = "/links/selects?page=" + page ;
		$("#center").load(url);
		}
  function add() {
	var url="/links/toadd";
	$("#center").load(url);
  }
  </script>
</body>
</html>