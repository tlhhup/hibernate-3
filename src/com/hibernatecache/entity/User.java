package com.hibernatecache.entity;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String address;

	//与角色的多对多单向关联
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
