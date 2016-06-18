package com.forum.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * 论坛主题类
 */
@Entity
@Table(name="t_topic")
public class Topic {

	private String id;
	private Board board;
	private User user;
	private String topicTitle;
	private String topicContent;
	private Date createTime;
	private Date lastPost;//最后回复时间
	private int topicView;
	private int topicReplies;
	private int digest;//为0不是精华帖,1为精华帖
	
	@Id
	@GeneratedValue(generator = "generator")  
    @GenericGenerator(name = "generator", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="board_id")
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="topic_title")
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	
	@Column(name="topic_content")
	public String getTopicContent() {
		return topicContent;
	}
	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="last_post")
	public Date getLastPost() {
		return lastPost;
	}
	public void setLastPost(Date lastPost) {
		this.lastPost = lastPost;
	}
	
	@Column(name="topic_view")
	public int getTopicView() {
		return topicView;
	}
	public void setTopicView(int topicView) {
		this.topicView = topicView;
	}
	
	@Column(name="topic_replies")
	public int getTopicReplies() {
		return topicReplies;
	}
	public void setTopicReplies(int topicReplies) {
		this.topicReplies = topicReplies;
	}
	
	@Column(name="digest")
	public int getDigest() {
		return digest;
	}
	public void setDigest(int digest) {
		this.digest = digest;
	}
	
	
}
