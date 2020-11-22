package com.example.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.assignment.entity.User;
import com.example.assignment.payload.UserRequestObject;


public interface UserService {
	public User createUser(UserRequestObject requestObject);
	public boolean checkUserByEmail(String email);
	public List<User> getAllUser();
	User createOrganizationUser(UserRequestObject requestObject);
	User deleteUser(long userId);
	User deleteOrganizationalUser(long userId);
}

