package com.cgrdev.whitecollar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class WhiteCollarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteCollarApplication.class, args);
	}

}
