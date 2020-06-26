package com.example.demo;
import com.example.demo.controller.MyController;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Connet;
import com.example.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

@EntityScan
@ComponentScan({""})
@SpringBootApplication
public class DemoSpringBootApplication {


	public static void main(String[] args) {

		SpringApplication.run(DemoSpringBootApplication.class, args);

	}
}

