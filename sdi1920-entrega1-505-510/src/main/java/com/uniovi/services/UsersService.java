package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAllByRole("ROLE_PUBLIC").forEach(users::add);
		return users;
	}

	public Page<User> searchUsersByEmailAndName(Pageable pageable, String searchText) {
		searchText = "%" + searchText + "%";
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		users = usersRepository.searchByEmailAndNameByRole(pageable, searchText, "ROLE_PUBLIC");
		return users;
	}

	public Page<User> getUsers(Pageable pageable) {
		return usersRepository.findAllByRole(pageable, "ROLE_PUBLIC");
	}

}