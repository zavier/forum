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

    <title>管理版块</title>

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

      <form>
        <div class="form-group sr-only">
		    <label for="boardId" class="sr-only">boardId</label>
		    <input type="text" class="form-control" id="boardId" name="id" value="${board.id }" />
		</div>
        <div class="form-group">
		    <label for="boardName" class="sr-only">boardName</label>
		    <input type="text" class="form-control" id="boardName" name="boardName" value="${board.boardName }" />
		</div>
		<c:if test="${!empty errorMsg }">
			<div style="color:red">${errorMsg }</div>
		</c:if>
		<div class="form-group">
		    <label for="boardDesc" class="sr-only">boardDesc</label>
		    <textarea rows="5" class="form-control" id="boardDesc" name="boardDesc" >${board.boardDesc }</textarea>
		</div>
        <button id="submit_button" class="btn btn-primary" type="button" onclick="updateBoardInfo();">更改板块信息</button>
        <button id="delete_button" class="btn btn-primary" type="button" onclick="deleteBoard('${board.id }');">删除板块</button>
      </form>
    </div> <!-- /container -->

	<script src="<%=contextPath %>/js/jquery.min.js"></script>
	<script src="<%=contextPath %>/my/my.js"></script>
	<script src="<%=contextPath %>/layer/layer.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=contextPath %>/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
