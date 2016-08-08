package com.cloud.erp.entities.table;



public class UserInfo {

	private Integer id;
	private String name;
	private Integer employeeId;
	private String employeeName;
	private Integer departmentId;
	private String departmentName;
	private Integer managerId;
	private String managerName;
	//private Employee deptManager;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

/*	@JSON(serialize = false)
	public Employee getDeptManager() {
		return deptManager;
	}

	public void setDeptManager(Employee deptManager) {
		this.deptManager = deptManager;	
		this.setManagerId(deptManager.getEmployeeId());
		this.setManagerName(deptManager.getName());
	}*/

}
