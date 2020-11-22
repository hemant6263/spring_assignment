package com.example.assignment.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRequestObject {
	@NotEmpty
	private String name;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty
	private String roleName;
	@NotEmpty
	private long orgId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	

}
