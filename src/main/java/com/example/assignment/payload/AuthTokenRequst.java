package com.example.assignment.payload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuthTokenRequst {
	private String token;
	private String orgName;
	private LocalDateTime expTime;
	private long orgId;
	private long tokenId;
	private boolean status;
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
	public void setExpTime(String expTime) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.expTime = LocalDateTime.parse(expTime,formatter);
	}

	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public long getTokenId() {
		return tokenId;
	}
	public void setTokenId(long tokenId) {
		this.tokenId = tokenId;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AuthTokenRequst [token=" + token + ", orgName=" + orgName + ", expTime=" + expTime + ", orgId=" + orgId
				+ "]";
	}
	
	
}
