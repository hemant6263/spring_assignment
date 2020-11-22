package com.example.assignment.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.entity.AuthToken;
import com.example.assignment.entity.User;
import com.example.assignment.payload.AuthTokenRequst;
import com.example.assignment.payload.UserRequestObject;
import com.example.assignment.service.OrganizationService;
import com.example.assignment.service.TokenService;
import com.example.assignment.service.UserService;

@RestController
@RequestMapping("/business")
public class BusinessController {
	@Autowired
	private TokenService service;
	@Autowired
	UserService userService;
	@Autowired
	OrganizationService orgService;

	private static Logger logger = Logger.getLogger(BusinessController.class);

	@RequestMapping("/useToken")
	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<?> getCurrentUser(@RequestHeader("token") String token,
			@RequestParam("details") String userDetails) {
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/createUser")
	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<?> getCreattUser(@RequestBody UserRequestObject object) {
		User createUser = userService.createUser(object);
		ResponseEntity res = new ResponseEntity(createUser,
				createUser == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return res;
	}

	@PatchMapping(path = "/deleteUser")
	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<?> deleteOrganizationalUser(@RequestParam("userId") long userId) {
		logger.debug("[BusinessController]][generateCurrentUser] request");
		User createUser = userService.deleteUser(userId);
		ResponseEntity res = new ResponseEntity(createUser,
				createUser == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return res;
	}

	@PostMapping("/generateToken")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> generateCurrentUser(@RequestBody AuthTokenRequst authTokenRequst) {
		logger.debug("[BusinessController]][generateCurrentUser] request" + authTokenRequst);
		AuthToken token = service.checkCreateAUthToken(authTokenRequst);
		ResponseEntity res = new ResponseEntity(token, HttpStatus.OK);
		return res;
	}

	@PatchMapping(path = "/deleteAuthToken")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteAuthToken(@RequestParam("tokenId") long tokenId) {
		logger.debug("[BusinessController]][deleteAuthToken] request");
		AuthToken token = service.deleteAuthToken(tokenId);
		ResponseEntity res = new ResponseEntity(token, token == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return res;
	}

	@PutMapping(path = "/updateToken")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateToken(@RequestBody AuthTokenRequst authTokenRequst) {
		logger.debug("[BusinessController]][updateToken] request");
		AuthToken token = service.updateAuthToken(authTokenRequst);
		ResponseEntity res = new ResponseEntity(token, token == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return res;
	}

}
