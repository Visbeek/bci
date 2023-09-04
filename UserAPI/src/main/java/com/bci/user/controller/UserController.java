package com.bci.user.controller;

import com.bci.user.dto.LoginDTO;
import com.bci.user.dto.LoginResponseDTO;
import com.bci.user.dto.UserDTO;
import com.bci.user.dto.UserResponseDTO;
import com.bci.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation("Método para crear un usuario")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation("Método para obtener un usuario activo por id")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("Método para obtener todos los usuarios activos")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Método para actualizar un usuario")
    public ResponseEntity<UserResponseDTO> updateUsers(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Método para realizar un borrado lógico de un usuario")
    public ResponseEntity<UserResponseDTO> logicalDeleteUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.logicalDeleteUser(id), HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation("Método para autenticarse con el email y password")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(userService.login(loginDTO), HttpStatus.OK);
    }
}
