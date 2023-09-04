package com.bci.user.util;

import com.bci.user.dto.PhoneDTO;
import com.bci.user.dto.UserDTO;
import com.bci.user.dto.UserResponseDTO;
import com.bci.user.entity.Phone;
import com.bci.user.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User userFromUserDTO(UserDTO userDTO){

        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhones(getPhoneFromPhoneDTO(userDTO.getPhones()));
        user.setLastLogin(new Date());
        user.setToken(JWTUtils.generateJWT(userDTO.getEmail()));

        return user;

    }

    public static UserResponseDTO userResponseDTOFromUser(User user){

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPassword(user.getPassword());
        userResponseDTO.setPhones(getPhoneDTOFromPhone(user.getPhones()));
        userResponseDTO.setCreated(user.getCreated());
        userResponseDTO.setModified(user.getModified());
        userResponseDTO.setIsActive(user.getIsActive());
        userResponseDTO.setLastLogin(user.getLastLogin());
        userResponseDTO.setToken(user.getToken());

        return userResponseDTO;
    }

    public static List<UserResponseDTO> userResponseDTOListFromUserList(List<User> users){
        return users.stream().map((UserMapper::userResponseDTOFromUser)).collect(Collectors.toList());
    }

    public static User updateUserFromUserDTO(User user, UserDTO userDTO){
        if (userDTO.getName() != null) user.setName(userDTO.getName());
        if (userDTO.getPassword() != null) user.setPassword(userDTO.getPassword());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());

        return user;
    }

    private static List<Phone> getPhoneFromPhoneDTO(List<PhoneDTO> phoneDTOS){
        List<Phone> phoneList = new ArrayList<>();

        for(PhoneDTO phoneDTO : phoneDTOS){
            Phone phone = new Phone();

            phone.setCityCode(phoneDTO.getCityCode());
            phone.setNumber(phoneDTO.getNumber());
            phone.setCountryCode(phoneDTO.getCountryCode());

            phoneList.add(phone);
        }

        return phoneList;
    }

    private static List<PhoneDTO> getPhoneDTOFromPhone(List<Phone> phones){
        List<PhoneDTO> phoneList = new ArrayList<>();

        for(Phone phone: phones){
            PhoneDTO phoneDTO = new PhoneDTO();

            phoneDTO.setCityCode(phone.getCityCode());
            phoneDTO.setNumber(phone.getNumber());
            phoneDTO.setCountryCode(phone.getCountryCode());

            phoneList.add(phoneDTO);
        }

        return phoneList;
    }

}
