package com.example.springsocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.entity.UserAuthToken;

@Repository
public interface UserAuthTokenRepository extends JpaRepository<UserAuthToken, Long> {
	List<UserAuthToken> getByTokenid(int tokenId);

}
