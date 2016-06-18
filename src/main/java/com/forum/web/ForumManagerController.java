package com.forum.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.Board;
import com.forum.entity.User;
import com.forum.service.ForumService;
import com.forum.service.UserService;
import com.forum.util.ResultSet;

/*
 * 论坛管理的控制器
 * 包括添加论坛版块、指定论坛版块管理员、对用户进行锁定/解锁
 */
@Controller
public class ForumManagerController {
	
	private Logger logger = Logger.getLogger(ForumManagerController.class);

	@Autowired
	private ForumService forumService;
	
	@Autowired
	private UserService userService;
	
	//进入首页
	@RequestMapping(value="/index", method=RequestMethod.GET )
	public String index(){
		return "/listAllBoards";
	}	
	
	//列出所有的论坛模块
	@RequestMapping(value="/searchAllBoards", method=RequestMethod.GET, produces="application/json" )
	public @ResponseBody List<Board> listAllBoards(HttpServletRequest request){
		return forumService.getAllBoards();
	}
	
	//添加一个版块的页面
	@RequestMapping(value="/forum/addBoardPage",method=RequestMethod.GET)
	public String addBoardPage(){
		return "/addBoardPage";
	}
	
	//添加或修改一个版块
	@RequestMapping(value="/forum/addOrUpdateBoard", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ResultSet addBoard( Board board){
		logger.info("addOrUpdateBoard is : " + board);
		boolean res;
		ResultSet result = new ResultSet();
		if(board.getId() == null){  //进行新增板块
			res= forumService.ifExistBoardName(board);
			result.setMessage("板块添加成功");
		}else{						//进行修改板块
			res = false;
			result.setMessage("板块修改成功");
		}
		if(res){		//存在同名版块
			result.setStateCode(1);
			result.setMessage("已存在同名板块");
		}else{
			forumService.addBoard(board);
			result.setStateCode(0);
		}
		return result;
	}
	
	//删除版块
	@RequestMapping(value="/forum/deleteBoard", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ResultSet deleteBoard(String  boardId){
		logger.info("deleteBoard id is : " + boardId);
		ResultSet result = new ResultSet();
		forumService.deleteBoard(boardId);
		result.setStateCode(0);
		result.setMessage("删除板块成功");
		return result;
	}
	
	//管理板块页面
	@RequestMapping(value="/forum/updateBoardPage/{boardId}", method=RequestMethod.GET)
	public ModelAndView updateBoardPage(@PathVariable String boardId){
		//TODO
		ModelAndView view = new ModelAndView();
		
		Board board = forumService.getBoardById(boardId);
		view.addObject("board", board);
		view.setViewName("/updateBoardPage");
		return view;
	}
	
	//指定论坛管理员的页面
	public ModelAndView setBoardManagerPage(){
		ModelAndView view = new ModelAndView();
		List<Board> boards = forumService.getAllBoards();
		List<User> users = userService.getAllUsers();
		view.addObject("boards", boards);
		view.addObject("users", users);
		view.setViewName("/setBoardManager");
		return view;
	}
}
