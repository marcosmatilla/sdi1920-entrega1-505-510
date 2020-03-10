package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Invitation {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private User receiver;

	@ManyToOne
	private User sender;

	public Invitation() {

	}

	public Invitation(User sender, User receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}


}
