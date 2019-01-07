package org.mysoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.mysoft")
public class CompanySalesMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanySalesMgmtApplication.class, args);
	}

}

