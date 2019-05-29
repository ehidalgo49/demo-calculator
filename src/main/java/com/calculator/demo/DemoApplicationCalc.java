package com.calculator.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplicationCalc extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplicationCalc.class);

	public static void main(String[] args) {
		try{
			logger.info("Inicia la ejecución");
			SpringApplication.run(DemoApplicationCalc.class, args);
		}catch (Exception e){
			logger.error("Error en la inicialización de la aplicación");
		}
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("Inicia la creación del contexto");
		return application.sources(DemoApplicationCalc.class);
	}
}
