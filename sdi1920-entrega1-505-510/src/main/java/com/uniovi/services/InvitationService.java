package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.repositories.InvitationRepository;

@Service
public class InvitationService {
	@Autowired
	private InvitationRepository invitationRepository;
	
	public Page<Invitation> getInvitationsOf(Pageable pageable, String email) {
		Page<Invitation> invitations = invitationRepository.getInvitationsOf(pageable,email);
		return invitations;
	}
	
	public void deleteInvitation(User sender, User reciever, Long id) {
		Invitation i = invitationRepository.findInvitation(id);
		sender.removeInvitation(sender, reciever, i);
		invitationRepository.deleteById(id);
	}
	
	public void sendInvitation(User sender, User reciever) {
		Invitation invitation = new Invitation(sender, reciever);
		invitationRepository.save(invitation);
		sender.sendInvitation(sender, reciever, invitation);
	}
	
	

}
