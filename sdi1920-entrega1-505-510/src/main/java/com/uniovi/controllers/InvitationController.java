package com.uniovi.controllers;

import java.security.Principal;

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
import com.uniovi.services.LoggerService;
import com.uniovi.services.UsersService;

@Controller
public class InvitationController {
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private LoggerService loggerService;
	
	@RequestMapping("/invitation/list")
	public String getList(Model model, Pageable pageable) {
		User user = usersService.getCurrentUser();
		loggerService.seeInvitations(user.getEmail());
		Page<Invitation> invitation = invitationService.getInvitationForUser(pageable, user);
		model.addAttribute("invitationList", invitation.getContent());
		model.addAttribute("page", invitation);
		return "invitation/list";
	}
	
	@RequestMapping("/invitation/list/update")
	public String updateList(Model model, Pageable pageable) {
		User user = usersService.getCurrentUser();
		Page<Invitation> invitation = invitationService.getInvitationForUser(pageable, user);
		model.addAttribute("tableUsers", invitation.getContent());
		model.addAttribute("page", invitation);
		return "user/friends::tableUsers";
	}

	@RequestMapping("user/accept/{idFr}/{idSender}")
	public String acceptFriendRequest(Model model, @PathVariable Long idFr, @PathVariable Long idSender) {
		User reciever = usersService.getCurrentUser();
		User sender = usersService.getUser(idSender);
		loggerService.acceptInvitation(sender.getEmail(), reciever.getEmail());
		usersService.acceptFriendRequest(sender, reciever);
		invitationService.deleteInvitation(sender, reciever, idFr);
		return "redirect:/user/friends/";
	}
	
	@RequestMapping("/user/send/{id}")
	public String sendFriendRequest(Principal principal, @PathVariable Long id) {
		User receiver = usersService.getUser(id);
		loggerService.sendInvitationTo(principal.getName(), receiver.getEmail());
		invitationService.sendInvitation(principal.getName(), id);
		return "redirect:/user/list";
	}
}
