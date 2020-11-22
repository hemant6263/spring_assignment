package com.example.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.assignment.entity.User;
import com.example.assignment.model.AuthProvider;
import com.example.assignment.payload.UserRequestObject;
import com.example.assignment.repository.OrganizationRepository;
import com.example.assignment.repository.UserRepository;
import com.example.assignment.security.UserPrincipal;
@Service
public class UserserviceImpl implements UserService {
	
	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public User createUser(UserRequestObject requestObject ) {
		User user = new User();
		user.setEmail(requestObject.getEmail());
		user.setEmailVerified(true);
		user.setPassword("demo");
		user.setName(requestObject.getName());
		user.setRoles(requestObject.getRoleName());
		user.setProvider(AuthProvider.local);
		user.setOrgId(requestObject.getOrgId());
		userRepository.save(user);
		return user;
	}
	@Override
	public boolean checkUserByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	@Override
	public User createOrganizationUser(UserRequestObject requestObject ) {
		User user = new User();
		UserPrincipal userPrincipal =(UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setEmail(requestObject.getEmail());
		user.setEmailVerified(true);
		user.setPassword("demo");
		user.setName(requestObject.getName());
		user.setRoles(requestObject.getRoleName());
		user.setProvider(AuthProvider.local);
		user.setOrgId(userPrincipal.getOrrgId());
		userRepository.save(user);
		return user;
	}
	@Override
	public User deleteUser(long userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User user2 = user.get();
			user2.setStatus(false);
			userRepository.save(user2);
			return user2;
		}
		return null;
	}
	@Override
	public User deleteOrganizationalUser(long userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User user2 = user.get();
			UserPrincipal userPrincipal =(UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(user2.getOrgId()==0 || userPrincipal.getOrrgId()==0 ) {
				return null;
			}
			if(user2.getOrgId() != userPrincipal.getOrrgId()) {
				return null;
			}
			user2.setStatus(false);
			userRepository.save(user2);
			return user2;
		}
		return null;
	}
}
