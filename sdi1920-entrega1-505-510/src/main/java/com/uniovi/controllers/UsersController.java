package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.LoggerService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private LoggerService loggerService;

	@RequestMapping(value = "/user/add")
	public String getUser(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "user/add";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		loggerService.newUserSignUp(user.getEmail());
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, Principal principal) {
		if (error != null) {
			model.addAttribute("error", error);
		}
		
		return "login";
	}

	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String searchText, Principal principal) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByEmailAndName(pageable, searchText);
		} else {
			users = usersService.getUsers(pageable, principal.getName());
		}
		loggerService.seeUsers(principal.getName());
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("activeUser", usersService.getCurrentUser());
		model.addAttribute("page", users);
		return "user/list";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
		loggerService.userLogged(principal.getName());
		return "home";
	}

	@RequestMapping("/user/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		Page<User> users = null;
		if (!usersService.getUserByEmail(principal.getName()).getRole().equals("ROLE_ADMIN")) {
			users = usersService.getUsers(pageable, principal.getName());
		} else {
			users = usersService.getAllUserForAdmin(pageable, principal.getName());
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("activeUser", usersService.getCurrentUser());
		model.addAttribute("page", users);
		return "user/list :: tableUsers";
	}

	/* PARA LOS AMIGOS */
	@RequestMapping("/user/friends")
	public String getFriends(Pageable pageable, Principal principal, Model model) {
		loggerService.seeOwnFriends(principal.getName());
		Page<User> users = usersService.getFriends(pageable, principal.getName());
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "/user/friends";
	}

	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Long id, Principal principal) {
		User userDelete = usersService.getUser(id);
		loggerService.adminDeleteUser(principal.getName(), userDelete.getEmail());
		usersService.deleteUser(id);
		return "redirect:/user/list";
	} 

}