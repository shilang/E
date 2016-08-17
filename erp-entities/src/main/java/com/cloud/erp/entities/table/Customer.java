/**
 * @Title:  Customer.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月23日 上午10:18:07
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
import org.hibernate.annotations.Formula;

/**
 * @ClassName Customer
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月23日 上午10:18:07
 *
 */
@Entity
@Table(name = "CUSTOMERS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer customerId;
	private String number;
	private String name;
	private String fullName;
	private String prefixCode;
	private Integer region;
	private String regionName;
	private String helpCode;
	private String address;
	private Integer parentId;
	private String phone;
	private String fax;
	private String email;
	private String homePage;
	private Integer typeId;
	private String typeName;
	private Integer saleMode;
	private String salesModeName;
	private String remark;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifier;
	
	public Customer() {
	}

	public Customer(Integer customerId) {
		this.customerId = customerId;
	}

	@Id
	@GeneratedValue
	@Column(name = "CUSTOMER_ID", unique = true, nullable = false)
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "NUMBER", length = 20, unique = true, nullable = false)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "NAME", length = 50, unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "FULL_NAME", length = 100)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Column(name = "PREFIX_CODE", length = 20, unique = true, nullable = false)
	public String getPrefixCode() {
		return prefixCode;
	}

	public void setPrefixCode(String prefixCode) {
		this.prefixCode = prefixCode;
	}

	@Column(name = "REGION")
	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}
	
	@Formula("(SELECT t.NAME FROM AREAS t WHERE t.AREA_ID=REGION)")
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(name = "HELP_CODE", length = 20)
	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	@Column(name = "ADDRESS", length = 255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PARENT_ID")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "PHONE", length = 30)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "FAX", length = 30)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "HOME_PAGE", length = 100)
	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	@Column(name = "TYPE_ID")
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=TYPE_ID)")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "SALE_MODE")
	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}
	
	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=SALE_MODE)")
	public String getSalesModeName() {
		return salesModeName;
	}

	public void setSalesModeName(String salesModeName) {
		this.salesModeName = salesModeName;
	}

	@Column(name = "REMARK", length = 2000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	@Column(name = "LASTMOD", length = 10)
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

	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

}
