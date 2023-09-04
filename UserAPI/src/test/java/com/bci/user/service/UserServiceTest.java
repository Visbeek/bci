package com.bci.user.service;

import com.bci.user.dto.*;
import com.bci.user.entity.Phone;
import com.bci.user.entity.User;
import com.bci.user.exception.UserNotFoundException;
import com.bci.user.repository.UserRepository;
import com.bci.user.service.impl.UserServiceImpl;
import com.bci.user.util.JWTUtils;
import com.bci.user.util.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String EMAIL = "test@mail.cl";
    private static final String PASSWORD = "MYp4s5w0rd11";
    @Mock
    private UserRepository userRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUserTest(){
        when(userRepository.existsUserByEmail(EMAIL)).thenReturn(false);
        when(userRepository.saveAndFlush(any())).thenReturn(createUser());
        UserResponseDTO response = userService.createUser(createUserDTO());
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void getUserByIdTest(){
        when(userRepository.findByIdAndIsActiveTrue(any())).thenReturn(Optional.of(createUser()));
        UserResponseDTO response = userService.getUserById(UUID.randomUUID().toString());
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void getUserByIdNotFoundTest(){
        when(userRepository.findByIdAndIsActiveTrue(any())).thenReturn(Optional.empty());
        String userId = UUID.randomUUID().toString();
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }
    @Test
    void getAllUsersTest(){
        when(userRepository.findAllByIsActiveTrue()).thenReturn(createListOfUsers());
        List<UserResponseDTO> response = userService.getAllUsers();
        assertThat(response).isNotEmpty();
        assertThat(response.get(0).getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void updateUser(){
        when(userRepository.saveAndFlush(any())).thenReturn(createUser());
        when(userRepository.findByIdAndIsActiveTrue(any())).thenReturn(Optional.of(createUser()));
        UserResponseDTO response = userService.updateUser(UUID.randomUUID().toString(), createUserDTO());
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void logicalDeleteUserTest() {
        when(userRepository.findByIdAndIsActiveTrue(any())).thenReturn(Optional.of(createUser()));

        User user = createUser();
        user.setIsActive(false);

        when(userRepository.saveAndFlush(any())).thenReturn(user);
        UserResponseDTO response = userService.logicalDeleteUser(UUID.randomUUID().toString());
        assertThat(response).isNotNull();
        assertThat(response.getIsActive()).isFalse();
    }

    @Test
    void loginTest() {
        when(userRepository.findByEmailAndIsActiveTrue(any())).thenReturn(Optional.of(createUser()));
        LoginResponseDTO response = userService.login(createLoginDTO());
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isNotNull();
    }


    private UserDTO createUserDTO(){
        List<PhoneDTO> phoneList = new ArrayList<>();
        PhoneDTO phone = new PhoneDTO();
        phone.setCountryCode(56);
        phone.setNumber(123456l);
        phone.setCityCode(12);

        phoneList.add(phone);

        UserDTO user = new UserDTO();

        user.setName("John Doe");
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setPhones(phoneList);

        return user;
    }
    private User createUser(){

        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setCountryCode(56);
        phone.setId(1L);
        phone.setCityCode(12);

        phoneList.add(phone);

        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setName("John Doe");
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setPhones(phoneList);
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken(JWTUtils.generateJWT(EMAIL));
        user.setIsActive(true);

        return user;
    }

    private List<User> createListOfUsers(){
        List<User> users = new ArrayList<>();
        users.add(createUser());

        return users;
    }

    private LoginDTO createLoginDTO(){
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail(EMAIL);
        loginDTO.setPassword(PASSWORD);

        return loginDTO;
    }

}
