/**
 * @Title:  SalesOrder.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月19日 下午3:03:40
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
 * @ClassName SalesOrder
 * @Description 销售订单
 * @author bollen bollen@live.cn
 * @date 2015年6月19日 下午3:03:40
 *
 */
@Entity
@Table(name = "SALES_ORDERS")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonIgnoreProperties(value = { "entries" })
public class SalesOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer interId;
	private String procInstId;
	private String billNo;
	private Integer currencyId;
	private String currencyName;
	private Integer customerId;
	private String customerName;
	private Date date;
	private Integer fetchWay;
	private String fetchWayName;
	private Date fetchDate;
	private Integer fetchAddr;
	private String fetchAddrName;
	private Date transitAheadTime;
	private Integer salesWay;
	private String salesWayName;
	private Integer salesScope;
	private String salesScopeName;
	private Integer departmentId;
	private String departmentName;
	private Integer employeeId;
	private String employeeName;
	private Integer checker;
	private String checkerName;
	private Date checkDate;
	private Integer managerId;
	private String managerName;
	private Float exchangeRate;
	private Integer settlementId;
	private String settlementName;
	private Date settlementDate;
	private String explanation;
	private String sourceType;
	private String sourceBillNo;
	private String review;
	private Integer result;
	private Integer cancellation;
	private Integer children;
	private String status;
	private Integer creater;
	private String createrName;
	private Date created;
	private Integer modifier;
	private Date lastmod;
	private List<SalesOrderEntry> entries = new ArrayList<SalesOrderEntry>(0);

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

	@Column(name = "BILL_NO", length = 20)
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
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

	@Column(name = "FETCH_WAY")
	public Integer getFetchWay() {
		return fetchWay;
	}

	public void setFetchWay(Integer fetchWay) {
		this.fetchWay = fetchWay;
	}
	
	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=FETCH_WAY)")
	public String getFetchWayName() {
		return fetchWayName;
	}

	public void setFetchWayName(String fetchWayName) {
		this.fetchWayName = fetchWayName;
	}

	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "FETCH_DATE")
	public Date getFetchDate() {
		return fetchDate;
	}

	public void setFetchDate(Date fetchDate) {
		this.fetchDate = fetchDate;
	}

	@Column(name = "FETCH_ADDR")
	public Integer getFetchAddr() {
		return fetchAddr;
	}

	public void setFetchAddr(Integer fetchAddr) {
		this.fetchAddr = fetchAddr;
	}
	
	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=FETCH_ADDR)")
	public String getFetchAddrName() {
		return fetchAddrName;
	}

	public void setFetchAddrName(String fetchAddrName) {
		this.fetchAddrName = fetchAddrName;
	}

	@JsonSerialize(using = DateSerializer.class)
	@JSON(format="yyyy-MM-dd")
	@Column(name = "TRANSIT_AHEAD_TIME")
	public Date getTransitAheadTime() {
		return transitAheadTime;
	}

	public void setTransitAheadTime(Date transitAheadTime) {
		this.transitAheadTime = transitAheadTime;
	}

	@Column(name = "SALES_WAY")
	public Integer getSalesWay() {
		return salesWay;
	}

	public void setSalesWay(Integer salesWay) {
		this.salesWay = salesWay;
	}
	
	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=SALES_WAY)")
	public String getSalesWayName() {
		return salesWayName;
	}

	public void setSalesWayName(String salesWayName) {
		this.salesWayName = salesWayName;
	}

	@Column(name = "SALES_SCOPE")
	public Integer getSalesScope() {
		return salesScope;
	}

	public void setSalesScope(Integer salesScope) {
		this.salesScope = salesScope;
	}

	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=SALES_SCOPE)")
	public String getSalesScopeName() {
		return salesScopeName;
	}

	public void setSalesScopeName(String salesScopeName) {
		this.salesScopeName = salesScopeName;
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

	@Column(name = "CHECKER")
	public Integer getChecker() {
		return checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	@Formula("(SELECT t.NAME FROM USERS t WHERE t.USER_ID=CHECKER)")
	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
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

	@Column(name = "EXCHANGE_RATE")
	public Float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name = "SETTLEMENT_ID")
	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=SETTLEMENT_ID)")
	public String getSettlementName() {
		return settlementName;
	}

	public void setSettlementName(String settlementName) {
		this.settlementName = settlementName;
	}

	@JsonSerialize(using = DateSerializer.class)
	@JSON(format="yyyy-MM-dd")
	@Column(name = "SETTLEMENT_DATE")
	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	@Column(name ="SOURCE_TYPE", length = 30)
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "SOURCE_BILL_NO", length = 20)
	public String getSourceBillNo() {
		return sourceBillNo;
	}

	public void setSourceBillNo(String sourceBillNo) {
		this.sourceBillNo = sourceBillNo;
	}

	@Column(name = "EXPLANATION", length = 200)
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "REVIEW", length = 200)
	public String getReview() {
		return review;
	}
	
	public void setReview(String review) {
		this.review = review;
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
	public List<SalesOrderEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<SalesOrderEntry> entries) {
		this.entries = entries;
	}
	
}
