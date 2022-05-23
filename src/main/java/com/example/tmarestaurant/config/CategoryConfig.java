package com.example.tmarestaurant.config;

import com.example.tmarestaurant.model.Category;
import com.example.tmarestaurant.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunnerCategory (CategoryRepository categoryRepository) {
        return args -> {
            Category breakfast = new Category( "breakfast" );
            Category lunch = new Category( "lunch" );
            Category dinner = new Category( "dinner" );
            Category softdrink = new Category( "softdrink" );
            Category alcohol = new Category( "alcohol" );

            categoryRepository.saveAll(Arrays.asList(breakfast,lunch,dinner,softdrink,alcohol));



        };
    }
}
