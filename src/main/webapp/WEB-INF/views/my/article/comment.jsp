<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>最新评论</title>
</head>
<body>
	<h4>最新评论</h4>
	<div class="comments" id="comments">
		<c:forEach items="${info.list}" var="comment">
			<div class="media">
				<div class="media-left">
					<a href="#"> <img class="media-object" src="/camera/logo.jpg"
						style="max-height: 50px" alt="...">
					</a>
				</div>
				<div class="media-body">
					<h4 class="media-heading">${comment.author.username}：</h4>
					<p>${comment.content}</p>
					<p>
						评论时间：
						<fmt:formatDate value="${comment.created}" pattern="yyyy-MM-dd" />
					</p>
				</div>
			</div>
		</c:forEach>
		<jsp:include page="/WEB-INF/views/common/pages.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
	function goPage(page) {
		var url = "/my/comment?page=" + page ;
		$("#center").load(url);
		}
	</script>
</body>
</html>