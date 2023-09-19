package com.shah.multipledb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author NORUL
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class MultipleDbSortFilterSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDbSortFilterSearchApplication.class, args);
	}

}
