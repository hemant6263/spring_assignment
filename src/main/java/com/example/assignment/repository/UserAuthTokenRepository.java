package com.example.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment.entity.UserAuthToken;

@Repository
public interface UserAuthTokenRepository extends JpaRepository<UserAuthToken, Long> {
	List<UserAuthToken> getByTokenid(int tokenId);
	
	UserAuthToken getByTokenidAndUserIdAndStatus(int tokenId,long userId,int status);


}
