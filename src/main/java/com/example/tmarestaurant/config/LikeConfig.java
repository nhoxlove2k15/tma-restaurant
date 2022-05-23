package com.example.tmarestaurant.config;

import com.example.tmarestaurant.model.Like;
import com.example.tmarestaurant.repository.LikeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Configuration
public class LikeConfig {
    @Bean
    CommandLineRunner commandLineRunnerLike (LikeRepository likeRepository) {
        return args -> {
            Like like1 = new Like(
                    16L,
                    9L
            );
            Like like2 = new Like(
                    16L,
                    10L
            );
            Like like3 = new Like(
                    17L,
                    10L
            );

            likeRepository.saveAll(Arrays.asList(like1,like2,like3));
        };
    }
}
