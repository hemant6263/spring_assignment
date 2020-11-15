package com.example.springsocial.service;

import com.example.springsocial.entity.AuthToken;
import com.example.springsocial.payload.AuthTokenRequst;

public interface TokenService {

	boolean checkIfTokenExist(String token);
	AuthToken checkCreateAUthToken(AuthTokenRequst authTokenRequst);
}
