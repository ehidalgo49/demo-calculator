package com.calculator.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.demo.exception.GeneralException;
import com.calculator.demo.modelo.Header;
import com.calculator.demo.modelo.Operation;
import com.calculator.demo.modelo.ResponseRest;
import com.calculator.demo.service.RestService;


@RestController
public class ServiceRestController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceRestController.class);
	
	@Autowired
	RestService restService;  
	 
//	    @RequestMapping(value = "/user/", method = RequestMethod.GET)
//	    public ResponseEntity<List<Audit>> findAuditBySession() {
//	        List<Audit> users = restService.findAuditBySession();
//	        if(users.isEmpty()){
//	            return new ResponseEntity<List<Audit>>(HttpStatus.NO_CONTENT);
//	        }
//	        return new ResponseEntity<List<Audit>>(users, HttpStatus.OK);
//	    }
	 
	/**
     * Método que genera endpoint rest para solicitar un token de sesion
     * 
     * @param username
     *            recibe nombre de usuario via get
     * @return JSON
     * 			  objeto JSON con la respuesta 
     */
	@RequestMapping(value = "/calc/{usuario}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseRest> getSession(@PathVariable("usuario") String user) {
        logger.debug("getSession for user: " + user);
        ResponseRest response = new ResponseRest();
        Header header = new Header();
        try{
	        response = restService.getSession(user);
	        if (response == null) {
	            logger.error("User " + user + " not save");
				header.setCode("40003");
				header.setMessage("User " + user + " not save");
				response = new ResponseRest();
				response.setHeader(header);
	            return new ResponseEntity<ResponseRest>(response, HttpStatus.NO_CONTENT);
	        }
	        header.setCode("200");
			header.setMessage("operación exitosa");
			response.setHeader(header);
		}catch(GeneralException e){
			header.setCode("40002");
			header.setMessage(e.getMessage());
			response.setHeader(header);
		}catch(Exception ex){
			header.setCode("40001");
			header.setMessage(ex.getMessage());
			response.setHeader(header);
		}
        return new ResponseEntity<ResponseRest>(response, HttpStatus.OK);
    }
    
	/**
     * Método que genera endpoint rest para grabar un operando
     * 
     * @param JSON
     *            objeto JSON con el operando
     * @return JSON
     * 			  objeto JSON con la respuesta 
     */
    @RequestMapping(value = "/calc/putOperando/", method = RequestMethod.POST)
    public ResponseEntity<ResponseRest> putOperando(@RequestBody Operation operation) {
    	logger.debug("putOperando for session: " + operation.getSession() + " and number: " + operation.getOperando());
    	ResponseRest response = new ResponseRest();
    	Header header = new Header();
    	try{
	        response = restService.putOperando(operation);
			if (response == null) {
				logger.error("putOperando have errors");
				header.setCode("40003");
				header.setMessage("putOperando have errors");
				response = new ResponseRest();
				response.setHeader(header);
	            return new ResponseEntity<ResponseRest>(response, HttpStatus.NO_CONTENT);
	        }
			header.setCode("200");
			header.setMessage("operación exitosa");
			response.setHeader(header);
    	}catch(GeneralException e){
    		header.setCode("40002");
    		header.setMessage(e.getMessage());
    		response.setHeader(header);
    	}catch(Exception ex){
    		header.setCode("40001");
    		header.setMessage(ex.getMessage());
    		response.setHeader(header);
    	}
    	return new ResponseEntity<ResponseRest>(response, HttpStatus.OK);
    }
    
    /**
     * Método que genera endpoint rest para calcular mediante un operador
     * 
     * @param JSON
     *            objeto JSON con el operador
     * @return JSON
     * 			  objeto JSON con el calculo y la respuesta 
     */
    @RequestMapping(value = "/calc/putOperador/", method = RequestMethod.POST)
    public ResponseEntity<ResponseRest> putOperador(@RequestBody Operation operation) {
    	logger.debug("putOperador for session: " + operation.getSession() + " and operador: " + operation.getOperador());
    	ResponseRest response = new ResponseRest();
    	Header header = new Header();
    	try{
	        response = restService.putOperador(operation);
			if (response == null) {
				logger.error("putOperador have errors");
				header.setCode("40003");
				header.setMessage("putOperador have errors");
				response = new ResponseRest();
				response.setHeader(header);
	            return new ResponseEntity<ResponseRest>(response, HttpStatus.NO_CONTENT);
	        }
			header.setCode("200");
			header.setMessage("operación exitosa");
			response.setHeader(header);
    	}catch(GeneralException e){
    		header.setCode("40002");
    		header.setMessage(e.getMessage());
    		response.setHeader(header);
    	}catch(Exception ex){
    		header.setCode("40001");
    		header.setMessage(ex.getMessage());
    		response.setHeader(header);
    	}
    	return new ResponseEntity<ResponseRest>(response, HttpStatus.OK);
    }
	 
//	    @RequestMapping(value = "/user/", method = RequestMethod.POST)
//	    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
//	        System.out.println("Creating User " + user.getUsername());
//	 
//	        if (restService.isUserExist(user)) {
//	            System.out.println("A User with name " + user.getUsername() + " already exist");
//	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//	        }
//	 
//	        restService.saveUser(user);
//	 
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
//	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//	    }
}
