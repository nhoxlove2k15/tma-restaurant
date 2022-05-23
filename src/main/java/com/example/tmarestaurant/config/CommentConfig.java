package com.example.tmarestaurant.config;

import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.repository.CommentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Configuration
public class CommentConfig {
    @Bean
    CommandLineRunner commandLineRunnerComment(CommentRepository commentRepository) {
        return args -> {
            Comment comment1 = new Comment(
                    "this pizza is good",
                    16L,
                    6L
            );
            Comment comment2 = new Comment(
                    "this coca is suck",
                    16L,
                    7L
            );
            Comment comment3 = new Comment(
                    "this pizza in California is better than here",
                    17L,
                    6L
            );
            commentRepository.saveAll(Arrays.asList(comment1,comment2,comment3));
        };

    }
}
