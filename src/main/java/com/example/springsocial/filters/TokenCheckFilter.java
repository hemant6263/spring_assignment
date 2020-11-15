package com.example.springsocial.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.oauth2.sdk.util.StringUtils;


public class TokenCheckFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("token");
		if (StringUtils.isBlank(header)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Not Valid");
		}
	}

}
