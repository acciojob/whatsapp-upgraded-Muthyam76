package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		WhatsappController whatsappController=new WhatsappController();
		List<User> l=new ArrayList<>();
		User a=new User("a","1");
		User b=new User("b","2");
		User c=new User("c","3");
		l.add(a);
		l.add(b);
		l.add(c);

		System.out.println(whatsappController.createUser("muthyam","123"));
		System.out.println(whatsappController.createGroup(l).getName());
		System.out.println(whatsappController.createGroup(l).getName());

		System.out.println(whatsappController.createMessage("hello"));
		System.out.println(whatsappController.createMessage("hello"));
	}
}
