/**
 * @Title:  ICItemCore.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月22日 下午5:42:58
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
 * @ClassName ICItemCore
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月22日 下午5:42:58
 *
 */
@Entity
@Table(name = "ICITEM_CORE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ICItemCore implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer itemId;
	private String name;
	private String fullName;
	private String model;
	private String helpCode;
	private String shortNumber;
	private String number;
	private Integer parentId;
	private Integer topId;
	private String topName;
	private Integer unit;
	private String unitName;
	private Integer source;
	private Integer lowLimit;
	private Integer highLimit;
	private Integer useState;
	private String useStateName;
	private Integer materialAttr;
	private String materialAttrName;
	private Integer defaultLoc;
	private String remark;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifier;

	@Id
	@GeneratedValue
	@Column(name = "ITEM_ID", unique = true, nullable = false)
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "FULL_NAME", length = 100)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "MODEL", length = 20)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getShortNumber() {
		return shortNumber;
	}

	public void setShortNumber(String shortNumber) {
		this.shortNumber = shortNumber;
	}

	@Column(name = "NUMBER", length = 20)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "PARENT_ID")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "TOP_ID")
	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
		this.topId = topId;
	}
	
	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=TOP_ID)")
	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	@Column(name = "MEASURE_UNIT_ID")
	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	
	@Formula("(SELECT t.NAME FROM MEASURE_UNIT t WHERE t.MEASURE_UNIT_ID = MEASURE_UNIT_ID)")
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "USE_STATE")
	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}
	
	@Formula("(SELECT t.NAME FROM AUXILIARY_RES_MESSAGES t WHERE t.MESSAGE_ID=USE_STATE)")
	public String getUseStateName() {
		return useStateName;
	}

	public void setUseStateName(String useStateName) {
		this.useStateName = useStateName;
	}
	
	@Column(name = "MATERIAL_ATTR")
	public Integer getMaterialAttr() {
		return materialAttr;
	}

	public void setMaterialAttr(Integer materialAttr) {
		this.materialAttr = materialAttr;
	}
	
	@Formula("(SELECT t.NAME FROM ICITEM_ATTR t WHERE t.ATTR_ID=MATERIAL_ATTR)")
	public String getMaterialAttrName() {
		return materialAttrName;
	}

	public void setMaterialAttrName(String materialAttrName) {
		this.materialAttrName = materialAttrName;
	}

	@Column(name = "SOURCE")
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@Column(name = "LOW_LIMIT")
	public Integer getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(Integer lowLimit) {
		this.lowLimit = lowLimit;
	}

	@Column(name = "HIGH_LIMIT")
	public Integer getHighLimit() {
		return highLimit;
	}

	public void setHighLimit(Integer highLimit) {
		this.highLimit = highLimit;
	}

	@Column(name = "DEFAULT_LOC")
	public Integer getDefaultLoc() {
		return defaultLoc;
	}

	public void setDefaultLoc(Integer defaultLoc) {
		this.defaultLoc = defaultLoc;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "REMARK", length = 2000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

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

	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

}
