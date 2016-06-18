package com.forum.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.User;
import com.forum.service.UserService;

//用户注册的控制器
@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	//用户注册的页面
	@RequestMapping(value="/registerPage")
	public String registerPage(){
		return "/registerPage";
	}
	
	//用户注册
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, User user){
		ModelAndView view = new ModelAndView();
		switch(userService.register(user)){
		case CORRECT_USER :
			view.setViewName("redirect:/index");
			break;
		case USERNAME_EXISTED :
			view.setViewName("/registerPage");
			view.addObject("errorMsg", "用户名已存在！");
			break;
		default:
			break;
		}
		return view;
	}
}
