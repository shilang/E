package com.cloud.erp.entities.table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "RELATIONS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Relation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String moduleName;
	private String moduleClass;
	private String tableName;
	private String entries;
	private String remark;

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "MODULE_NAME", length = 30)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "MODULE_CLASS", length = 50)
	public String getModuleClass() {
		return moduleClass;
	}

	public void setModuleClass(String modeleClass) {
		this.moduleClass = modeleClass;
	}

	@Column(name = "TABLE_NAME", length = 20)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "ENTRIES", length = 50)
	public String getEntries() {
		return entries;
	}

	public void setEntries(String entries) {
		this.entries = entries;
	}

	@Column(name = "REMARK", length= 50)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
