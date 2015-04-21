/**
 * @Title:  SalesPriceList.java
 * @Package:  com.cloud.erp.entities
 * @Description:  TODO
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
package com.cloud.erp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @ClassName SalesPriceList
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年4月20日 上午9:55:05
 *
 */
@Entity
@Table(name = "SALES_PRICE_LIST")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SalesPriceList implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer salesPriceListId;
	private String number;
	private String currency;
	private double exchangeRate;
	private String buyUnit;
	private Date date;
	private Integer dept;
	private Integer manager;
	private Integer sales;
	private Integer audit;
	private Date auditDate;
	private String status;
	private Date Created;
	private Date Lastmod;
	private Integer creater;
	private Integer modifier;
	private Set<SalesPriceListItem> items = new HashSet<SalesPriceListItem>();

	/**
	 * default constructor
	 */
	public SalesPriceList() {
		super();
	}

	/**
	 * full constructor
	 */
	public SalesPriceList(String number, String currency, double exchangeRate,
			String buyUnit, Date date, Integer dept, Integer manager,
			Integer sales, Integer audit, Date auditDate, String status,
			Date created, Date lastmod, Integer creater, Integer modifier,
			Set<SalesPriceListItem> items) {
		super();
		this.number = number;
		this.currency = currency;
		this.exchangeRate = exchangeRate;
		this.buyUnit = buyUnit;
		this.date = date;
		this.dept = dept;
		this.manager = manager;
		this.sales = sales;
		this.audit = audit;
		this.auditDate = auditDate;
		this.status = status;
		Created = created;
		Lastmod = lastmod;
		this.creater = creater;
		this.modifier = modifier;
		this.items = items;
	}

	@Id
	@GeneratedValue
	@Column(name = "SALES_PRICE_LIST_ID")
	public Integer getSalesPriceListId() {
		return salesPriceListId;
	}

	public void setSalesPriceListId(Integer salesPriceListId) {
		this.salesPriceListId = salesPriceListId;
	}

	@Column(name = "NUMBER", length = 50)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "CURRENCY", length = 50)
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "EXCHANGE_RATE")
	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name = "BUY_UNIT", length = 200)
	public String getBuyUnit() {
		return buyUnit;
	}

	public void setBuyUnit(String buyUnit) {
		this.buyUnit = buyUnit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE", length = 10)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "DEPT")
	public Integer getDept() {
		return dept;
	}

	public void setDept(Integer dept) {
		this.dept = dept;
	}

	@Column(name = "MANAGER")
	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	@Column(name = "SALES")
	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	@Column(name = "AUDIT")
	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUDIT_DATE", length = 10)
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
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
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD", length = 10)
	public Date getLastmod() {
		return Lastmod;
	}

	public void setLastmod(Date lastmod) {
		Lastmod = lastmod;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "salesPriceList")
	public Set<SalesPriceListItem> getItems() {
		return items;
	}

	public void setItems(Set<SalesPriceListItem> items) {
		this.items = items;
	}

}
