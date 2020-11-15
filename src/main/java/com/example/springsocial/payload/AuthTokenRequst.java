package com.example.springsocial.payload;

import java.time.LocalDateTime;
import java.util.List;

public class AuthTokenRequst {
	private String token;
	private String orgName;
	private LocalDateTime expTime;
	private List<Long> userIds;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public LocalDateTime getExpTime() {
		return expTime;
	}
	public void setExpTime(LocalDateTime expTime) {
		this.expTime = expTime;
	}
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	
}
