package com.example.springsocial.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springsocial.entity.AuthToken;
import com.example.springsocial.repository.AuthTokenRerpository;
import com.nimbusds.oauth2.sdk.util.StringUtils;


public class TokenCheckFilter extends OncePerRequestFilter {

	@Autowired
	AuthTokenRerpository authTokenRerpository;
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("token");
		if (StringUtils.isBlank(header)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Not present");
		}
		AuthToken token = authTokenRerpository.findByToken(header);
		if(token == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Not Valid");
		}
		if(token.getExpTime().isBefore(LocalDateTime.now())) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
		}
	}

}
