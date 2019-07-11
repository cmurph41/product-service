package com.jungle.scout.product;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@Slf4j
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase(ProductRepository repo) {
		return args -> {
//			log.info("test");
			log.info("Preloading "+repo.save(new Product("B002QYW81W", "Baby", 32, 4.3)));
			log.info("Preloading "+repo.save(new Product("B002QYW81X", "Baby", 33, 5.3)));

		};
		
	}
}
