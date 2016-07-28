package com.hibernatecache.entity;

import java.io.Serializable;
import java.util.Set;

public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;

	//构建与学生的一对多关系
	private Set<Student> students;

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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
