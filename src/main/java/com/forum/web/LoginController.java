package com.forum.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.User;
import com.forum.service.UserService;

//用户登录、注销的控制器
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	//用户登录页面
	@RequestMapping(value="/login/loginPage")
	public String registerPage(){
		return "loginPage";
	}
	
	
	
	/*
	 * 用户登录
	 */
	@RequestMapping(value="/login/doLogin")
	public ModelAndView login(HttpServletRequest request, User user){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginPage");			//设置登录失败是返回的界面
		switch(userService.ifExistUser(user)){
		case NOT_USERNAME : 
			mav.addObject("errorMsg", "用户名不存在");
			break;
		case ERROR_PASSWORD :
			mav.addObject("errorMsg", "用户密码不正确");
			break;
		case USER_LOCKED :
			mav.addObject("errorMsg", "用户已被锁定，不能登录");
			break;
		case CORRECT_USER :
			userService.loginSuccess(user,request);	//对登录成功进行相关日志记录
			mav.setViewName("redirect:/index");       //设置登录成功返回的界面
			break;
		default:
			break;
		}
		return mav;
	}
	
	/*
	 * 用户注销
	 */
	@RequestMapping("/login/doLogout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("LOGINUSER");
		return "redirect:/index";
	}
}
