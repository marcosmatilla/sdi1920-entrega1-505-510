package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.id = ?1")
	User findUserById(Long id);
	
	List<User> findAllByRole(String role);

	Page<User> findAll(Pageable pageable);

	@Query("SELECT u FROM User u WHERE u.role = ?1")
	Page<User> findAllByRole(Pageable pageable, String role);
	
	@Query("SELECT u FROM User u WHERE u.role = ?2 and u.email!=?1")
	Page<User> findAll(Pageable pageable,String userEmail ,String role);


	@Query("SELECT u FROM User u WHERE u.role = ?2 AND "
			+ "(LOWER(u.name + ' ' + u.lastName) LIKE LOWER(?1) OR LOWER(u.email) LIKE LOWER(?1))")
	Page<User> searchByEmailAndNameByRole(Pageable pageable, String seachtext, String role);

	@Query("SELECT u FROM User u JOIN u.friends f WHERE f.email = ?1")
	Page<User> getFriendsOf(Pageable pageable, String email);

	@Query("SELECT r FROM User r WHERE (LOWER(r.email) NOT LIKE LOWER(?1))")
	Page<User> listUsersAdmin(Pageable pageable, String email);
}