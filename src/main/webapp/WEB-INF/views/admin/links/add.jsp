<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="/resource/css/jquery/screen.css">
</head>
<body>
	<form id="form1">
	<hr>
			<input type="text" class="form-control-plaintext" style="width: 450px;" placeholder="请输入要添加的网址" name="url">
			<hr>
			<input type="text" class="form-control-plaintext" style="width: 250px;" placeholder="请输入网站名称" name="text">
			<hr>
			<button type="button" style="float: left;" class="btn btn-info" onclick="save()">添加</button>
	</form>
	<script type="text/javascript">
	
	function save(){
		$.post("/links/add",$("#form1").serialize(),function(flag){
			if(flag.code==0){
				alert(flag.msg);
				$("#center").load("/links/selects");
			}else{
				alert(flag.msg);
			}
		})
	}
	</script>
</body>
</html>