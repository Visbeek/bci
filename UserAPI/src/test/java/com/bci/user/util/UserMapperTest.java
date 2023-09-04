package com.bci.user.util;

import com.bci.user.dto.PhoneDTO;
import com.bci.user.dto.UserDTO;
import com.bci.user.dto.UserResponseDTO;
import com.bci.user.entity.Phone;
import com.bci.user.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private static final String NAME = "John Doe";
    private static final String EMAIL= "hola@test.cl";
    private static final String PASSWORD = "Password12";
    private static final Integer CITY_CODE = 12;
    private static final Integer COUNTRY_CODE = 34;
    private static final Long PHONE_NUMBER = 1L;
    private static final String TOKEN = "fs4g55h4gdf3r4tert";


    @Test
    void userFromUserDTOTest(){
        User response = UserMapper.userFromUserDTO(createUserDTO());
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void userResponseDTOFromUserTest(){
        UserResponseDTO response = UserMapper.userResponseDTOFromUser(createUser());
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void userResponseDTOListFromUserListTest(){
        List<User> userList = new ArrayList<>();
        userList.add(createUser());

        List<UserResponseDTO> response = UserMapper.userResponseDTOListFromUserList(userList);
        assertThat(response).isNotEmpty();
        assertThat(response.get(0).getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void updateUserFromUserDTOTest(){
        User response = UserMapper.updateUserFromUserDTO(createUser(), createUserDTO());
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(EMAIL);
    }

    private User createUser(){
        List<Phone> phoneList = new ArrayList<>();

        Phone phone = new Phone();
        phone.setId(1L);
        phone.setCityCode(CITY_CODE);
        phone.setCountryCode(COUNTRY_CODE);
        phone.setNumber(PHONE_NUMBER);

        phoneList.add(phone);

        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setPhones(phoneList);
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken(TOKEN);
        user.setIsActive(true);

        return user;
    }

    private UserDTO createUserDTO(){

        List<PhoneDTO> phoneDTOList = new ArrayList<>();

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber(PHONE_NUMBER);
        phoneDTO.setCountryCode(COUNTRY_CODE);
        phoneDTO.setCityCode(CITY_CODE);

        phoneDTOList.add(phoneDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(EMAIL);
        userDTO.setName(NAME);
        userDTO.setPassword(PASSWORD);
        userDTO.setPhones(phoneDTOList);

        return userDTO;

    }
}
