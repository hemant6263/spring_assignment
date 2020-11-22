package com.example.assignment.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Future;

@Entity
@Table(name = "auth_token", uniqueConstraints = {
        @UniqueConstraint(columnNames = "token")
})
public class AuthToken extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private String orgName;
	@Future
	@Column(nullable = false)
	private LocalDateTime expTime;
	@Column
	private boolean status;
	@Column(nullable = false)
	private long orgId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
	

}
