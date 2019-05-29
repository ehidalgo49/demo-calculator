package com.calculator.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.calculator.demo.modelo.Operation;
import com.calculator.demo.modelo.Session;

public interface OperationRepository extends CrudRepository<Operation, Long> {

	List<Operation> findAllBySessionAndOperador(Session session, String operador);
}
