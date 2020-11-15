package com.example.springsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.entity.AuthToken;
import com.example.springsocial.payload.AuthTokenRequst;
import com.example.springsocial.service.TokenService;

@RestController
@RequestMapping("/business")
public class BusinessController {
	@Autowired
	private TokenService service;

	@RequestMapping("/useToken")
	public ResponseEntity<?> getCurrentUser(@RequestHeader("token") String token,
			@RequestParam("details") String userDetails) {
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping("/generateToken")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> generateCurrentUser(@RequestBody AuthTokenRequst authTokenRequst) {
		AuthToken token = service.checkCreateAUthToken(authTokenRequst);
		ResponseEntity res = new ResponseEntity(token, HttpStatus.OK);
		return res;
	}

}
