var contextPath = '/forum_1';

function waiting(){
	layer.msg("请等待后续完善...");
}

function checkRegisterInfo(){
	var username = $("#inputUserName").val();
	var trim_username = $.trim(username);
	var password = $("#inputPassword").val();
	if(trim_username.length < 6 || trim_username.length > 8){
		alert("账号应6~8位!");
		return false;
	}
	if(password.length < 6 || password.length > 12){
		alert("密码应6~12位!");
		return false;
	}
	return true;
}

function checkIfLogined(){
	var loginButtonText = $("#loginLink").text();
	if(loginButtonText != '登录'){					//已经登录
		var userId = $("#loginedUserId").text();
		$("#loginLink").attr('href', contextPath + '/showUserInformation/' + userId);
	}else{											//还未登录
		$("#loginLink").attr('href', contextPath + '/login/loginPage');
	}
	
	var registerButtonText = $("#registerLink").text();
	if(registerButtonText == '注册'){
		$("#registerLink").attr('href',contextPath + '/registerPage');
	}else if(registerButtonText == '注销'){
		$("#registerLink").attr('href',contextPath + '/login/doLogout');
	}
}

function initSearchBoardAndTopic(){
	//查询并列出所有版块及对应主题帖
	var boardUrl = '/searchAllBoards';
	var boardReqType = 'GET';
	var boardParams = new Object();
	boardParams.page = 1;
	boardParams.size = 10;
	commonAjax(boardUrl, boardParams, boardReqType, listAllBoardsAndTopic);
	
}

function listAllBoardsAndTopic(data){
	$('#boardList').empty();
	var html = '';
	$.each(data,function(key, value){
		html += '<li><a href="javascript:void(0);"'
			 + ' onclick="changeBoardColor('+ key + ',\'' + value.id +'\')">' 
			 + value.boardName + '</a></li>';
	});
	$('#boardList').append(html);
	$('#boardList li:first').addClass('active');
	
	//对添加主题帖的链接进行初始化
	$("#posting").attr('href', contextPath + '/board/addTopicPage/' + data[0].id);
	
	//对管理主题帖的链接进行初始化
	$("#updateBoardPage").attr('onclick', 'updateBoardPage("' + data[0].id + '")');
	
	//查询并展示初始版块下的主题帖
	listBoardTopic(data[0].id);
}

//查询并展示某版块下的主题帖
function listBoardTopic(boardId){
	var boardUrl = '/board/listBoardTopics/' + boardId;
	var boardReqType = 'GET';
	var boardParams = new Object();
	boardParams.page = 1;
	boardParams.size = 10;
	commonAjax(boardUrl, boardParams, boardReqType, listTopics);
}

//列出初始版块下的主题帖
function listTopics(data){
	var html = '';
	$("tbody").empty();
	$.each(data, function(key, value){
		html += '<tr><td><a href="showTopicDetails/' + value.id + '">' 
			 + value.topicTitle + '</a></td><td>' 
			 + value.user.userName + '</td></tr>';  
	});
	$("tbody").append(html);
}


//单机触发版块的背景颜色变化
function changeBoardColor(key, boardId){
	$('#boardList li').removeClass('active');
	$('#boardList li:eq('+ key + ')').addClass('active');
	
	//根据选择的版块，对添加主题帖的链接进行更改
	$("#posting").attr('href', contextPath + '/board/addTopicPage/' + boardId);
	
	//根据选择的板块，对管理主题帖的事件进行更改
	$("#updateBoardPage").attr('onclick', 'updateBoardPage("' + boardId + '")');
	
	//根据所选的版块，对展示的主题帖进行更改
	listBoardTopic(boardId);
}


//检查提交的主题帖子信息是否正确
function checkTopicInfo(){
	var topicTitle = $("#topicTitle").val();
	var topicTitle_trim = $.trim(topicTitle);
	var topicContent = $("#topicContent").val();
	var topicContent_trim = $.trim(topicContent);
	
	var topicTitleLen = topicTitle_trim.length;
	if(topicTitleLen > 20){
		alert("标题不能为空,且应小于20个字");
		return false;
	}
	if(topicContent_trim == ''){
		alert("正文内容不能为空");
		return false;
	}
	return true;
}

//检查提交的版块信息是否正确
function checkBoardInfo(){
	var boardName = $("#boardName").val();
	var boardDesc = $("#boardDesc").val();
	var boardName_trim = $.trim(boardName);
	var boardDesc_trim = $.trim(boardDesc);
	if(boardName_trim.length > 10 || boardName_trim == ""){
		alert("名称应在1~10个字之间");
		return false;
	}
	return true;
}

//管理板块页面
function updateBoardPage(boardId){
	layer.open({
		title : '管理板块',
		type : 2,
		content : 'forum/updateBoardPage/' + boardId,
		area : ['600px', '335px'],
	});
}

//新增板块页面
function addBoardPage(){
	layer.open({
		title : '新建板块',
		type : 2,
		content : 'forum/addBoardPage',
		area : ['600px', '335px'],
	});
}

//更新板块信息
function updateBoardInfo(){
	if(!checkBoardInfo()){	//检查板块信息是否符合要求
		return false;
	}
	var boardId = $("#boardId").val();
	var boardName = $("#boardName").val();
	var boardDesc = $("#boardDesc").val();
	var url = '/forum/addOrUpdateBoard';
	var params = new Object();
	params.id = boardId;
	params.boardName = boardName;
	params.boardDesc = boardDesc;
	
	commonAjax(url, params, 'POST', showResult);
}

//新增板块信息
function addBoardInfo(){
	if(!checkBoardInfo()){	//检查板块信息是否符合要求
		return false;
	}
	var boardName = $("#boardName").val();
	var boardDesc = $("#boardDesc").val();
	var url = '/forum/addOrUpdateBoard';
	var params = new Object();
	params.boardName = boardName;
	params.boardDesc = boardDesc;
	
	commonAjax(url, params, 'POST', showResult);
}

//进行添加修改板块后的后续处理
function showResult(data){
	var message = data['message'];
	layer.msg(message);
	window.setTimeout(function() {
		closeLayer();
	}, 500);
	
	parent.initSearchBoardAndTopic();
}

function closeLayer(){
	//当你在iframe页面关闭自身时
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭 
}

//删除板块
function deleteBoard(boardId){
	layer.confirm('删除板块后导致其下的帖子全被删除，确定？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		//进行删除操作
		var url = '/forum/deleteBoard';
		var params = new Object();
		params.boardId = boardId;
		commonAjax(url, params, 'GET', showResult);
		
	}, function(){
	    
	});
}

function commonAjax(url,params,reqType,fun){
	$.ajax({
        type: reqType,
        dataType:'json',
        url: contextPath + url,
        data:params,
        async: true,
//        contentType : 'application/json;charset=utf-8',
        success : function(data) {
        	eval(fun(data));
        },
        complete : function(XMLHttpRequest, textStatus) {
        },
        error : function(data) {
        	layer.msg('请求错误', 1, 2);
        }
    });
}