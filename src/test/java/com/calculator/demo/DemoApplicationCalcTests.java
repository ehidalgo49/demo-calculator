package com.calculator.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.calculator.demo.modelo.Session;
import com.calculator.demo.rest.ServiceRestController;
import com.calculator.demo.service.RestService;

import io.jsonwebtoken.Claims;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceRestController.class)
@WebAppConfiguration
public class DemoApplicationCalcTests {

    private MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	   
	@MockBean
	RestService restService;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
    public void getSession() throws Exception {
		String uri = "/calc/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.param("usuario", "admin")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   assertTrue(content.length() > 0);
    }
	
	@Test
	public void createToken() throws Exception {
		String token = restService.getJWTToken("administrator");
		assertNotNull(token);
		assertTrue(token.length() > 0);
	}
	
	@Test
	public void validateToken() throws Exception {
		Session session = new Session();
		session.setSession("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwiaWF0IjoxNTU5MTA3MjQzLCJleHAiOjE1NTkxMDc4NDN9.c_bB7djuWbEsYT9R298XsoPx5-p52Z3Tmo-uA0VWUovdSHSbxiR3c3G-Us0J80DdfC-31gNu3Q4M1JHflp9bxA");
		Claims claims= restService.validateToken(session);
		assertNotNull(claims);
		assertNotNull(claims.get("sub"));
	}

}
