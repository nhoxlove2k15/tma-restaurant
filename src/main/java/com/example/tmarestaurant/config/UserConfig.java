package com.example.tmarestaurant.config;

import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.model.UserRole;
import com.example.tmarestaurant.repository.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {
        return args -> {
            User duy = new User(
                    "duydk",
                    "duy",
                    "123456",
                    "09090909",
                    UserRole.ADMIN
            );
            User hieu = new User(
                    "hieult",
                    "hieu",
                    "123456",
                    "0808080",
                    UserRole.CUSTOMER

            );
            User dat = new User(
                    "datbt",
                    "dat",
                    "axzc",
                    "0707070",
                    UserRole.CUSTOMER
            );
            User tam = new User(
                    "tamnv",
                    "tam",
                    "aaaaaa",
                    "060606",
                    UserRole.ADMIN
            );
            User quan = new User(
                    "quandtm",
                    "quan",
                    "bbbbb",
                    "050505",
                    UserRole.CUSTOMER
            );

//            Iterable<String> iterable = Arrays.asList("john", "tom", "jane");
//            ArrayList<User> users = new ArrayList<>();
//            users.addAll(Collection)
            userRepository.saveAll(Arrays.asList(duy, hieu, dat,tam,quan));


        };
    }
}
