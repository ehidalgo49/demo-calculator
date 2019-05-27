package com.calculator.demo.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calculator.demo.model.Audit;
import com.calculator.demo.model.Operation;
import com.calculator.demo.model.ResponseRest;
import com.calculator.demo.model.Session;
import com.calculator.demo.repository.SessionRepository;


@Service("restService")
@Transactional
public class RestServiceImpl implements RestService{
	
	@Autowired
	private SessionRepository sessionRepository;
	

	public ResponseRest getSession(String user){
		return null;
	}
	
	public ResponseRest putOperando(Operation operation){
		return null;
	}
	
	public ResponseRest putOperador(Operation operation){
		return null;
	}
	
	public Audit getAudit (Session session){
		return null;
	}
}
