package com.example.tmarestaurant.config;

import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.BillDetail;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.repository.BillDetailRepository;
import com.example.tmarestaurant.repository.BillRepository;
import com.example.tmarestaurant.utils.MenuOrigin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Configuration
public class BillConfig {

    @Bean
    CommandLineRunner commandLineRunnerBill (BillRepository billRepository, BillDetailRepository billDetailRepository) {
        return args -> {

            Menu noodles = new Menu(
                    "noodles",
                    "noodles is good for lunch",
                    25.0d,
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
            Menu chicken = new Menu(
                    "chickern",
                    "chicken for lunch",
                    75.0d,
                    "url3",
                    2L

            );
            System.out.println("================================================================ 1 " );
            MenuOrigin menuOrigin1 = new MenuOrigin(noodles.getName(),noodles.getDescription(),noodles.getPrice(),2);
            MenuOrigin menuOrigin2 = new MenuOrigin(egg.getName(),egg.getDescription(),egg.getPrice(),3);
            MenuOrigin menuOrigin3 = new MenuOrigin(pepsi.getName(),pepsi.getDescription(),pepsi.getPrice(),1);
            MenuOrigin menuOrigin4 = new MenuOrigin(chicken.getName(),chicken.getDescription(),chicken.getPrice(),5);


            BillDetail billDetail1 = new BillDetail();
            billDetail1.getMenuOrigin().addAll(Arrays.asList(menuOrigin1,menuOrigin2));
            BillDetail billDetail2 = new BillDetail();
            billDetail2.getMenuOrigin().add(menuOrigin3);
            BillDetail billDetail3 = new BillDetail();
            billDetail3.getMenuOrigin().add(menuOrigin4);

            System.out.println("================================================================ 2 " );
            billDetailRepository.saveAll(Arrays.asList(billDetail1,billDetail2,billDetail3));



//            Bill bill1 = new Bill(18L,1L);
//            Bill bill2 = new Bill(18L,2L);
//            Bill bill3 = new Bill(19L,3L);
            System.out.println("================================================================ 3 " );



//            billRepository.saveAll(Arrays.asList(bill1,bill2,bill3));
            System.out.println("================================================================ 4 " );

        };
    }
}
