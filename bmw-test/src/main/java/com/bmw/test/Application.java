package com.bmw.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Class <code>Application</code> is main entry of whole application
 *
 * @author gyang
 * 
 */
@SpringBootApplication
@EnableJpaRepositories
@EntityScan("com.bmw.test.domain")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
