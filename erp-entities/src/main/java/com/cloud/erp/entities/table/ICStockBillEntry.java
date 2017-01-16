/**
 * @Title:  ICStockBillEntry.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年7月2日 下午3:35:57
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
 * @ClassName ICStockBillEntry
 * @Description 销售出库单分录表
 * @author bollen bollen@live.cn
 * @date 2015年7月2日 下午3:35:57
 *
 */
@Entity
@Table(name = "ICSTOCK_BILL_ENTRY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ICStockBillEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer entryId;
	private ICStockBill master;
	private Integer itemId;
	private String number;
	private String itemName;
	private String itemModel;
	private String itemUnit;
	private Integer itemAttr;
	private String attrContent;
	private Integer quantity;
	private Integer actualQuantity;
	private Double price;
	private Double amount;
	private String sourceBillNo;
	private Integer stockId;
	private String stockName;
	private Integer stockPlaceId;
	private String stockPlaceName;
	private String remark;
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
	public ICStockBill getMaster() {
		return master;
	}

	public void setMaster(ICStockBill master) {
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
	
	@Column(name = "ACTUAL_QUANTITY")
	public Integer getActualQuantity() {
		return actualQuantity;
	}

	public void setActualQuantity(Integer actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	@Column(name = "PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "AMOUNT")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockPlaceName() {
		return stockPlaceName;
	}

	public void setStockPlaceName(String stockPlaceName) {
		this.stockPlaceName = stockPlaceName;
	}

	@Column(name = "SOURCE_BILL_NO")
	public String getSourceBillNo() {
		return sourceBillNo;
	}

	public void setSourceBillNo(String sourceBillNo) {
		this.sourceBillNo = sourceBillNo;
	}

	@Column(name = "STOCK_ID")
	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	@Column(name = "STOCK_PLACE_ID")
	public Integer getStockPlaceId() {
		return stockPlaceId;
	}

	public void setStockPlaceId(Integer stockPlaceId) {
		this.stockPlaceId = stockPlaceId;
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

	@Column(name = "CREATER")
	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

}
