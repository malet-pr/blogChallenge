package org.alkemy.java.individual.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yuequan.jpa.soft.delete.repository.EnableJpaSoftDeleteRepositories;

@SpringBootApplication
@EnableJpaSoftDeleteRepositories
public class BlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

}
