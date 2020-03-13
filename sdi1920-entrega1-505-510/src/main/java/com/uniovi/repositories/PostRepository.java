package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;

public interface PostRepository extends CrudRepository<Post, Long>{
	
	@Query("SELECT p FROM Post p WHERE p.user=?1 ORDER BY p.id ASC")
	Page<Post> findAllByUser(Pageable pageable, User user);
	
}
