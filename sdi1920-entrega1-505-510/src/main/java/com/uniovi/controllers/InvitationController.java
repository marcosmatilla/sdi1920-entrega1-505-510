package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.services.InvitationService;
import com.uniovi.services.UsersService;

@Controller
public class InvitationController {
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/invitation/list")
	public String getList(Model model, Pageable pageable) {
		User user = usersService.getCurrentUser();
		Page<Invitation> invitation = invitationService.getFriendRequestsForUser(pageable, user);
		model.addAttribute("invitationTable", invitation.getContent());
		model.addAttribute("page", invitation);
		return "invitation/list";
	}
}
