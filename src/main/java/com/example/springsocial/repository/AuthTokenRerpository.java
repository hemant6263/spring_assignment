package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.config.AppProperties.Auth;
import com.example.springsocial.entity.AuthToken;

@Repository
public interface AuthTokenRerpository extends JpaRepository<AuthToken, Long> {
	AuthToken findByToken(String token);

}
