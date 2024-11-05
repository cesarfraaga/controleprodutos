package com.cesarfraga.controller;

import com.cesarfraga.dto.UserDto;
import com.cesarfraga.entity.User;
import com.cesarfraga.entity.Role;
import com.cesarfraga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        User existingUser = userService.findByUsername(userDto.getUsername());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());

        // Obter as roles e associá-las ao usuário
        Set<Role> userRoles = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            Role role = userService.findRoleByName(roleName);
            if (role != null) {
                userRoles.add(role);
            }
        }

        userService.saveUser(newUser, userRoles);
        return ResponseEntity.ok("User registered successfully");
    }
}
