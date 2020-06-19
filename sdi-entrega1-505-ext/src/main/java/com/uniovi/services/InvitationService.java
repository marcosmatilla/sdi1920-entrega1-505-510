package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.repositories.InvitationRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class InvitationService {
	@Autowired
	private InvitationRepository invitationRepository;

	@Autowired
	private UsersRepository usersRepository;

	public Page<Invitation> getInvitationsOf(Pageable pageable, String email) {
		Page<Invitation> invitations = invitationRepository.getInvitationsOf(pageable, email);
		return invitations;
	}

	public void deleteInvitation(User sender, User reciever, Long id) {
		Invitation i = invitationRepository.findInvitationById(id);
		sender.removeInvitation(sender, reciever, i);
		invitationRepository.deleteById(id);
	}

	public void sendInvitation(String sender_email, Long receiver_id) {
		User receiver = usersRepository.findUserById(receiver_id);
		User sender = usersRepository.findByEmail(sender_email);
		invitationRepository.save(new Invitation(sender, receiver));

	}

	public Page<Invitation> getInvitationForUser(Pageable pageable, User user) {
		Page<Invitation> invitation = invitationRepository.findAllByUser(pageable, user);
		return invitation;
	}

	public Invitation getInvitationById(Long id) {
		return invitationRepository.findInvitationById(id);
	}

}
