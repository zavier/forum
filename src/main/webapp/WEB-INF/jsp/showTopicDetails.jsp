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
    <!-- <link rel="icon" href="../../favicon.ico"> -->

    <title>帖子信息</title>

	<link href="<%=contextPath %>/my/my.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath %>/css/dashboard.css" rel="stylesheet">

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

    <jsp:include page="commonNavigationBar.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12 col-md-12 main">
          <div class="table-responsive">
          <h4 class="topicTitle">${topic.topicTitle }</h4>
            <table class="table table-striped table-bordered">
              <tbody>
              	<tr>
              		<td class="col-sm-2" align="center">
              			<div>
              				<img alt="" src="${topic.user.pictureUrl }" width="150" height="150">
              			</div>
              			<div>
              				${topic.user.userName }
              			</div>
              		</td>
              		<td class="col-sm-10">
              			<div><span class="right">
								<a href="javascript:void(0);">楼主</a>
							</span>
							<span class="left"> 发表于： ${topic.createTime } </span></div>
						<hr>
              			<div class="topic_body">${topic.topicContent }</div>
              		</td>
              	</tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=contextPath %>/js/jquery.min.js"></script>
    <script src="<%=contextPath %>/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="<%=contextPath %>/assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=contextPath %>/assets/js/ie10-viewport-bug-workaround.js"></script>
    <!-- 引入layer -->
    <script src="<%=contextPath %>/layer/layer.js"></script>
    
    <script src="<%=contextPath %>/my/my.js"></script>
    
  </body>
</html>
