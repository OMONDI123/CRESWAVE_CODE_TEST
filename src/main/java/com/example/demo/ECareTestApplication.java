package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class ECareTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECareTestApplication.class, args);
		//ImportIcdDExcellData importata=new ImportIcdDExcellData();
		//importata.printValuesFromexcell();
	}

}
