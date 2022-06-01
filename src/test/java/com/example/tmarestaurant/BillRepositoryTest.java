package com.example.tmarestaurant;

import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.BillDetail;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.BillRepository;
import com.example.tmarestaurant.utils.MenuOrigin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import java.util.Arrays;

@DataJpaTest
public class BillRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BillRepository billRepository;

    @Test
    @Rollback(value = false)
    public void saveBill() {
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
        User user = User
                .builder()
                .fullname("ho hai hieu")
                .username("hieuhh")
                .password("123456")
                .build();
        User userAdd = entityManager.persist(user);
        BillDetail billDetailAdd = entityManager.persist(billDetail1);
        Bill bill = billRepository.save(new Bill(userAdd,billDetailAdd));
        Assertions.assertThat(bill.getId()).isGreaterThan(0);
    }

}
