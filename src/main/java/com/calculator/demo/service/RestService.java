package com.calculator.demo.service;

import com.calculator.demo.model.Audit;
import com.calculator.demo.model.Operation;
import com.calculator.demo.model.ResponseRest;
import com.calculator.demo.model.Session;


public interface RestService {
	
	public ResponseRest getSession(String user);
	
	public ResponseRest putOperando(Operation operation);
	
	public ResponseRest putOperador(Operation operation);
	
	public Audit getAudit (Session session);
}
