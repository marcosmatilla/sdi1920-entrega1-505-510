package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Invitation;
import com.uniovi.repositories.InvitationRepository;

@Service
public class InvitationService {
	@Autowired
	private InvitationRepository invitationRepository;
	
	public Page<Invitation> getInvitationsOf(Pageable pageable, String email) {
		return invitationRepository.getInvitationsOf(pageable,email);
	}

}
