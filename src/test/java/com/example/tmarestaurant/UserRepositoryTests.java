package com.example.tmarestaurant;


import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {
        User user = User
                .builder()
                .fullname("ho hai hieu")
                .username("hieuhh")
                .password("123456")
                .build();
        userRepository.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getUserTest() {
        User user = userRepository.findById(1L).get();
        Assertions.assertThat(user.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void getListOfUsersTest() {
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateUserTest() {
        User user = userRepository.findById(1L).get();
        user.setFullname("hieu zoro");
        User userUpdated = userRepository.save(user);
        Assertions.assertThat(userUpdated.getFullname()).isEqualTo("hieu zoro");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    void deleteUserTest() {
        User user = userRepository.findById(1L).get();
        userRepository.delete(user);
        User user1 = null ;
        Optional<User> optionalUser = userRepository.findByFullname("hieu zoro");

        if(optionalUser.isPresent()) {
            user1 = optionalUser.get();
        }
        Assertions.assertThat(user1).isNull();
    }
}
