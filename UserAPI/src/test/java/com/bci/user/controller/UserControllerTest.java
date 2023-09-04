package com.bci.user.controller;

import com.bci.user.dto.*;
import com.bci.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    private static final String NAME = "John Doe";
    private static final String EMAIL = "hola@test.cl";
    private static final String PASSWORD = "Password12";
    private static final Integer CITY_CODE = 12;
    private static final Integer COUNTRY_CODE = 34;
    private static final Long PHONE_NUMBER = 1L;
    private static final String TOKEN = "fs4g55h4gdf3r4tert";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void createUserTest() throws Exception {
        when(userService.createUser(any())).thenReturn(createUserResponseDTO());
        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateJSONRequest(createUserDTO())))
                .andExpect(status().isCreated());
    }

    @Test
    void getUserByIdTest() throws Exception {
        when(userService.getUserById(any())).thenReturn(createUserResponseDTO());
        this.mockMvc.perform(get("/user/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsersTest() throws Exception {
        List<UserResponseDTO> response = new ArrayList<>();
        response.add(createUserResponseDTO());

        when(userService.getAllUsers()).thenReturn(response);
        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUsersTest() throws Exception {
        when(userService.updateUser(any(), any())).thenReturn(createUserResponseDTO());
        this.mockMvc.perform(put("/user/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateJSONRequest(createUserDTO())))
                .andExpect(status().isOk());
    }

    @Test
    void logicalDeleteUserTest() throws Exception {
        when(userService.logicalDeleteUser(any())).thenReturn(createUserResponseDTO());
        this.mockMvc.perform(delete("/user/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void loginTest() throws Exception {
        when(userService.login(any())).thenReturn(new LoginResponseDTO(TOKEN));
        this.mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateJSONRequest(createLoginDTO())))
                .andExpect(status().isOk());
    }

    private String generateJSONRequest(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(object);
    }

    private UserResponseDTO createUserResponseDTO() {
        UserResponseDTO user = new UserResponseDTO();

        user.setId(UUID.randomUUID().toString());
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setPhones(createPhoneDTOList());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken(TOKEN);
        user.setIsActive(true);

        return user;
    }

    private UserDTO createUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(EMAIL);
        userDTO.setName(NAME);
        userDTO.setPassword(PASSWORD);
        userDTO.setPhones(createPhoneDTOList());

        return userDTO;

    }

    private List<PhoneDTO> createPhoneDTOList() {
        List<PhoneDTO> phoneDTOList = new ArrayList<>();

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber(PHONE_NUMBER);
        phoneDTO.setCountryCode(COUNTRY_CODE);
        phoneDTO.setCityCode(CITY_CODE);

        phoneDTOList.add(phoneDTO);

        return phoneDTOList;
    }

    private LoginDTO createLoginDTO() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail(EMAIL);
        loginDTO.setPassword(PASSWORD);

        return loginDTO;
    }
}
