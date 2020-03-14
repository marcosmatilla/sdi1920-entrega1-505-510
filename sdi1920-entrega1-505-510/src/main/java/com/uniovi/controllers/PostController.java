package com.uniovi.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping("/post/list/{id}")
	public String getListFriend(Model model, Pageable pageable, @PathVariable Long id) {
		User user = usersService.getUser(id);
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
	public String addPost(Post post, Model model, @RequestParam("image") MultipartFile image, BindingResult result) {
		Post saved = postsService.addPost(post, usersService.getCurrentUser());
		if(!image.isEmpty()) {
			saveImage(image,result,saved);
			if(result.hasErrors()) {
				return "post/add";				
			}
		}
		return "redirect:/post/list";
	}

	private void saveImage(MultipartFile image,BindingResult result,Post post) {
		try {
			InputStream is = image.getInputStream();
			Files.copy(is, 
					Paths.get("src/main/resources/static/fotos/" + post.getId() + ".jpg"),
					StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException e) {
			e.printStackTrace();
			result.addError(new ObjectError("image", "Error"));
		}
	}


}
