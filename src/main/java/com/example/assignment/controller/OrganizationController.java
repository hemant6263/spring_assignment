package com.example.assignment.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.entity.AuthToken;
import com.example.assignment.entity.Organization;
import com.example.assignment.entity.User;
import com.example.assignment.payload.AuthTokenRequst;
import com.example.assignment.payload.UserRequestObject;
import com.example.assignment.service.OrganizationService;
import com.example.assignment.service.UserService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	UserService userService;
	@Autowired
	OrganizationService orgService;

	private static Logger logger = Logger.getLogger(BusinessController.class);

	@GetMapping("/getUsers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getUsers() {
		logger.debug("[BusinessController]][generateCurrentUser] request");
		List<User> allUser = userService.getAllUser();
		ResponseEntity res = new ResponseEntity(allUser, HttpStatus.OK);
		return res;
	}

	@GetMapping("/getOrganizations")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getOrganizations() {
		logger.debug("[BusinessController]][generateCurrentUser] request");
		List<Organization> findAll = orgService.findAll();
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

	@RequestMapping(path = "/createOrganizations", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> createOrganization(@RequestParam("email") String email,
			@RequestParam("name") String name) {
		logger.debug("[BusinessController]][generateCurrentUser] request");
		Organization createOrganization = orgService.createOrganization(name, email);
		ResponseEntity res = new ResponseEntity(createOrganization,
				createOrganization == null ? HttpStatus.CREATED : HttpStatus.OK);
		return res;
	}

	@RequestMapping(path = "/createUser", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> createUser(@RequestBody UserRequestObject object) {
		logger.debug("[BusinessController]][generateCurrentUser] request");
		User createUser = userService.createUser(object);
		ResponseEntity res = new ResponseEntity(createUser,
				createUser == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return res;
	}

	@PatchMapping(path = "/deleteUser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@RequestParam("userId") long userId) {
		logger.debug("[BusinessController]][generateCurrentUser] request");
		User createUser = userService.deleteUser(userId);
		ResponseEntity res = new ResponseEntity(createUser,
				createUser == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return res;
	}

	@PatchMapping(path = "/deleteOrganization")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteAuthToken(@RequestParam("orgId") long orgId) {
		logger.debug("[BusinessController]][deleteAuthToken] request");
		Organization org = orgService.deleteOrg(orgId);
		ResponseEntity res = new ResponseEntity(org, org == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return res;
	}

}
