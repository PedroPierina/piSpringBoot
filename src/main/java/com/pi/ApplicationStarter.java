package com.pi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"/pi/src/main/java/com/pi/controller/Controller.java"})
public class ApplicationStarter extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}
	
	@RequestMapping("/insereCSV")
    public String greetingForm() {
        return "index2";
    }
}
