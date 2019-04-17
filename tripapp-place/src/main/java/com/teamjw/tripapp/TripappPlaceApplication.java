package com.teamjw.tripapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@ComponentScan({"com.teamjw.tripapp.common.util"})*/
/*@SpringBootApplication()*/
@SpringBootApplication
public class TripappPlaceApplication {

	public static void main(String[] args) {

		//System.out.println("gg" + System.getProperty("server.servlet.context-path"));
		System.setProperty("server.servlet.context-path", "/api");
//		System.out.println("gg : " + System.getProperty("server.servlet.context-path"));
		SpringApplication.run(TripappPlaceApplication.class, args);
	}

}
