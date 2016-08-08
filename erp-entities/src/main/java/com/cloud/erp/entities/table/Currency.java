/**
 * @Title:  Currencies.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 上午9:42:43
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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @ClassName Currencies
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月20日 上午9:42:43
 *
 */
@Entity
@Table(name = "CURRENCY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Currency implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer currencyId;
	private String number;
	private String name;
	private String operator;
	private Double exchangeRate;
	private Integer scale;
	private Integer fixRate;
	private Integer control;
	private Integer systemType;
	private String status;
	private Date created;
	private Integer creater;
	private Date lastmod;
	private Integer modifier;

	/**
	 * 
	 */
	public Currency() {
	}

	/**
	 * @param currencyId
	 */
	public Currency(Integer currencyId) {
		this.currencyId = currencyId;
	}

	@Id
	@GeneratedValue
	@Column(name = "CURRENCY_ID", unique = true, nullable = false)
	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	@Column(name = "NUMBER", length = 20)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "EXCHANGE_RATE")
	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name = "SCALE")
	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	@Column(name = "FIX_RATE")
	public Integer getFixRate() {
		return fixRate;
	}

	public void setFixRate(Integer fixRate) {
		this.fixRate = fixRate;
	}

	@Column(name = "CONTROL")
	public Integer getControl() {
		return control;
	}

	public void setControl(Integer control) {
		this.control = control;
	}

	@Column(name = "SYSTEM_TYPE")
	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "CREATER")
	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

}
