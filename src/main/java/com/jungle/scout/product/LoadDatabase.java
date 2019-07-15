package com.jungle.scout.product;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@Slf4j
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase(ProductRepository repo) {
		return args -> {
//			log.info("Preloading "+repo.save(new Product("B002QYW81W", "Baby", 32, Arrays.asList(5.1,2.3,4.5))));
//			log.info("Preloading "+repo.save(new Product("B002QYW81X", "Baby", 3, Arrays.asList(1.1,2.78,4.67))));

		};
		
	}
}
