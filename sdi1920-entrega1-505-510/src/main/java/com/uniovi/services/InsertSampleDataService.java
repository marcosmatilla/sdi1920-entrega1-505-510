package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.Post;
import com.uniovi.entities.User;
import com.uniovi.repositories.InvitationRepository;
import com.uniovi.repositories.PostRepository;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private PostRepository postsRepository;

	@PostConstruct
	public void init() {
		User user1 = new User("pediaz@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("lu@gmail.com", "Lucia", "Llera");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("marod@yahoo.es", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("martitaalmonteperdido@hotmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("pescadofrito@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		User user6 = new User("diezne@yahoo.es", "Diego", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		User user7 = new User("tutuya@hotmail.com", "Andrea", "Casillas");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		User user8 = new User("juanito11@yahoo.es", "Juan", "Díaz");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[0]);
		User user9 = new User("pepe@gmail.com", "Pepe", "Gonzalez");
		user9.setPassword("123456");
		user9.setRole(rolesService.getRoles()[0]);
		User user10 = new User("mariadelao@gmail.com", "María", "Hurlé");
		user10.setPassword("123456");
		user10.setRole(rolesService.getRoles()[0]);
		User user11 = new User("martita2201@hotmail.com", "Marta", "Almonte");
		user11.setPassword("123456");
		user11.setRole(rolesService.getRoles()[0]);
		User user12 = new User("peval@gmail.com", "Juca", "Valdes");
		user12.setPassword("123456");
		user12.setRole(rolesService.getRoles()[0]);
		User user13 = new User("mati@hotmail.com", "Miguel", "Matilla");
		user13.setPassword("123456");
		user13.setRole(rolesService.getRoles()[0]);

		User user14 = new User("admin@email.com", "Admin", "Admin");
		user14.setPassword("admin");
		user14.setRole(rolesService.getRoles()[1]);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);
		usersService.addUser(user10);
		usersService.addUser(user11);
		usersService.addUser(user12);
		usersService.addUser(user13);
		
		usersService.addUser(user14);
		
		user1.getFriends().add(user2);
		user1.getFriends().add(user3);
		user1.getFriends().add(user4);
		user1.getFriends().add(user5);
		user1.getFriends().add(user6);
		user1.getFriends().add(user7);
		user1.getFriends().add(user8);
		user1.getFriends().add(user9);
		user1.getFriends().add(user10);
		user1.getFriends().add(user11);
		user1.getFriends().add(user12);
		user1.getFriends().add(user13);
		user2.getFriends().add(user1);
		user2.getFriends().add(user3);
		user2.getFriends().add(user4);
		user2.getFriends().add(user5);
		user2.getFriends().add(user6);
		user2.getFriends().add(user7);
		user2.getFriends().add(user8);
		user2.getFriends().add(user9);
		user2.getFriends().add(user10);
		user2.getFriends().add(user11);
		user2.getFriends().add(user12);
		user2.getFriends().add(user13);
		user3.getFriends().add(user1);
		user3.getFriends().add(user2);
		user3.getFriends().add(user4);
		user3.getFriends().add(user5);
		user3.getFriends().add(user6);
		user3.getFriends().add(user7);
		user3.getFriends().add(user8);
		user3.getFriends().add(user9);
		user3.getFriends().add(user10);
		user3.getFriends().add(user11);
		user3.getFriends().add(user12);
		user3.getFriends().add(user13);
		user4.getFriends().add(user1);
		user4.getFriends().add(user2);
		user4.getFriends().add(user3);
		user4.getFriends().add(user5);
		user4.getFriends().add(user6);
		user4.getFriends().add(user7);
		user4.getFriends().add(user8);
		user4.getFriends().add(user9);
		user4.getFriends().add(user10);
		user4.getFriends().add(user11);
		user4.getFriends().add(user12);
		user4.getFriends().add(user13);
		user5.getFriends().add(user1);
		user5.getFriends().add(user2);
		user5.getFriends().add(user3);
		user5.getFriends().add(user4);
		user5.getFriends().add(user6);
		user5.getFriends().add(user7);
		user5.getFriends().add(user8);
		user5.getFriends().add(user9);
		user5.getFriends().add(user10);
		user5.getFriends().add(user11);
		user5.getFriends().add(user12);
		user5.getFriends().add(user13);
		user6.getFriends().add(user1);
		user6.getFriends().add(user2);
		user6.getFriends().add(user3);
		user6.getFriends().add(user4);
		user6.getFriends().add(user5);
		user6.getFriends().add(user7);
		user6.getFriends().add(user8);
		user6.getFriends().add(user9);
		user6.getFriends().add(user10);
		user6.getFriends().add(user11);
		user6.getFriends().add(user12);
		user6.getFriends().add(user13);
		
		List<Invitation> invitaciones = new ArrayList<Invitation>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Invitation(user1,user3));
				add(new Invitation(user7,user1));
				add(new Invitation(user8,user1));
				add(new Invitation(user9,user1));
				add(new Invitation(user10,user1));
				add(new Invitation(user1,user2));
				add(new Invitation(user1,user5));
				add(new Invitation(user1,user6));
				add(new Invitation(user1,user7));
				add(new Invitation(user1,user8));
				add(new Invitation(user2,user3));
				add(new Invitation(user2,user5));
				add(new Invitation(user2,user6));
				add(new Invitation(user2,user7));
				add(new Invitation(user2,user8));
				add(new Invitation(user3,user4));
				add(new Invitation(user3,user5));
				add(new Invitation(user3,user6));
				add(new Invitation(user3,user7));
				add(new Invitation(user3,user8));
			}
		};
		
		invitationRepository.saveAll(invitaciones);
		
		Post post1 = new Post(user1, "Hola", "Buenas");
		postsRepository.save(post1);
		
	}


}