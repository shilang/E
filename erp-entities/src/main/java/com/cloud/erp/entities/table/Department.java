/**
 * @Title:  Department.java
 * @Package:  com.cloud.erp.entities
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月2日  上午11:18:01
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities.table;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

/**
 * @ClassName Department
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年2月2日 上午11:18:01
 *
 */

@Entity
@Table(name = "DEPARTMENTS")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer departmentId;
	private String number;
	private String fullName;
	private String name;
	private String ename;
	private String tel;
	private String fax;
	private Integer pid;
	private Integer assistantManager;
	private Integer empQty;
	private Integer manager;
	private Employee deptManager;
	private String managerName;
	private String iconCls;
	private String description;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifier;
	private String state = "closed";
	
	public Department() {
	}
	
	public Department(Integer departmentId){
		this.departmentId = departmentId;
	}

	@Id
	@GeneratedValue
	@Column(name = "DEPARTMENT_ID", unique = true, nullable = false)
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "NUMBER", length = 25)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "PID")
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "FULL_NAME")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "ENAME", length = 100)
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "MANAGER")
	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}
	
	@Transient
	public Employee getDeptManager() {
		return deptManager;
	}

	public void setDeptManager(Employee deptManager) {
		this.deptManager = deptManager;
	}

	@Formula("(SELECT t.NAME FROM EMPLOYEES t WHERE t.EMPLOYEE_ID=MANAGER)")
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Column(name = "ICONCLS", length = 100)
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "ASSISTANT_MANAGER")
	public Integer getAssistantManager() {
		return assistantManager;
	}

	public void setAssistantManager(Integer assistantManager) {
		this.assistantManager = assistantManager;
	}

	@Column(name = "EMP_QTY")
	public Integer getEmpQty() {
		return empQty;
	}

	public void setEmpQty(Integer empQty) {
		this.empQty = empQty;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 10)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TEL", length = 50)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "FAX", length = 50)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "DESCRIPTION", length = 2000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CREATER")
	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	@Column(name = "STATE", length = 20)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
