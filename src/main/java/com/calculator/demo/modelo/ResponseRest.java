package com.calculator.demo.modelo;

import java.util.Date;

public class ResponseRest {

	private Date creationDate;
	
	private Session session;
	
	private Header header;
	
	private Object body;

	public ResponseRest(Date creationDate, Session session, Header header, Object body) {
		super();
		this.creationDate = creationDate;
		this.session = session;
		this.header = header;
		this.body = body;
	}

	public ResponseRest() {
		super();
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

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
}
