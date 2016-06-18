package com.forum.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.entity.LoginLog;
import com.forum.entity.User;
import com.forum.repository.LoginLogRepository;
import com.forum.repository.UserRepository;

//用户管理服务器，负责查询用户、注册用户、锁定用户等操作
@Service
public class UserService {

	public static final int USER_LOCKED = 1;
	public static final int USER_UNLOCK = 0;
	
	public enum UserStatus { ERROR_PASSWORD, NOT_USERNAME, USER_LOCKED, CORRECT_USER, USERNAME_EXISTED };
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginLogRepository loginLogRepository;
	
	/*
	 * 注册新用户
	 */
	@Transactional
	public UserStatus register(User user){
		String userName = user.getUserName();
		User findUser = userRepository.findByUserName(userName);
		if(findUser != null){
			return UserStatus.USERNAME_EXISTED;
		}else{
			user.setUserType(1);
			user.setCredit(5);
			userRepository.save(user);
			return UserStatus.CORRECT_USER;
		}
	}

	/*
	 * 通过用户名查询用户
	 */
	@Transactional(readOnly=true)
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	/*
	 * 锁定用户
	 */
	@Transactional
	public void lockUser(String userName){
		User user = userRepository.findByUserName(userName);
		user.setLocked(USER_LOCKED);
		userRepository.save(user);
	}
	
	/*
	 * 解除锁定的用户
	 */
	@Transactional
	public void unlockUser(String userName){
		User user = userRepository.findByUserName(userName);
		user.setLocked(USER_UNLOCK);
		userRepository.save(user);
	}
	
	/*
	 * 登录成功并记录日志,添加session
	 */
	@Transactional
	public void loginSuccess(User user, HttpServletRequest request){
		LoginLog loginLog = new LoginLog();
		loginLog.setIp(request.getRemoteAddr());
		loginLog.setLoginDatetime(new Date());
		User findUser = userRepository.findByUserName(user.getUserName());
		loginLog.setUser(findUser);  //设置loginLog中的User外键
		loginLogRepository.save(loginLog);
		request.getSession().setAttribute("LOGINUSER", findUser);
	}

	@Transactional(readOnly=true)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	
	//判断要登录的用户是否存在以及状态
	@Transactional(readOnly=true)
	public UserStatus ifExistUser(User user) {
		String userName = user.getUserName();
		String password = user.getPassword();
		User findUser = new User();
		if((findUser = userRepository.findByUserName(userName)) == null){
			return UserStatus.NOT_USERNAME;
		}else if(!password.equals(findUser.getPassword())){
			return UserStatus.ERROR_PASSWORD;
		}else if(findUser.getLocked() == UserService.USER_LOCKED){
			return UserStatus.USER_LOCKED;
		}
		return UserStatus.CORRECT_USER;
	}

	@Transactional(readOnly=true)
	public User findById(String userId) {
		return userRepository.findById(userId);
	}

	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
}
