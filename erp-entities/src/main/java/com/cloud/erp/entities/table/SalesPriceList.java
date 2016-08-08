/**
 * @Title:  SalesPriceList.java
 * @Package:  com.cloud.erp.entities
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月20日 上午9:55:05
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import com.cloud.erp.entities.serializer.DateSerializer;
import com.cloud.erp.entities.serializer.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @ClassName SalesPriceList
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月20日 上午9:55:05
 *
 */
@Entity
@Table(name = "SALES_PRICE_LIST")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonIgnoreProperties(value = {"entries"})
public class SalesPriceList implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer interId;
	private String procInstId;
	private Integer customerId;
	private String customerName;
	private String billNo;
	private Date date;
	private Integer currencyId;
	private String currencyName;
	private double exchangeRate;
	private Integer employeeId;
	private String employeeName;
	private Integer checker;
	private String checkerName;
	private Date checkDate;
	private Integer departmentId;
	private String deptName;
	private Integer managerId;
	private String managerName;
	private Integer result;
	private Integer cancellation;
	private Integer children;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private String createrName;
	private Integer modifier;
	private List<SalesPriceListEntry> entries = new ArrayList<SalesPriceListEntry>(0);

	@Id
	@GeneratedValue
	@Column(name = "INTER_ID", unique = true, nullable = false)
	public Integer getInterId() {
		return interId;
	}

	public void setInterId(Integer interId) {
		this.interId = interId;
	}
	
	@Column(name = "PROC_INST_ID", length = 64)
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "CUSTOMER_ID")
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Formula("(SELECT t.NAME FROM CUSTOMERS t WHERE t.CUSTOMER_ID=CUSTOMER_ID)")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "BILL_NO", length = 20)
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	@JsonSerialize(using = DateSerializer.class)
	@JSON(format="yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "CURRENCY_ID")
	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	
	@Formula("(SELECT t.NAME FROM CURRENCY t WHERE t.CURRENCY_ID=CURRENCY_ID)")
	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	@Column(name = "EXCHANGE_RATE")
	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name = "EMPLOYEE_ID")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	@Formula("(SELECT t.NAME FROM EMPLOYEES t WHERE t.EMPLOYEE_ID=EMPLOYEE_ID)")
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Column(name = "CHECKER_ID")
	public Integer getChecker() {
		return checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}
	
	@Formula("(SELECT t.NAME FROM USERS t WHERE t.USER_ID=CHECKER_ID)")
	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "DEPARTMENT_ID")
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Formula("(SELECT t.NAME FROM DEPARTMENTS t WHERE t.DEPARTMENT_ID=DEPARTMENT_ID)")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "MANAGER_ID")
	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	@Formula("(SELECT t.NAME FROM EMPLOYEES t WHERE t.EMPLOYEE_ID=MANAGER_ID)")
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	@Column(name = "RESULT", columnDefinition = "INT default 0")
	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Column(name = "CANCELLATION", columnDefinition = "INT default 0")
	public Integer getCancellation() {
		return cancellation;
	}

	public void setCancellation(Integer cancellation) {
		this.cancellation = cancellation;
	}
	
	@Column(name = "CHILDREN", columnDefinition = "INT default 0")
	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "CREATER")
	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Formula("(SELECT t.NAME FROM USERS t WHERE t.USER_ID=CREATER)")
	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	@JSON(serialize = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "master")
	public List<SalesPriceListEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<SalesPriceListEntry> entries) {
		this.entries = entries;
	}
	
}
