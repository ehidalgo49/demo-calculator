package com.calculator.demo.service;

import com.calculator.demo.modelo.Audit;
import com.calculator.demo.modelo.Operation;
import com.calculator.demo.modelo.ResponseRest;
import com.calculator.demo.modelo.Session;

public interface RestService {
	
public ResponseRest getSession(String user);
	
	public ResponseRest putOperando(Operation operation);
	
	public ResponseRest putOperador(Operation operation);
	
	public Audit getAudit (Session session);	
}
