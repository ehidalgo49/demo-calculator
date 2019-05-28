package com.calculator.demo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Session", schema="public" )
public class Session {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private Date creationDate;
	
	private String session;
	
	private String username;

	public Session(long id, Date creationDate, String session, String username) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.session = session;
		this.username = username;
	}

	public Session() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
