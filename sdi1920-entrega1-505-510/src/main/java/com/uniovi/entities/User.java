package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String email;

	private String name;
	private String lastName;
	private String role;
	private String password;
	@Transient
	private String passwordConfirm;

	@Transient
	private boolean send;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "sender_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "reciever_id", referencedColumnName = "id"))
	private Set<User> friends = new HashSet<User>();

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private Set<Invitation> receivedInvitations = new HashSet<Invitation>();

	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private Set<Invitation> sendedInvitations = new HashSet<Invitation>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public Set<Invitation> getReceivedInvitations() {
		return receivedInvitations;
	}

	public void setReceivedInvitations(Set<Invitation> receivedInvitations) {
		this.receivedInvitations = receivedInvitations;
	}

	public Set<Invitation> getSendedInvitations() {
		return sendedInvitations;
	}

	public void setSendedInvitations(Set<Invitation> sendedInvitations) {
		this.sendedInvitations = sendedInvitations;
	}

	/**
	 * Añade petición de amistad a la lista de peticiones enviadas del usuario que
	 * la envia y a la lista de peticiones recibidas del usuario que recibe la
	 * petición
	 * 
	 * @param sender
	 * @param reciever
	 * @param invitation
	 */
	public void sendInvitation(User sender, User reciever, Invitation invitation) {
		sender.getSendedInvitations().add(invitation);
		reciever.getReceivedInvitations().add(invitation);
	}

	/**
	 * Elimina petición de amistad de la lista de enviadas del que la envia y de la
	 * recibida del que la recibe.
	 * 
	 * @param sender
	 * @param reciever
	 * @param invitation
	 */
	public void removeInvitation(User sender, User reciever, Invitation invitation) {
		sender.getSendedInvitations().remove(invitation);
		reciever.getReceivedInvitations().remove(invitation);
		invitation.setSender(null);
		invitation.setReceiver(null);
	}

	/**
	 * Acepta petición de amistad.
	 * 
	 * @param sender
	 * @param receiver
	 */
	public void acceptInvitation(User sender, User receiver) {
		sender.getFriends().add(receiver);
		receiver.getFriends().add(sender);
	}

	/**
	 * Comprueba si el usuario a enviado petición ya
	 * 
	 * @param receiver
	 * @return
	 */
	public boolean existInvitation(User receiver) {
		for (Invitation i : getSendedInvitations()) {
			if (i.getReceiver().equals(receiver)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Comprueba si el usuario actual es amigo del usuario pasado por parámetro.
	 * 
	 * @param user
	 * @return true si son amigos o es él mismo, false si no lo son
	 */
	public String checkFriendStatus(User user) {
		if (this.equals(user) || friends.contains(user)) {
			send = true;
			return "FRIENDS";
		}
		if (existInvitation(user)) {
			send = true;
			return "REQUEST_SENT";
		}
		send = false;
		return "NOT_FRIENDS";
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}