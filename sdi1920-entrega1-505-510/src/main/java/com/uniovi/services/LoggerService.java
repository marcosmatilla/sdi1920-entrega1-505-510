package com.uniovi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void newUserSignUp(String email) {
		log.info("Nuevo usuario registrado: {}", email);
	}
	
	public void userLogged(String email) {
		log.info("Usuario entrado en sesión: {}", email);
	}

	public void sendInvitationTo(String emailSender, String emailReceiver) {
		log.info("El usuario {} envío una petición de amistad a {}", emailSender, emailReceiver);
	}
	
	public void acceptInvitation(String emailSender, String emailReceiver) {
		log.info("El usuario {} aceptó la petición de amistad de {}", emailReceiver, emailSender);
	}
	
	public void seeUserPost(String email, String emailToSee) {
		log.info("El usuario {} visitó los post del usuario {}", email, emailToSee);
	}
	
	public void addPost(String email) {
		log.info("El usuario {} añadió un post", email);
	}

	public void seeOwnPost(String email) {
		log.info("El usuario {} visitó sus propios post", email);
	}
	
	public void seeOwnFriends(String email) {
		log.info("El usuario {} visitó sus propios amigos", email);
	}
	
	public void seeInvitations(String email) {
		log.info("El usuario {} visitó sus invitaciones", email);
	}
	
	public void adminDeleteUser(String name, String email) {
		log.info("El adminstrador {} eliminó al usuario {}", name, email);
	}

	public void seeUsers(String name) {
		log.info("El usuario {} listó todos los usuarios", name);
	}
	
	public void errorFriendPost(String user1, String user2) {
		log.info("El usuario {} intentó acceder a los post de {} sin ser amigos", user1, user2);
	}
}


