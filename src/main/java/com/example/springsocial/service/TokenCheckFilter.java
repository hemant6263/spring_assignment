package com.example.springsocial.service;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springsocial.entity.AuthToken;
import com.example.springsocial.entity.UserAuthToken;
import com.example.springsocial.repository.AuthTokenRerpository;
import com.example.springsocial.repository.UserAuthTokenRepository;
import com.example.springsocial.security.UserPrincipal;
import com.nimbusds.oauth2.sdk.util.StringUtils;

@Component
public class TokenCheckFilter extends OncePerRequestFilter {

	@Autowired
	private AuthTokenRerpository authTokenRerpository;
	@Autowired
	private UserAuthTokenRepository userAuthTokenRerpository;
	private Logger logger = Logger.getLogger(TokenCheckFilter.class);

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ServletContext servletContext = request.getServletContext();
//		WebApplicationContext webApplicationContext = WebApplicationContextUtils
//				.getWebApplicationContext(servletContext);
//		this.authTokenRerpository = webApplicationContext.getBean(AuthTokenRerpository.class);
//		this.userAuthTokenRerpository = webApplicationContext.getBean(UserAuthTokenRepository.class);
		String header = request.getHeader("token");
		if (StringUtils.isBlank(header)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token Not present");
		}
		AuthToken token = authTokenRerpository.findByToken(header);
		if (token == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token Not Valid");
		}
		if (token.getExpTime().isBefore(LocalDateTime.now())) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token Expired");
		}
		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserAuthToken userToken = userAuthTokenRerpository.getByTokenidAndUserIdAndStatus(token.getId(),
				principal.getId(), 1);
		if (userToken == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Contact Service");
		}

	}

}
