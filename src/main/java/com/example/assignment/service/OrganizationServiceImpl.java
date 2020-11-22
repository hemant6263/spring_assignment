package com.example.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.entity.Organization;
import com.example.assignment.repository.OrganizationRepository;
import com.example.assignment.repository.UserRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public Organization createOrganization(String orgName, String email) {
		if(checkOrganizationByEmail(email)) {
			return null;
		}
		Organization organization = new Organization();
		organization.setEmail(email);
		organization.setName(orgName);
		organizationRepository.save(organization);
		return organization;
	}
	@Override
	public boolean checkOrganizationByEmail(String email) {
		Organization organization = organizationRepository.getByEmail(email);
		return organization==null ? false :true;
	}
	@Override
	public List<Organization> findAll() {
		List<Organization> findAll = organizationRepository.findAll();
		return findAll;
	}
	@Override
	public Organization deleteOrg(long id) {
		Optional<Organization> organization = organizationRepository.findById(id);
		if(organization.isPresent()) {
			Organization organization2 = organization.get();
			organization2.setStatus(false);
			organizationRepository.save(organization2);
			return organization2;
		}
		return null;
	}

}
