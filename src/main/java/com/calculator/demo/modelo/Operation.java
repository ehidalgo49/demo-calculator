package com.calculator.demo.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="Operation", schema="public" )
public class Operation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private Date creationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Session session;
	
	@Column(nullable=true)
	private BigDecimal operando;
	
	@Column(nullable=true)
	private String operador;
	
	@Column(nullable=true)
	private BigDecimal resultado;
	
	public Operation(long id, Date creationDate, Session session, BigDecimal operando, String operador,
			BigDecimal resultado) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.session = session;
		this.operando = operando;
		this.operador = operador;
		this.resultado = resultado;
	}
	
	public Operation() {
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

	public String getOperador() {
		return operador != null ? operador.trim() : operador;
	}

	public void setOperador(String operador) {
		this.operador = operador != null ? operador.trim() : operador;
	}

	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "Operation [ id="+this.id+" creationDate="+this.creationDate+" operando="+this.operando+" operador="+this.operador
				+" resultado="+this.resultado+" session="+ this.session.toString() +" ]";
	}
}
