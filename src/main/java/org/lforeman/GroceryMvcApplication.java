package org.lforeman;

import org.lforeman.controllers.EmailController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class GroceryMvcApplication {

	public static void main(String[] args) {
		run(GroceryMvcApplication.class, args);
		EmailController.configProperties();
	}

}
