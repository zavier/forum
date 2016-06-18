package com.forum.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forum.entity.Board;
import com.forum.entity.Post;
import com.forum.entity.Topic;
import com.forum.entity.User;
import com.forum.service.ForumService;

/*
 * 论坛的基本功能
 * 包括发表主题帖子、回复帖子、删除帖子、设精华主题帖等
 */
@Controller
public class BoardManagerController {

	@Autowired
	private ForumService forumService;
	
	//列出论坛模块下的主题帖子
	@RequestMapping(value="/board/listBoardTopics/{boardId}",method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Topic> listBoardTopics(@PathVariable String boardId){
		return forumService.getPagedTopics(boardId);
	}
	
	//新增主题帖子的页面
	@RequestMapping(value="/board/addTopicPage/{boardId}",method=RequestMethod.GET)
	public ModelAndView addTopicPage(@PathVariable String boardId){
		ModelAndView view = new ModelAndView();
		view.addObject("boardId", boardId);
		view.setViewName("addTopicPage");
		return view;
	}
	
	//新增主题帖
	@RequestMapping(value="/board/addTopic/{boardId}",method=RequestMethod.POST)
	public String addTopic(HttpServletRequest request, @PathVariable String boardId, Topic topic){
		User user = (User) request.getSession().getAttribute("LOGINUSER");
		topic.setUser(user);
		Date now = new Date();
		topic.setCreateTime(now);
		topic.setLastPost(now);
		topic.setTopicView(1);
		topic.setTopicReplies(0);
		topic.setDigest(0);
		forumService.addTopic(topic, boardId);
		return "redirect:/index";
	}
	
	//列出主题下的所有帖子
	@RequestMapping(value="/board/listTopicPosts-{topicId}",method=RequestMethod.GET)
	public ModelAndView listTopicPosts(@PathVariable String topicId){
		ModelAndView view = new ModelAndView();
		Topic topic = forumService.getTopicById(topicId);
		List<Post> pagedPost = forumService.getPagedPosts(topicId);
		view.addObject("topic", topic);
		view.addObject("pagedPost", pagedPost);
		view.setViewName("/listTopicPosts");
		return view;
	}
	
	//查看主题帖详细信息
	@RequestMapping(value="/showTopicDetails/{topicId}")
	public ModelAndView showTopicDetails(@PathVariable String topicId){
		ModelAndView view = new ModelAndView();
		view.setViewName("showTopicDetails");
		Topic topic = forumService.getTopicById(topicId);
		view.addObject("topic", topic);
		return view;
	}
}
