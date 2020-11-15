package com.example.springsocial.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsocial.entity.AuthToken;
import com.example.springsocial.entity.UserAuthToken;
import com.example.springsocial.payload.AuthTokenRequst;
import com.example.springsocial.repository.AuthTokenRerpository;
import com.example.springsocial.repository.UserAuthTokenRepository;
import com.example.springsocial.util.Md5Util;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private AuthTokenRerpository authTokenRepo;
	@Autowired
	private UserAuthTokenRepository userAuthTokenRepo;

	@Override
	public boolean checkIfTokenExist(String token) {
		AuthToken findByToken = authTokenRepo.findByToken(token);
		if(findByToken == null ) {
			return false;
		}
		return true;
	}

	@Override
	public AuthToken checkCreateAUthToken(AuthTokenRequst authTokenRequst) {
		boolean tokenCheck = checkIfTokenExist(authTokenRequst.getToken());
		if(tokenCheck) {
			return null;
		}else {
			AuthToken token = new AuthToken();
			token.setExpTime(LocalDateTime.now().plusDays(2));
			token.setOrgName(authTokenRequst.getOrgName());
			token.setStatus(1);
			token.setToken(Md5Util.createMd5Hash(token.getOrgName()+token.getExpTime()));
			token= authTokenRepo.save(token);
			
			UserAuthToken userAuthToken = new UserAuthToken();
			userAuthToken.setToken_id(token.getId());
			userAuthToken.setStatus(1);
			List<Long> userIds = authTokenRequst.getUserIds();
			for(Long id :  userIds) {
				userAuthToken.setUserId(id);
				userAuthTokenRepo.save(userAuthToken);
			}
			return token;
		}
		
	}

}
