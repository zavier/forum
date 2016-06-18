package com.forum.repository;

import org.springframework.data.repository.Repository;

import com.forum.entity.LoginLog;

public interface LoginLogRepository extends Repository<LoginLog, String> {

	LoginLog save(LoginLog loginLog);
	
}
