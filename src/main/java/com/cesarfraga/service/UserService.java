package com.cesarfraga.service;

import com.cesarfraga.entity.User;
import com.cesarfraga.entity.Role;
import com.cesarfraga.repository.UserRepository;
import com.cesarfraga.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user, Set<Role> roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
