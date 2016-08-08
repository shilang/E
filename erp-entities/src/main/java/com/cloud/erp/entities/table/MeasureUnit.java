/**
 * @Title:  MeasureUnit.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 上午9:55:48
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
 * @ClassName MeasureUnit
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月20日 上午9:55:48
 *
 */
@Entity
@Table(name = "MEASURE_UNIT")
@DynamicUpdate(true)
@DynamicInsert(true)
public class MeasureUnit implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer measureUnitId;
	private Integer UnitGroupId;
	private String groupName;
	private String number;
	private String auxClass;
	private String name;
	private String nameEn;
	private Integer conversation;
	private Double coefficient;
	private Integer precision;
	private Integer itemId;
	private Integer parentId;
	private Integer standard;
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
	public MeasureUnit() {
	}

	/**
	 * @param measureUnitId
	 */
	public MeasureUnit(Integer measureUnitId) {
		this.measureUnitId = measureUnitId;
	}

	@Id
	@GeneratedValue
	@Column(name = "MEASURE_UNIT_ID", unique = true, nullable = false)
	public Integer getMeasureUnitId() {
		return measureUnitId;
	}

	public void setMeasureUnitId(Integer measureUnitId) {
		this.measureUnitId = measureUnitId;
	}

	@Column(name = "UNIT_GROUP_ID")
	public Integer getUnitGroupId() {
		return UnitGroupId;
	}

	public void setUnitGroupId(Integer unitGroupId) {
		UnitGroupId = unitGroupId;
	}

	@Column(name = "GROUP_NAME", length = 30)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "NUMBER", length = 20)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "AUX_CLASS", length = 20)
	public String getAuxClass() {
		return auxClass;
	}

	public void setAuxClass(String auxClass) {
		this.auxClass = auxClass;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_EN", length = 100)
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@Column(name = "CONVERSATION")
	public Integer getConversation() {
		return conversation;
	}

	public void setConversation(Integer conversation) {
		this.conversation = conversation;
	}

	@Column(name = "COEFFICIENT")
	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	@Column(name = "PRECISIONS")
	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	@Column(name = "ITEM_ID")
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "PARENT_ID")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "STANDARD")
	public Integer getStandard() {
		return standard;
	}

	public void setStandard(Integer standard) {
		this.standard = standard;
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
