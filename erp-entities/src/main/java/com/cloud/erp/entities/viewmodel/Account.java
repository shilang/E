package com.cloud.erp.entities.viewmodel;

import java.util.List;

import com.cloud.erp.entities.table.User;

public class Account {

	private List<User> users;
	private Integer accId;
	private String accName;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

}
