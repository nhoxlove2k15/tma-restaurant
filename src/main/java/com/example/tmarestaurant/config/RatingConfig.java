package com.example.tmarestaurant.config;

import com.example.tmarestaurant.model.Rating;
import com.example.tmarestaurant.repository.RatingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Configuration
public class RatingConfig {
    @Bean
    CommandLineRunner commandLineRunnerRating(RatingRepository ratingRepository) {
        return args -> {
            Rating rating1 = new Rating(
                    1.0D,
                    16L,
                    6L
            );
            Rating rating2 = new Rating(
                    5.0D,
                    17L,
                    6L
            );
            Rating rating3 = new Rating(
                    3.0D,
                    18L,
                    7L
            );

            ratingRepository.saveAll(Arrays.asList(rating1,rating2,rating3));
        };
    }
}
