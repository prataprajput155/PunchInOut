package com.punchInOut.controller;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableJpaRepositories(basePackages = "com.punchInOut.repository")
@EntityScan(basePackages="com.punchInOut")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableScheduling
public class DemoApplication {

   
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
