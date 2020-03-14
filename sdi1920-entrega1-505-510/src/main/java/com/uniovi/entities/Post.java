package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private long id;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private Date date;
	
	private String title;
	private String content;
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	public Post() {
		this.date = new Date();
	}
	
	public Post(User user, String title, String content) {
		this.user = user;
		this.date = new Date();
		this.title = title;
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

