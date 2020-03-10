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
	
	/* Pruebas enviar invitaciones */
	private Boolean resend = false;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "sender_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "reciever_id", referencedColumnName = "id"))
	private Set<User> friends = new HashSet<User>();

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private Set<Invitation> receivedInvitations = new HashSet<Invitation>();

	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private Set<Invitation> sendedInvitations = new HashSet<Invitation>();

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

	/* Pruebas enviar invitaciones */
	public Boolean getResend() {
		return resend;
	}

	public void setResend(Boolean resend) {
		this.resend = resend;
	}

}