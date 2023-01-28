package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		WhatsappController whatsappController=new WhatsappController();
		System.out.println(whatsappController.createUser("muthyam","123"));
	}
}
