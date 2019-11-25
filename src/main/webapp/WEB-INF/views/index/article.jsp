<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				
				<!-- 文章 -->
				<h2 align="center">${article.title}</h2>
				<div class="text-center">
					作者：${article.user.username}&nbsp;&nbsp;&nbsp;&nbsp;
					浏览：${article.hits}&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="mc">
					<c:if test="${isCollect!=1}">
						<a href="javascript:collect()"><span
							style="font-size: 18px; color: rgb(202, 202, 202);">☆收藏</span></a>
					</c:if>
					<c:if test="${isCollect==1}">
						<span style="font-size: 18px; color: rgb(255, 189, 29);">☆已收藏</span>
					</c:if>
					</span>
				</div>
				<hr/>
				<div class="content">
					${article.content}
				</div>
				<div class="text-right">发布时间：<fmt:formatDate value="${article.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				
				<hr/>
				
				<h4>最新评论</h4>
				<div class="comments" id="comments">
					 <c:forEach items="${comments}" var="comment">
						<div class="media">
						  <div class="media-left">
						    <a href="#">
						      <img class="media-object" src="/camera/logo.jpg" style="max-height: 50px" alt="...">
						    </a>
						  </div>
						  <div class="media-body">
						    <h4 class="media-heading">${comment.author.username}：</h4>
						    <p>${comment.content}</p>
						    <p>评论时间：<fmt:formatDate value="${comment.created}" pattern="yyyy-MM-dd"/></p>
						  </div>
						</div>
					</c:forEach>
				</div>
				<div>
					<form id="comment" name="comment" method="post">
						<textarea id="content" name="content" cols="3" class="form-control" placeholder="${user.username}发表评论"></textarea>
						<button type="submit" class="btn btn-info btn-block">发表</button>
					</form>
				</div>
				<br/>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	function collect() {
		var text = '${article.title}';//收藏的标题
		var url = window.location.href;//收藏的地址

		$.post("/collect/collect", {
			text : text,
			url : url
		}, function(flag) {
			if (flag.code == 0) {
				alert("收藏"+flag.msg);
				$("#mc").html(
						"<span style='font-size: 18px; color: rgb(255, 189, 29);'>☆已收藏</span>")
			} else {
				alert(flag.msg);

			}
		})

	}
	</script>
	
	<script type="text/javascript">
	
		var authorNickname = "${user.username}";
		
		var template = "<div class=\"media\">"
		  +"<div class=\"media-left\">"
		  +"<a href=\"#\">"
		  +"<img class=\"media-object\" src=\"/camera/logo.jpg\" style=\"max-height: 50px\" alt=\"...\">"
		  +"</a>"
		  +"</div>"
		  +"<div class=\"media-body\">"
		  +"<h4 class=\"media-heading\">${user.username}：</h4>"
		  +"<p>{{comment_content}}</p>"
		  +"<p>评论时间：刚刚</p>"
		  +"</div>"
		  +"</div>";
		  
		$(document).ready(function(){
			if(authorNickname.length==0){
				$("#content").attr("disabled", "disabled").attr("placeholder", "请登录后发表评论");
			}
			
			$("#comment").submit(function(){
				var content = $("#content").val();
				if(content.length==0){
					alert('请填写评论内容');
					return false;
				}
				
				$.ajax({
					url:'/comm/comment?id=' + '${article.id}',
					type:'post',
					data:$(this).serialize(),
					error: function(){alert('发表失败');},
					success:function(data){
						/* if(data.status){ */
						if(data){
							var comments = $("#comments").html();
							$("#comments").html(template.replace("{{comment_content}}", content) + comments);
							$("#content").val("");
						}else{
							/* alert(data.message); */
							alert("评论失败！")
						}
					}
					
				},"json");
				return false;
			});
		});
	</script>
</body>	
</html>