package com.palindrome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.palindrome.repository")
@EnableCaching
public class PalindromeVerifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(PalindromeVerifierApplication.class, args);
	}

}
