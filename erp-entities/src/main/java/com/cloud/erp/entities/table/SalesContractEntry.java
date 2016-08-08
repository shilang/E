/**
 * @Title:  SalesContractEntry.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月19日 上午11:14:52
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

/**
 * @ClassName SalesContractEntry
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月19日 上午11:14:52
 *
 */
@Entity
@Table(name = "SALES_CONTRACT_ENTRY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SalesContractEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer entryId;
	private SalesContract master;
	private Integer itemId;
	private String number;
	private String itemName;
	private String itemModel;
	private String itemUnit;
	private Integer itemAttr;
	private String attrContent;
	private Integer quantity;
	private Double price;
	private Float taxPrice;
	private Double amount;
	private Float taxRate;
	private Double tax;
	private Double amountIncludeTax;
	private String remark;
	private Integer index;
	private String status;
	private Integer creater;
	private Date created;
	private Integer modifier;
	private Date lastmod;

	@Id
	@GeneratedValue
	@Column(name = "ENTRY_ID", unique = true, nullable = false)
	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	@JSON(serialize = false)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "INTER_ID")
	public SalesContract getMaster() {
		return master;
	}

	public void setMaster(SalesContract master) {
		this.master = master;
	}

	@Column(name = "ITEM_ID")
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Formula("(SELECT t.NUMBER FROM ICITEM_CORE t WHERE t.ITEM_ID=ITEM_ID)")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Formula("(SELECT t.NAME FROM ICITEM_CORE t WHERE t.ITEM_ID=ITEM_ID)")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Formula("(SELECT t.MODEL FROM ICITEM_CORE t WHERE t.ITEM_ID=ITEM_ID)")
	public String getItemModel() {
		return itemModel;
	}

	public void setItemModel(String itemModel) {
		this.itemModel = itemModel;
	}

	@Formula("(SELECT b.NAME FROM ICITEM_CORE a, MEASURE_UNIT b WHERE a.MEASURE_UNIT_ID = b.MEASURE_UNIT_ID and a.ITEM_ID = ITEM_ID)")
	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	
	@Formula("(SELECT t.MATERIAL_ATTR FROM ICITEM_CORE t WHERE t.ITEM_ID=ITEM_ID)")
	public Integer getItemAttr() {
		return itemAttr;
	}

	public void setItemAttr(Integer itemAttr) {
		this.itemAttr = itemAttr;
	}
	
	@Column(name = "ATTR_CONTENT", length = 200)
	public String getAttrContent() {
		return attrContent;
	}

	public void setAttrContent(String attrContent) {
		this.attrContent = attrContent;
	}

	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "TAX_PRICE")
	public Float getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Float taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Column(name = "AMOUNT")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "TAX_RATE")
	public Float getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name = "TAX")
	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	@Column(name = "AMOUNT_INCLUDE_TAX")
	public Double getAmountIncludeTax() {
		return amountIncludeTax;
	}

	public void setAmountIncludeTax(Double amountIncludeTax) {
		this.amountIncludeTax = amountIncludeTax;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "INDEXS")
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
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

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

}
