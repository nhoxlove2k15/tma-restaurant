package com.example.tmarestaurant.config;

import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.repository.MenuRepository;
import com.example.tmarestaurant.utils.MenuOrigin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class MenuConfig {
    @Bean
    CommandLineRunner commandLineRunnerMenu (MenuRepository menuRepository) {
        return args -> {
            Menu pizza = new Menu(
                "pizza",
                    "pizza from italya",
                    20.0d,
                  "url1",
                    1L

            );
            Menu coca = new Menu(
                    "coca",
                    "coca is a popular softdrink",
                    25.0d,
                    "url2",
                    4L

            );
            Menu whitskey = new Menu(
                    "whitskey",
                    "whiteskey is a 40 Celius alcohol",
                    125.0d,
                    "url3",
                    5L

            );
            Menu tiger = new Menu(
                    "tiger",
                    "tiger is a beer",
                    25.0d,
                    "url3",
                    5L

            );
            Menu rice = new Menu(
                    "rice",
                    "rice is nice choice for dinner",
                    50.0d,
                    "url3",
                    3L

            );
            Menu hambuger = new Menu(
                    "hambuger",
                    "hambuger is fastfood for breakfast",
                    20.0d,
                    "url3",
                    1L

            );
            Menu chicken = new Menu(
                    "chickern",
                    "chicken for lunch",
                    75.0d,
                    "url3",
                    2L

            );
            Menu egg = new Menu(
                    "egg",
                    "egg is good for breakfast",
                    25.0d,
                    "url3",
                    1L

            );
            Menu pepsi = new Menu(
                    "pepsi",
                    "pepsi is popular softdrink in Brazil",
                    30.0d,
                    "url3",
                    4L

            );
            Menu noodles = new Menu(
                    "noodles",
                    "noodles is good for lunch",
                    25.0d,
                    "url3",
                    2L

            );
            Menu numberOne = new Menu(
                    "numberOne",
                    "yellow softdrink",
                    25.0d,
                    "url4",
                    new ArrayList<>(),
                    4L

            );
            numberOne.getImagesjson().add(new MenuOrigin(numberOne.getName(),numberOne.getDescription(),numberOne.getPrice()));
            numberOne.getImagesjson().add(new MenuOrigin(noodles.getName(),noodles.getDescription(),noodles.getPrice()));
//            numberOne.getImagesjson().add("789");
            menuRepository.saveAll(Arrays.asList(pizza,coca,whitskey,noodles,pepsi,egg,rice,hambuger,chicken,tiger,numberOne));
//            menuRepository.save(pizza);
        };
    }
}
