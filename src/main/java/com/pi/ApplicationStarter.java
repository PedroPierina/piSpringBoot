package com.pi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.pi")
public class ApplicationStarter extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}
}
