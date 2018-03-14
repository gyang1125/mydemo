package com.ygl.test.model;

import java.util.Date;

public class Employee {

	private Long id;
	private String name;
	private boolean gender;
	private Date birthday;
	private String desc;
	private UserAccount userAccount;
	private Department deparment;

	public Department getDeparment() {
		return deparment;
	}

	public void setDeparment(Department deparment) {
		this.deparment = deparment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
