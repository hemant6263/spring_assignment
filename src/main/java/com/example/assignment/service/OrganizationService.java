package com.example.assignment.service;

import java.util.List;

import com.example.assignment.entity.Organization;

public interface OrganizationService {
	public Organization createOrganization(String orgName,String email);
	public boolean checkOrganizationByEmail(String email);
	
	List<Organization> findAll();
	
	public Organization deleteOrg(long id);
}
