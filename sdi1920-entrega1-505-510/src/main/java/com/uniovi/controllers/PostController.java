package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;
import com.uniovi.services.PostsService;
import com.uniovi.services.UsersService;

@Controller
public class PostController {
	@Autowired
	private PostsService postsService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/post/list")
	public String getList(Model model, Pageable pageable) {
		User user = usersService.getCurrentUser();
		Page<Post> posts = postsService.getPostOfUser(pageable, user);
		model.addAttribute("postList", posts.getContent());
		model.addAttribute("page", posts);
		return "post/list";
	}
	
	@RequestMapping(value = "/post/add", method = RequestMethod.GET)
	public String addPost(Model model) {
		model.addAttribute("post", new Post());
		return "post/add";
	}

	
	@RequestMapping(value = "/post/add", method = RequestMethod.POST)
	public String addPost(Post post, Model model) {
		postsService.addPost(post, usersService.getCurrentUser());
		return "redirect:/post/list";
	}
}
