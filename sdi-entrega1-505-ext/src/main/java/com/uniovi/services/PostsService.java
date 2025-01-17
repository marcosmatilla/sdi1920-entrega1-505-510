package com.uniovi.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;
import com.uniovi.repositories.PostRepository;

@Service
public class PostsService {
	@Autowired
	private PostRepository postsRepository;
	
	public Post addPost(Post post, User user) {
		post.setUser(user);
		return postsRepository.save(post);
	}
	
	public Post getPostById(Long id) {
		return postsRepository.findPostById(id);
	}
	
	public ArrayList<Post> getPostByUserId(Long id){
		return postsRepository.findAllByUserId(id);
	}
	
	public Page<Post> getPostOfUser(Pageable pageable, User user){
		return postsRepository.findAllByUser(pageable, user);
	}
	
	
	
}
