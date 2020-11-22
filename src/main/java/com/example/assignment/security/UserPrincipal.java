package com.example.assignment.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import com.example.assignment.entity.User;

/**
 * @author hemant 
 * This Class will be used to create principals and extract user
 *         detials that will be used to authenticate object the info in the
 *         security context
 */
public class UserPrincipal implements OAuth2User, UserDetails {
	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;
	private Long orrgId;
	private boolean status;
	private static Logger logger= Logger.getLogger(UserPrincipal.class);

	public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities,long orrgId,boolean status  ) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.orrgId =orrgId;
		this.status = status;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		String roles = user.getRoles();
		if(!StringUtils.isEmpty(roles) && roles.contains(",")){
			String[] role  = roles.split(",");
			for(String s : role) {
				authorities.add(new SimpleGrantedAuthority(s));	
			}
		}else if(!StringUtils.isEmpty(roles)){
			authorities.add(new SimpleGrantedAuthority(roles));
		}else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), authorities,user.getOrgId(),user.isStatus());
	}

	public static UserPrincipal create(User user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		userPrincipal.setAttributes(attributes);
		logger.debug("[UserPrincipal][UserPrincipal]"+userPrincipal);
		return userPrincipal;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return status;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return String.valueOf(id);
	}

	@Override
	public String toString() {
		return "UserPrincipal [id=" + id + ", email=" + email + ", password=" + password + ", authorities="
				+ authorities + ", attributes=" + attributes + ", logger=" + logger + "]";
	}

	public Long getOrrgId() {
		return orrgId;
	}

	public void setOrrgId(Long orrgId) {
		this.orrgId = orrgId;
	}
	
	
}
