package com.forum.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.forum.entity.User;

public interface UserRepository extends Repository<User, String> {

	User save(User user);

	User findByUserName(String userName);

	List<User> findAll();

	User findById(String userId);

}
