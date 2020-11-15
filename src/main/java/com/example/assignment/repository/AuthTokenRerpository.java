package com.example.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment.config.AppProperties.Auth;
import com.example.assignment.entity.AuthToken;

@Repository
public interface AuthTokenRerpository extends JpaRepository<AuthToken, Long> {
	AuthToken findByToken(String token);

}
