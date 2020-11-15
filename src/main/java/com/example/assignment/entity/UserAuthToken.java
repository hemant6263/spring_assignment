package com.example.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_auth_token")
public class UserAuthToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int tokenid;
	@Column(nullable = false)
	private Long userId;
	@Column
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getToken_id() {
		return tokenid;
	}
	public void setToken_id(int token_id) {
		this.tokenid = token_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTokenid() {
		return tokenid;
	}
	public void setTokenid(int tokenid) {
		this.tokenid = tokenid;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
