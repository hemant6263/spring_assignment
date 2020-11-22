package com.example.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "org_auth_token")
public class OrgAuthToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private long tokenid;
	@Column(nullable = false)
	private Long orgId;
	@Column
	private int status;
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getToken_id() {
		return tokenid;
	}
	public void setToken_id(long token_id) {
		this.tokenid = token_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getTokenid() {
		return tokenid;
	}
	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
}
