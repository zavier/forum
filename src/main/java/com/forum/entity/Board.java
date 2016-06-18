package com.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * 论坛版块类
 */
@Entity
@Table(name="t_board")
public class Board {
	
	private String id;
	private String boardName;
	private String boardDesc;
	private int topicNum;
	
	@Id
	@GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="board_name")
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	@Column(name="board_desc")
	public String getBoardDesc() {
		return boardDesc;
	}
	public void setBoardDesc(String boardDesc) {
		this.boardDesc = boardDesc;
	}
	
	@Column(name="topic_num")
	public int getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
	
	@Override
	public String toString() {
		return "Board [id=" + id + ", boardName=" + boardName + ", boardDesc=" + boardDesc + ", topicNum=" + topicNum
				+ "]";
	}
	
}
