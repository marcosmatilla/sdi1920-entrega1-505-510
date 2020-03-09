package com.uniovi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Sdi1920Entrega1505510Application {

	public static void main(String[] args) {
		SpringApplication.run(Sdi1920Entrega1505510Application.class, args);
	}

}
