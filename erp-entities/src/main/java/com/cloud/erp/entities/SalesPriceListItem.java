/**
 * @Title:  SalesPriceListItem.java
 * @Package:  com.cloud.erp.entities
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月20日 上午10:20:51
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @ClassName SalesPriceListItem
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年4月20日 上午10:20:51
 *
 */
@Entity
@Table(name = "SALES_PRICE_LIST_ITEM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SalesPriceListItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer salesPriceListItemId;
	private Integer item;
	private String productCode;
	private String productName;
	private String productModel;
	private String secondaryAttr;
	private String unit;
	private Integer quantity;
	private double price;
	private double discountRate;
	private String remark;
	private SalesPriceList salesPriceList;

	/**
	 * defualt constructor
	 */
	public SalesPriceListItem() {
		super();
	}

	/**
	 * full constructor
	 */
	public SalesPriceListItem(Integer item, String productCode,
			String productName, String productModel, String secondaryAttr,
			String unit, Integer quantity, double price, double discountRate,
			String remark) {
		super();
		this.item = item;
		this.productCode = productCode;
		this.productName = productName;
		this.productModel = productModel;
		this.secondaryAttr = secondaryAttr;
		this.unit = unit;
		this.quantity = quantity;
		this.price = price;
		this.discountRate = discountRate;
		this.remark = remark;
	}

	
	@Id
	@GeneratedValue
	@Column(name = "SALES_PRICE_LIST_ITEM_ID")
	public Integer getSalesPriceListItemId() {
		return salesPriceListItemId;
	}

	public void setSalesPriceListItemId(Integer salesPriceListItemId) {
		this.salesPriceListItemId = salesPriceListItemId;
	}

	@Column(name = "ITEM")
	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	@Column(name = "PRODUCT_CODE", length = 20)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Column(name = "PRODUCT_NAME", length = 100)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "PRODUCT_MODEL", length = 20)
	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	@Column(name = "SECONDARY_ATTR", length = 100)
	public String getSecondaryAttr() {
		return secondaryAttr;
	}

	public void setSecondaryAttr(String secondaryAttr) {
		this.secondaryAttr = secondaryAttr;
	}

	@Column(name = "UNIT", length = 20)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "DISCOUNT_RATE")
	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	@Column(name = "REMARK", length = 2000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALES_PRICE_LIST_ID")
	public SalesPriceList getSalesPriceList() {
		return salesPriceList;
	}

	public void setSalesPriceList(SalesPriceList salesPriceList) {
		this.salesPriceList = salesPriceList;
	}

	

}
