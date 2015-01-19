package com.cloud.erp.entities;

public class Employee {

	private Integer eid;
	private String employeeCn;
	private String employeeEn;
	private String employeeNumber;
	private String gender;
	private String duty;
	private String telephone;
	private String email;
	private String identityCard;
	private String remarks;
	private Department department;

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEmployeeCn() {
		return employeeCn;
	}

	public void setEmployeeCn(String employeeCn) {
		this.employeeCn = employeeCn;
	}

	public String getEmployeeEn() {
		return employeeEn;
	}

	public void setEmployeeEn(String employeeEn) {
		this.employeeEn = employeeEn;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
