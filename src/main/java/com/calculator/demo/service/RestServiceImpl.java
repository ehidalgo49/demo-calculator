package com.calculator.demo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	private final String secretKey = "aaaZZZ";

	public ResponseRest getSession(String user){
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
	
	public ResponseRest putOperando(Operation operation){
		System.out.println(operation.getSession());
		ResponseRest response = new ResponseRest();
		Claims claims = validateToken(operation.getSession());
//		System.out.println(claims.toString());
		if (claims.get("sub") != null) {
//			Token exitoso
			System.out.println("correcto");
			Session session = sessionRepository.findBySession(operation.getSession().getSession());
			System.out.println(session.toString());
			operation.setCreationDate(new Date());
			operation.setSession(session);
			System.out.println(operation.toString());
			response.setBody((Operation)operationRepository.save(operation));
		} else {
			System.out.println("Error");
//			SecurityContextHolder.clearContext();
		}
		return response;
	}
	
	public ResponseRest putOperador(Operation operation){
		System.out.println(operation.getSession());
		ResponseRest response = new ResponseRest();
		Claims claims = validateToken(operation.getSession());
//		System.out.println(claims.toString());
		if (claims.get("sub") != null) {
//			Token exitoso
			System.out.println("correcto");
			Session session = sessionRepository.findBySession(operation.getSession().getSession());
			System.out.println(session.toString());
			operation.setCreationDate(new Date());
			operation.setSession(session);
			System.out.println(operation.toString());
			
			BigDecimal resultado = new BigDecimal(0);
			
			List<Operation> operations = operationRepository.findAllBySessionAndOperador(session, null);
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
							System.out.println("no es posible dividir entre cero");
						}
					}else if(operation.getOperador().compareTo("^") == 0){
						resultado = resultado.pow(op.getOperando().intValue());
					}else{
						System.out.println("operacion no existente");
					}
				}
				operation.setOperando(resultado);
				operation.setOperador(null);
				response.setBody(operationRepository.save(operation));
			}else{
				System.out.println("debe haber minimo dos operandos para operar");
			}
		} else {
			System.out.println("Error token no valido");
//			SecurityContextHolder.clearContext();
		}
		return response;
	}
	
	public Audit getAudit (Session session){
		return null;
	}
	
	private String getJWTToken(String username) {
		String token = Jwts
				.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return token;
	}
	
	private Claims validateToken(Session session) {
		String jwtToken = session.getSession();
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
	}
}
