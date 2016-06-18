<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String contextPath = request.getContextPath();
	if(contextPath == "/"){
		contextPath = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="text-left">
					<img alt="" class="navbar-brand"
						src="${LOGINUSER.pictureUrl }"> <a
						class="navbar-brand" href="<%=contextPath %>/index">首页</a>
				</div>
				<!-- <a class="navbar-brand" href="#">Forum</a> -->
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="display: none;" id="loginedUserId" >${LOGINUSER.id }</li>
					<li><a href="<%=contextPath %>/login/loginPage" id="loginLink"
						onclick="checkIfLogined()">${LOGINUSER != null ? LOGINUSER.userName : '登录' }</a></li>
					<li><a href="<%=contextPath %>/registerPage" id="registerLink"
						onclick="checkIfLogined()">${LOGINUSER != null ? '注销' : '注册' }</a></li>
					<li><a href="javascript:void(0);" onclick="waiting();">设置</a></li>
					<li><a href="javascript:void(0);" onclick="waiting();">帮助</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>
</body>
</html>