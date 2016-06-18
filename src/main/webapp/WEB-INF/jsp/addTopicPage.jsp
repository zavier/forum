<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String contextPath = request.getContextPath();
	if(contextPath == "/"){
		contextPath = "";
	}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>发帖</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath %>/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="<%=contextPath %>/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <form action="<%=contextPath %>/board/addTopic/${boardId}" method="post" >
        <h2 class="form-signin-heading col-md-4 col-md-offset-5">发帖</h2>
        <div class="form-group">
		    <label for="topicTitle" class="sr-only">topicTitle</label>
		    <input type="text" class="form-control" id="topicTitle" name="topicTitle" placeholder="在此输入标题"></input>
		</div>
        <div class="form-group">
		    <label for="topicContent" class="sr-only">topicContent</label>
		    <textarea rows="10" class="form-control" id="topicContent" name="topicContent" placeholder="在此输入正文内容"></textarea>
		</div>
        <button id="submit_button" class="btn btn-lg btn-primary btn-block" type="submit" onclick="return checkTopicInfo();">提交</button>
      </form>
    </div> <!-- /container -->

	<script src="<%=contextPath %>/js/jquery.min.js"></script>
	<script src="<%=contextPath %>/my/my.js"></script>
	<script src="<%=contextPath %>/layer/layer.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=contextPath %>/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
