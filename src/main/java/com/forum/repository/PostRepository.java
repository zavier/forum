package com.forum.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.forum.entity.Post;

public interface PostRepository extends Repository<Post, String> {

	List<Post> findByTopic_Id(String topicId);

}
