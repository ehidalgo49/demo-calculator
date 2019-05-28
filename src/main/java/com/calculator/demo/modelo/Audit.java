package com.calculator.demo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="Audit", schema="public" )
public class Audit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String username;
	
	private Date creationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Session session;
	
	private String request;
	
	private String response;

	public Audit(long id, String username, Date creationDate, Session session, String request, String response) {
		super();
		this.id = id;
		this.username = username;
		this.creationDate = creationDate;
		this.session = session;
		this.request = request;
		this.response = response;
	}
	
	public Audit() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
