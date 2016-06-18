package com.forum.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.forum.entity.Topic;

public interface TopicRepository extends Repository<Topic, String> {

	Topic save(Topic topic);

	Topic findById(String topicId);

	List<Topic> findByBoard_Id(String boardId);

	void delete(Topic topic);

}
