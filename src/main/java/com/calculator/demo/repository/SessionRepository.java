package com.calculator.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.calculator.demo.modelo.Session;

public interface SessionRepository extends CrudRepository<Session, Long> {

	 /**
     * Método implementación de busqueda
     * 
     * @param session
     *            token con la sesion generada
     * @return Session
     * 			  retorna objeto con la session 
     */
	Session findBySession(String session);
}
