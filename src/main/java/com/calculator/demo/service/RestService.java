package com.calculator.demo.service;

import com.calculator.demo.exception.GeneralException;
import com.calculator.demo.modelo.Audit;
import com.calculator.demo.modelo.Operation;
import com.calculator.demo.modelo.ResponseRest;
import com.calculator.demo.modelo.Session;

import io.jsonwebtoken.Claims;

public interface RestService {
	
	/**
     * Método para implementar la obtencion de una session por usuario
     * 
     * @param user
     *            usuario a loguear
     * @return ResponseRest
     * 			  respuesta del objeto rest 
     */
	public ResponseRest getSession(String user);

	/**
     * Método para implementar el grabado de un operando numérico
     * 
     * @param operation
     *            objeto Operation con el número a operar
     * @return ResponseRest
     * 			  respuesta del objeto rest 
     */
	public ResponseRest putOperando(Operation operation) throws GeneralException;
	
	/**
     * Método para implementar el cálculo de una operación para los datos de una sesion
     * 
     * @param Operation
     *            objeto Operation con el simbolo de la operacion a realizar
     * @return ResponseRest
     * 			  respuesta del objeto rest 
     */
	public ResponseRest putOperador(Operation operation) throws GeneralException;
	
	/**
     * Método para implementar la obtencion de auditoria y traza
     * 
     * @param session
     *            objeto de session a auditar
     * @return ResponseRest
     * 			  respuesta del objeto rest de auditoria 
     */
	public Audit getAudit (Session session) throws GeneralException;
	
	/**
     * Método para implementar la obtencion y generación de un token mediante una clave privada
     * y durante 10 minutos
     * 
     * @param username
     *            recibe nombre de usuario
     * @return String 
     * 			  respuesta del token generado 
     */
	public String getJWTToken(String username) throws GeneralException;

	/**
     * Método que valida un token generado mediante una clave privada y comprueba su vigencia y validez
     * 
     * @param session
     *            recibe el objeto session con el token
     * @return Claims
     * 			  respuesta del objeto con la informacion del token 
     */
	public Claims validateToken(Session session);
}
