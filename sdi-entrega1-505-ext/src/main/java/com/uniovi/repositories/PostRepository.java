package com.uniovi.repositories;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;

public interface PostRepository extends CrudRepository<Post, Long>{
	
	@Query("SELECT p FROM Post p WHERE p.user=?1 ORDER BY p.id ASC")
	Page<Post> findAllByUser(Pageable pageable, User user);
	
	@Query("SELECT p FROM Post p WHERE p.user.id=?1")
	ArrayList<Post> findAllByUserId(Long id);
	
	@Query("SELECT p FROM Post p WHERE p.id = ?1")
	Post findPostById(Long id);
}
