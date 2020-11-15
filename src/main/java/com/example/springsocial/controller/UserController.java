package com.example.springsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.entity.User;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.UserPrincipal;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public User getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
