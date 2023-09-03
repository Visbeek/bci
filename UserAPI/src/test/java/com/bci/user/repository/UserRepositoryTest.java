package com.bci.user.repository;

import com.bci.user.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    private static final String EMAIL = "test@mail.cl";
    private static final String ALT_EMAIL = "altest@mail.cl";

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup(){
        userRepository.saveAndFlush(generateUser());
    }

    @Test
    @Order(1)
    void saveUser_saved(){
        User user = userRepository.saveAndFlush(generateUser());
        assertThat(user.getId()).isNotNull();
    }

    @Test
    @Order(2)
    void checkIfUserExists_exists(){
        boolean result = userRepository.existsUserByEmail(EMAIL);
        assertThat(result).isTrue();
    }

    @Test
    @Order(3)
    void checkIfUserExists_doesNotExists(){
        boolean result = userRepository.existsUserByEmail(ALT_EMAIL);
        assertThat(result).isFalse();
    }

    @Test
    @Order(4)
    void findAllUsers_found(){
        List<User> result = userRepository.findAllByIsActiveTrue();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getEmail()).isEqualTo(EMAIL);
    }

    @Test
    @Order(5)
    void findUserByEmail_found(){
        Optional<User> result = userRepository.findByEmailAndIsActiveTrue(EMAIL);
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo(EMAIL);
    }



    private User generateUser(){
        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setName("John Doe");
        user.setPassword("mYp4s5w0rd");
        user.setEmail(EMAIL);

        return user;
    }

}
