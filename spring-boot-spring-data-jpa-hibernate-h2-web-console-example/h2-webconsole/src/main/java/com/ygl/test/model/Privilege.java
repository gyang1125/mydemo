package com.ygl.test.model;

import java.util.HashSet;
import java.util.Set;

public class Privilege {
	private Long id;
	private String action;
	private Set<UserAccount> userAccounts = new HashSet<UserAccount>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Set<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

}
