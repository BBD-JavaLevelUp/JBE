package com.jbe.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

//	private void insertBean(BeanRepository repository) {
//		repository.save(new Bean("bean1", 5.01F));
//		repository.save(new Bean("bean1", 6F));
//		repository.save(new Bean("bean1", 7F));
//	}

}
