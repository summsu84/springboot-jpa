package com.teamjw.tripapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/*@ComponentScan({"com.teamjw.tripapp.common.util"})*/
/*@SpringBootApplication()*/
@SpringBootApplication
@EnableConfigurationProperties
public class TripappUserApplication {

	public static void main(String[] args) {

		//System.out.println("gg" + System.getProperty("server.servlet.context-path"));
		//System.setProperty("server.servlet.context-path", "/api");
//		System.out.println("gg : " + System.getProperty("server.servlet.context-path"));
		SpringApplication.run(TripappUserApplication.class, args);
	}

}
