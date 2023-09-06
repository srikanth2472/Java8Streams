package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private long salaray;

	private String dept;
	
	private String gender;
	public Employee() {
		
	}
	
	public Employee(int id, String name, long salaray, String dept ,String gender ) {
		super();
		this.id = id;
		this.name = name;
		this.salaray = salaray;
		this.dept = dept;
		this.gender=gender;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salaray=" + salaray + ", dept=" + dept + " gender ="+gender+"]";
	}
	public int getId() {
		return id;
	}
	public String getGender() {
		return gender.substring(0, 1).toUpperCase() + gender.substring(1); 
	}

	public void setGender(String gender) {
		this.gender = gender.substring(0, 1).toUpperCase() + gender.substring(1); 
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
	public long getSalaray() {
		return salaray;
	}
	public void setSalaray(long salaray) {
		this.salaray = salaray;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
}
