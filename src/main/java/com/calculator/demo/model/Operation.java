package com.calculator.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Operation", schema="public" )
public class Operation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private Date creationDate;
	
	private Session session;
	
	private BigDecimal operando;
	
	private BigDecimal operador;
	
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

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public BigDecimal getOperando() {
		return operando;
	}

	public void setOperando(BigDecimal operando) {
		this.operando = operando;
	}

	public BigDecimal getOperador() {
		return operador;
	}

	public void setOperador(BigDecimal operador) {
		this.operador = operador;
	}

}
