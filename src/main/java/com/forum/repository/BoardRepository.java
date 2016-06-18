package com.forum.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.forum.entity.Board;
/*
 * 论坛版块Dao
 */
public interface BoardRepository extends Repository<Board, String> {

	List<Board> findAll();

	Board save(Board board);

	Board findById(String boardId);

	Board findByBoardName(String boardName);

	void delete(Board board);
	
}