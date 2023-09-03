package com.bci.user.service;

import com.bci.user.dto.LoginDTO;
import com.bci.user.dto.LoginResponseDTO;
import com.bci.user.dto.UserDTO;
import com.bci.user.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    public UserResponseDTO createUser(UserDTO userDTO);

    public UserResponseDTO getUserById(String id);

    public List<UserResponseDTO> getAllUsers();

    public UserResponseDTO updateUser(String id, UserDTO userDTO);

    public UserResponseDTO logicalDeleteUser(String id);

    public LoginResponseDTO login(LoginDTO loginDTO);
}
