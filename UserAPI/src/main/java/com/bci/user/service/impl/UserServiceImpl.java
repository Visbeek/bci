package com.bci.user.service.impl;

import com.bci.user.dto.LoginDTO;
import com.bci.user.dto.LoginResponseDTO;
import com.bci.user.dto.UserDTO;
import com.bci.user.dto.UserResponseDTO;
import com.bci.user.entity.User;
import com.bci.user.exception.DuplicatedEmailException;
import com.bci.user.exception.UnauthorizedUserException;
import com.bci.user.exception.UserNotFoundException;
import com.bci.user.repository.UserRepository;
import com.bci.user.service.UserService;
import com.bci.user.util.UserMapper;
import com.bci.user.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser(UserDTO userDTO) {
        validateUser(userDTO);
        return UserMapper.userResponseDTOFromUser(userRepository.saveAndFlush(UserMapper.userFromUserDTO(userDTO)));
    }

    public UserResponseDTO getUserById(String id) {
        return UserMapper.userResponseDTOFromUser(this.findUser(id));
    }

    public List<UserResponseDTO> getAllUsers() {
        return UserMapper.userResponseDTOListFromUserList(userRepository.findAllByIsActiveTrue());
    }

    public UserResponseDTO updateUser(String id, UserDTO userDTO) {
        validateUser(userDTO);

        return UserMapper.userResponseDTOFromUser(userRepository.saveAndFlush(UserMapper.updateUserFromUserDTO(this.findUser(id), userDTO)));
    }

    public UserResponseDTO logicalDeleteUser(String id) {
        User user = findUser(id);
        user.setIsActive(false);

        return UserMapper.userResponseDTOFromUser(userRepository.saveAndFlush(user));
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmailAndIsActiveTrue(loginDTO.getEmail());

        if (!user.isPresent()) {
            throw new UserNotFoundException("Usuario inexistente");
        }

        if (!loginDTO.getPassword().equals(user.get().getPassword()))
            throw new UnauthorizedUserException("Usuario o contraseña incorrecta");

        return new LoginResponseDTO(user.get().getToken());
    }

    private User findUser(String id) {
        Optional<User> user = userRepository.findByIdAndIsActiveTrue(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("Usuario inexistente");
        }

        return user.get();
    }

    private void validateUser(UserDTO userDTO) {
        validator.validateMailFormat(userDTO.getEmail());
        validator.validatePasswordFormat(userDTO.getPassword());

        if (userRepository.existsUserByEmail(userDTO.getEmail())) {
            throw new DuplicatedEmailException("El correo ya registrado.");
        }
    }

}
