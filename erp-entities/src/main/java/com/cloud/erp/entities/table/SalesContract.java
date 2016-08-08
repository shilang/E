/**
 * @Title:  SalesContract.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月18日 下午3:06:27
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
 * @ClassName SalesContract
 * @Description 销售合同单
 * @author bollen bollen@live.cn
 * @date 2015年5月18日 下午3:06:27
 *
 */
@Entity
@Table(name = "SALES_CONTRACTS")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonIgnoreProperties(value = {"entries", "schemes"})
public class SalesContract implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer interId;
	private String procInstId;
	private Integer contractTypeId;
	private Integer detail;
	private String billNo;
	private String contractName;
	private Date date;
	private Integer customer;
	private Integer departmentId;
	private String departmentName;
	private Integer employeeId;
	private String employeeName;
	private Integer currencyId;
	private String currencyName;
	private Float exchangeRate;
	private Double totalAmount;
	private String explanation;
	private String classType;
	private Integer itemClassId;
	private String itemClassName;
	private Integer systemType;
	private Integer checker;
	private String checkerName;
	private Date checkDate;
	private String remark;
	private Integer result;
	private Integer cancellation;
	private Integer children;
	private String status;
	private Integer creater;
	private String createrName;
	private Date created;
	private Integer modifier;
	private Date lastmod;
	private List<SalesContractEntry> entries = new ArrayList<SalesContractEntry>(0);
	private List<SalesContractScheme> schemes = new ArrayList<SalesContractScheme>(0); 
	
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

	@Column(name = "CONTRACT_TYPE_ID")
	public Integer getContractTypeId() {
		return contractTypeId;
	}

	public void setContractTypeId(Integer contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	@Column(name = "DETAIL")
	public Integer getDetail() {
		return detail;
	}

	public void setDetail(Integer detail) {
		this.detail = detail;
	}
	
	@Column(name = "BILL_NO", length = 20)
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	@Column(name = "CONTRACT_NAME", length = 50)
	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@JsonSerialize(using = DateSerializer.class)
	@JSON(format="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE",length = 8)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "CUSTOMER")
	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	@Column(name = "DEPARTMENT_ID")
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	@Formula("(SELECT t.NAME FROM DEPARTMENTS t WHERE t.DEPARTMENT_ID=DEPARTMENT_ID)")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	public Float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name = "TOTAL_AMOUNT")
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "EXPLANATION", length = 100)
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Column(name = "ITEM_CLASS_ID")
	public Integer getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(Integer itemClassId) {
		this.itemClassId = itemClassId;
	}

	@Formula("(SELECT t.NAME FROM CUSTOMERS t WHERE t.CUSTOMER_ID=ITEM_CLASS_ID)")
	public String getItemClassName() {
		return itemClassName;
	}

	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}

	@Column(	name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;	
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

	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	@Column(name = "CLASS_TYPE_ID")
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Column(name = "CHECKER")
	public Integer getChecker() {
		return checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}
	
	@Formula("(SELECT t.NAME FROM USERS t WHERE t.USER_ID=EMPLOYEE_ID)")
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

	@Column(name = "REMARK", length = 2000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
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

	@JSON(serialize = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "master")
	public List<SalesContractEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<SalesContractEntry> entries) {
		this.entries = entries;
	}

	@JSON(serialize = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "master")
	public List<SalesContractScheme> getSchemes() {
		return schemes;
	}

	public void setSchemes(List<SalesContractScheme> schemes) {
		this.schemes = schemes;
	}
	
}
