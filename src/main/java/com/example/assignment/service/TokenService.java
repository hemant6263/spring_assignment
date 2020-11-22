package com.example.assignment.service;

import com.example.assignment.entity.AuthToken;
import com.example.assignment.payload.AuthTokenRequst;

public interface TokenService {

	boolean checkIfTokenExist(String token);
	AuthToken checkCreateAUthToken(AuthTokenRequst authTokenRequst);
	AuthToken updateAuthToken(AuthTokenRequst authTokenRequst);
	AuthToken deleteAuthToken(long tokenId);
}
