package com.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.entity.Board;
import com.forum.entity.Post;
import com.forum.entity.Topic;
import com.forum.repository.BoardRepository;
import com.forum.repository.PostRepository;
import com.forum.repository.TopicRepository;

@Service
public class ForumService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	//查询所有的论坛模块
	@Transactional(readOnly=true)
	public List<Board> getAllBoards(){
		return boardRepository.findAll();
	}

	//添加论坛板块
	@Transactional
	public void addBoard(Board board) {
		board.setTopicNum(0);
		boardRepository.save(board);
	}
	
	//删除论坛版块
	@Transactional
	public void deleteBoard(String boardId){
		List<Topic> topics = topicRepository.findByBoard_Id(boardId);
		for(Topic topic : topics){
			topicRepository.delete(topic);
		}
		Board board = boardRepository.findById(boardId);
		boardRepository.delete(board);
	}

	//通过ID查找板块
	@Transactional(readOnly=true)
	public Board getBoardById(String boardId) {
		return boardRepository.findById(boardId);
	}

	//查找板块下所有主题帖
	@Transactional(readOnly=true)
	public List<Topic> getPagedTopics(String boardId) {
		return topicRepository.findByBoard_Id(boardId);
	}

	//增加主题帖
	@Transactional
	public void addTopic(Topic topic , String boardId) {
		Board board = boardRepository.findById(boardId);
		topic.setBoard(board);
		topicRepository.save(topic);
	}

	//根据主题帖ID查找主题帖
	@Transactional(readOnly=true)
	public Topic getTopicById(String topicId) {
		
		return topicRepository.findById(topicId);
	}

	//查找主题帖子下的所有帖子
	@Transactional(readOnly=true)
	public List<Post> getPagedPosts(String topicId) {
		
		return postRepository.findByTopic_Id(topicId);
	}

	@Transactional(readOnly=true)
	public boolean ifExistBoardName(Board board) {
		Board searchBoard = boardRepository.findByBoardName(board.getBoardName());
		if(searchBoard != null){
			return true;
		}
		return false;
	}
}
