package com.calculator.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.calculator.demo.modelo.Session;

public interface SessionRepository extends CrudRepository<Session, Long> {

	Session findBySession(String session);
}
