package com.example.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment.entity.OrgAuthToken;

@Repository
public interface OrgAuthTokenRepository extends JpaRepository<OrgAuthToken, Long> {
	List<OrgAuthToken> getByTokenid(int tokenId);
	
	OrgAuthToken getByTokenidAndOrgIdAndStatus(long tokenId,long orgId,int status);


}
