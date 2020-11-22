package com.example.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	Organization getByEmail(String email);

}
