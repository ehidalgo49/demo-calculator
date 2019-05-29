package com.calculator.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.calculator.demo.modelo.Operation;
import com.calculator.demo.modelo.Session;

public interface OperationRepository extends CrudRepository<Operation, Long> {

	/**
     * Método implementación de busqueda
     * 
     * @param session
     *            token con la sesion generada
     * @return List<Operation>
     * 			  retorna lista de Operation para la sesion 
     */
	List<Operation> findAllBySession(Session session);
}
