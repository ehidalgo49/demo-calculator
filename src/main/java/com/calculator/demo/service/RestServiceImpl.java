package com.calculator.demo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calculator.demo.exception.GeneralException;
import com.calculator.demo.modelo.Audit;
import com.calculator.demo.modelo.Operation;
import com.calculator.demo.modelo.ResponseRest;
import com.calculator.demo.modelo.Session;
import com.calculator.demo.repository.OperationRepository;
import com.calculator.demo.repository.SessionRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service("restService")
@Transactional
public class RestServiceImpl implements RestService{

	private static final Logger logger = LoggerFactory.getLogger(RestServiceImpl.class);
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	private final String secretKey = "aaaZZZ";

	/**
     * Implementación de método para la obtencion de una session por usuario
     * 
     * @param user
     *            usuario a loguear
     * @return ResponseRest
     * 			  respuesta del objeto rest 
     */
	public ResponseRest getSession(String user) throws GeneralException{
		ResponseRest response = new ResponseRest();
		
		String token = getJWTToken(user);
		Session session = new Session();
		session.setCreationDate(new Date());
		session.setSession(token);
		session.setUsername(user);
		response.setSession(session);
		sessionRepository.save(session);
		
		return response;
	}
	/**
     * Método que implementa el grabado de un operando numérico, valida la session que este activa y almacena el valor
     * 
     * @param operation
     *            objeto Operation con el número a operar
     * @return ResponseRest
     * 			  respuesta del objeto rest 
     */
	public ResponseRest putOperando(Operation operation) throws GeneralException{
		logger.debug("Session recibida "+operation.getSession());
		ResponseRest response = new ResponseRest();
		
		Claims claims = validateToken(operation.getSession());
		if (claims.get("sub") != null) {
			logger.debug("Session validada");
			Session session = sessionRepository.findBySession(operation.getSession().getSession());
			logger.debug(" session "+session.toString());
			operation.setCreationDate(new Date());
			operation.setSession(session);
			logger.debug(operation.toString());
			response.setSession(session);
			response.setBody((Operation)operationRepository.save(operation));
		} else {
			logger.error("Error");
			throw new GeneralException("token invalido");
		}
		return response;
	}
	
	/**
     * Método que implementa el cálculo de una operación para los datos de una sesion, valida que la sessión exista
     * busca los operandos que han sido almacenados y realiza validaciones de cantidad de datos y tipos de operador
     * finalmente realiza el calculo y retorna la respuesta
     * 
     * @param Operation
     *            objeto Operation con el simbolo de la operacion a realizar
     * @return ResponseRest
     * 			  respuesta del objeto rest 
     */
	public ResponseRest putOperador(Operation operation) throws GeneralException{
		logger.debug("Session recibida "+operation.getSession());
		ResponseRest response = new ResponseRest();
		Claims claims = validateToken(operation.getSession());
		if (claims.get("sub") != null) {
			logger.debug("Session valida");
			Session session = sessionRepository.findBySession(operation.getSession().getSession());
			logger.debug(" session "+session.toString());
			operation.setCreationDate(new Date());
			operation.setSession(session);
			logger.debug(operation.toString());
			
			BigDecimal resultado = new BigDecimal(0);
			
			List<Operation> operations = operationRepository.findAllBySession(session);
			if(!operations.isEmpty() && operations.size() > 1){
				response.setBody(operationRepository.save(operation));
				resultado = operations.get(0).getOperando();
				for(int i = 1; i < operations.size(); i++){
					Operation op = operations.get(i);
					if(operation.getOperador().compareTo("+") == 0){
						resultado = resultado.add(op.getOperando());
					}else if(operation.getOperador().compareTo("-") == 0){
						resultado = resultado.subtract(op.getOperando());
					}else if(operation.getOperador().compareTo("*") == 0){
						resultado = resultado.multiply(op.getOperando());
					}else if(operation.getOperador().compareTo("/") == 0){
						if(op.getOperando().compareTo(new BigDecimal(0)) != 0){
							resultado = resultado.divide(op.getOperando());
						}else{
							logger.info("no es posible dividir entre cero");
							throw new GeneralException("no es posible dividir entre cero");
						}
					}else if(operation.getOperador().compareTo("^") == 0){
						resultado = resultado.pow(op.getOperando().intValue());
					}else{
						logger.info("operacion no existente");
						throw new GeneralException("tipo de operacion no existente");
					}
				}
				operation.setResultado(resultado);
				operation.setOperando(resultado);
				operation = operationRepository.save(operation);
				response.setSession(session);
				response.setBody(operation);
			}else{
				logger.info("debe haber minimo dos operandos para operar");
				throw new GeneralException("debe haber minimo dos operandos para operar");
			}
		} else {
			logger.error("Error token no valido");
			throw new GeneralException("token invalido");
		}
		return response;
	}
	
	/**
     * Método que implementa la obtencion de auditoria y traza
     * 
     * @param session
     *            objeto de session a auditar
     * @return ResponseRest
     * 			  respuesta del objeto rest de auditoria 
     */
	public Audit getAudit (Session session) throws GeneralException{
		return null;
	}
	
	/**
     * Método para implementar la obtencion y generación de un token mediante una clave privada
     * y durante 10 minutos
     * 
     * @param username
     *            recibe nombre de usuario
     * @return String 
     * 			  respuesta del token generado 
     */
	public String getJWTToken(String username) throws GeneralException{
		String token = Jwts
				.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return token;
	}
	
	/**
     * Método que valida un token generado mediante una clave privada y comprueba su vigencia y validez
     * 
     * @param session
     *            recibe el objeto session con el token
     * @return Claims
     * 			  respuesta del objeto con la informacion del token 
     */
	public Claims validateToken(Session session) throws GeneralException{
		String jwtToken = session.getSession();
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
	}
}
