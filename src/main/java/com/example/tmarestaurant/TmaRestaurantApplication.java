package com.example.tmarestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.util.Properties;

@SpringBootApplication
public class TmaRestaurantApplication {

	public static void main(String[] args) {
//		Properties jpaProperties = new Properties();
//
//
//		jpaProperties.put("hibernate.default_schema", "my_default_schema");
//		ManagementFactory entityManagerFactoryBean = new ManagementFactory();
//		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		SpringApplication.run(TmaRestaurantApplication.class, args);
	}

}
