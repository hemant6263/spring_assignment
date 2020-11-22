package com.example.assignment.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.entity.AuthToken;
import com.example.assignment.entity.OrgAuthToken;
import com.example.assignment.entity.Organization;
import com.example.assignment.payload.AuthTokenRequst;
import com.example.assignment.repository.AuthTokenRerpository;
import com.example.assignment.repository.OrgAuthTokenRepository;
import com.example.assignment.repository.OrganizationRepository;
import com.example.assignment.util.Md5Util;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private AuthTokenRerpository authTokenRepo;
	@Autowired
	private OrgAuthTokenRepository orgAuthTokenRepo;
	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public boolean checkIfTokenExist(String token) {
		AuthToken findByToken = authTokenRepo.findByToken(token);
		if (findByToken == null) {
			return false;
		}
		return true;
	}

	@Override
	public AuthToken checkCreateAUthToken(AuthTokenRequst authTokenRequst) {
		
			AuthToken token = new AuthToken();
			token.setExpTime(LocalDateTime.now().plusDays(2));
			token.setOrgName(authTokenRequst.getOrgName());
			token.setStatus(true);
			token.setToken(Md5Util.createMd5Hash(token.getOrgName() + token.getExpTime()));
			
			long orgId= authTokenRequst.getOrgId();
			
			Optional<Organization> findById = organizationRepository.findById(orgId);
			if(!findById.isPresent()) {
				return  null;
			}else {
				Organization organization = findById.get();
				boolean tokenCheck = checkIfTokenExist(authTokenRequst.getToken());
				
				if (tokenCheck) {
					token.setToken(Md5Util.createMd5Hash(authTokenRequst.getOrgName()+organization.getEmail() + token.getExpTime()));
				}
				token = authTokenRepo.save(token);

				OrgAuthToken userAuthToken = new OrgAuthToken();
				userAuthToken.setToken_id(token.getId());
				userAuthToken.setStatus(1);
				userAuthToken.setOrgId(orgId);
				orgAuthTokenRepo.save(userAuthToken);
				return token;
			}

	}

	@Override
	public AuthToken updateAuthToken(AuthTokenRequst authTokenRequst) {
		long tokenId = authTokenRequst.getTokenId();
		Optional<AuthToken> optionToken = authTokenRepo.findById(tokenId);
		if(optionToken.isPresent()) {
			AuthToken authToken = optionToken.get();
			authToken.setExpTime(authTokenRequst.getExpTime());
			authToken.setStatus(authTokenRequst.isStatus());
			authToken.setOrgId(authTokenRequst.getOrgId());
			authToken.setOrgName(authTokenRequst.getOrgName());
			return authToken;
		}
		return null;
	}

	@Override
	public AuthToken deleteAuthToken(long tokenId) {
		Optional<AuthToken> optionToken = authTokenRepo.findById(tokenId);
		if(optionToken.isPresent()) {
			AuthToken authToken = optionToken.get();
			authToken.setStatus(false);
			return authToken;
		}
		return null;
	}

}
