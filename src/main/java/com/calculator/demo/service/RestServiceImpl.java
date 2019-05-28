package com.calculator.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calculator.demo.modelo.Audit;
import com.calculator.demo.modelo.Operation;
import com.calculator.demo.modelo.ResponseRest;
import com.calculator.demo.modelo.Session;
import com.calculator.demo.repository.SessionRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service("restService")
@Transactional
public class RestServiceImpl implements RestService{
	
	@Autowired
	private SessionRepository sessionRepository;
	
	private final String secretKey = "aaaZZZ";

	public ResponseRest getSession(String user){
		ResponseRest response = new ResponseRest();
		String token = getJWTToken(user);
		Session session = new Session();
		session.setCreationDate(new Date());
		session.setSession(token);
		session.setUsername(user);
		response.setSession(session);
//		sessionRepository.save(session);
		return response;
	}
	
	public ResponseRest putOperando(Operation operation){
		Session session = operation.getSession();
		Claims claims = validateToken(session);
		if (claims.get("authorities") != null) {
//			Token exitoso
		} else {
//			SecurityContextHolder.clearContext();
		}
		return null;
	}
	
	public ResponseRest putOperador(Operation operation){
		return null;
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
		return "token: " + token;
	}
	
	private Claims validateToken(Session session) {
		String jwtToken = session.getSession();
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
	}
}
