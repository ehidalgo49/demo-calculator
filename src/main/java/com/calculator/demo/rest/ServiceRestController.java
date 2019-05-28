package com.calculator.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.demo.modelo.Operation;
import com.calculator.demo.modelo.ResponseRest;
import com.calculator.demo.service.RestService;


@RestController
public class ServiceRestController {

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
	 
	@RequestMapping(value = "/calc/{usuario}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseRest> getSession(@PathVariable("usuario") String user) {
        System.out.println("getSession for user: " + user);
        ResponseRest response = restService.getSession(user);
        if (response == null) {
            System.out.println("User " + user + " not found");
            return new ResponseEntity<ResponseRest>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ResponseRest>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/calc/putOperando/", method = RequestMethod.POST)
    public ResponseEntity<ResponseRest> putOperando(@RequestBody Operation operation) {
    	System.out.println("putOperando for session: " + operation.getSession() + " and number: " + operation.getOperando());
    	ResponseRest response = restService.putOperando(operation);
		if (response == null) {
            System.out.println("putOperando have error");
            return new ResponseEntity<ResponseRest>(HttpStatus.NO_CONTENT);
        }
    	return new ResponseEntity<ResponseRest>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/calc/putOperador/", method = RequestMethod.POST)
    public ResponseEntity<ResponseRest> putOperador(@RequestBody Operation operation) {
    	System.out.println("putOperador for session: " + operation.getSession() + " and operador: " + operation.getOperador());
    	ResponseRest response = restService.putOperador(operation);
		if (response == null) {
            System.out.println("putOperador have error");
            return new ResponseEntity<ResponseRest>(HttpStatus.NO_CONTENT);
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
