package com.nativa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class NativaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NativaApplication.class, args);
	}

}
