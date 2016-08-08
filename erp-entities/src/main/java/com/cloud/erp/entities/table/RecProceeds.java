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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import com.cloud.erp.entities.serializer.DateSerializer;
import com.cloud.erp.entities.serializer.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "REC_PROCEEDS")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonIgnoreProperties(value = {"entries"})
public class RecProceeds implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer interId;
	private String procInstId;
	private String billNo;
	private Date date;
	private String subject;
	private Integer customerId;
	private String customerName;
	private String cashSubject;
	private Integer settleId;
	private String settleName;
	private Date settleDate;
	private String settleNum;
	private String custBank;
	private String custBankNum;
	private String explanation;
	private Integer departmentId;
	private String departmentName;
	private Integer employeeId;
	private String employeeName;
	private String bank;
	private String bankNum;
	private Integer currencyId;
	private String currencyName;
	private Double exchangeRate;
	private Double amount;
	private String recType;
	private String sourceType;
	private String sourceBillNo;
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
	private List<RecProceedsEntry> entries = new ArrayList<RecProceedsEntry>(0);

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

	@JsonSerialize(using = DateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "SUBJECT", length = 20)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "CUSTOMER_ID")
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public String getCashSubject() {
		return cashSubject;
	}

	public void setCashSubject(String cashSubject) {
		this.cashSubject = cashSubject;
	}

	@Column(name = "SETTLE_ID")
	public Integer getSettleId() {
		return settleId;
	}

	public void setSettleId(Integer settleId) {
		this.settleId = settleId;
	}

	@JsonSerialize(using = DateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SETTLE_DATE")
	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	@Column(name = "SETTLE_NUM", length = 20)
	public String getSettleNum() {
		return settleNum;
	}

	public void setSettleNum(String settleNum) {
		this.settleNum = settleNum;
	}

	@Column(name = "CUST_BANK")
	public String getCustBank() {
		return custBank;
	}

	public void setCustBank(String custBank) {
		this.custBank = custBank;
	}

	@Column(name = "CUST_BANK_NUM", length = 50)
	public String getCustBankNum() {
		return custBankNum;
	}

	public void setCustBankNum(String custBankNum) {
		this.custBankNum = custBankNum;
	}

	@Column(name = "EXPLANATION", length = 200)
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Column(name = "BANK")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "BANK_NUM", length = 50)
	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	@Column(name = "CURRENCY_ID")
	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	@Column(name = "EXCHANGE_RATE")
	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name = "AMOUNT")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "REC_TYPE", length = 20)
	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	@Column(name = "SOURCE_TYPE", length = 20)
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name= "SOURCE_BILL_NO", length = 20)
	public String getSourceBillNo() {
		return sourceBillNo;
	}

	public void setSourceBillNo(String sourceBillNo) {
		this.sourceBillNo = sourceBillNo;
	}

	@Column(name = "REMARK", length = 2000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Column(name = "CREATER")
	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}
	
	@Column(name = "DEPARTMENT_ID")
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "EMPLOYEE_ID")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "CHECKER")
	public Integer getChecker() {
		return checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "master")
	public List<RecProceedsEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<RecProceedsEntry> entries) {
		this.entries = entries;
	}

	@Formula("(SELECT t.NAME FROM CUSTOMERS t WHERE t.CUSTOMER_ID=CUSTOMER_ID)")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=SETTLE_ID)")
	public String getSettleName() {
		return settleName;
	}

	public void setSettleName(String settleName) {
		this.settleName = settleName;
	}

	@Formula("(SELECT t.NAME FROM DEPARTMENTS t WHERE t.DEPARTMENT_ID=DEPARTMENT_ID)")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Formula("(SELECT t.NAME FROM EMPLOYEES t WHERE t.EMPLOYEE_ID=EMPLOYEE_ID)")
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Formula("(SELECT t.NAME FROM CURRENCY t WHERE t.CURRENCY_ID=CURRENCY_ID)")
	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	@Formula("(SELECT t.NAME FROM USERS t WHERE t.USER_ID=CHECKER)")
	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	@Formula("(SELECT t.NAME FROM USERS t WHERE t.USER_ID=CREATER)")
	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	
}
