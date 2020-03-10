package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		System.out.println("getList de invitation controller" + user + "usuario");
		Page<Invitation> invitation = invitationService.getFriendRequestsForUser(pageable, user);
		model.addAttribute("invitationList", invitation.getContent());
		model.addAttribute("page", invitation);
		return "invitation/list";
	}
	
	@RequestMapping("/invitation/list/update")
	public String updateList(Model model, Pageable pageable) {
		User user = usersService.getCurrentUser();
		Page<Invitation> invitation = invitationService.getFriendRequestsForUser(pageable, user);
		System.out.println("invittion controller" + invitation);
		model.addAttribute("tableUsers", invitation.getContent());
		model.addAttribute("page", invitation);
		return "user/friends::tableUsers";
	}

	@RequestMapping("user/accept/{idFr}/{idSender}")
	public String acceptFriendRequest(Model model, @PathVariable Long idFr, @PathVariable Long idSender) {
		User reciever = usersService.getCurrentUser();
		User sender = usersService.getUser(idSender);
		usersService.acceptFriendRequest(sender, reciever);
		invitationService.deleteInvitation(sender, reciever, idFr);
		System.out.println(usersService.getCurrentUser().getFriends());
		return "redirect:/user/friends/";
	}
}
