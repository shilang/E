/**
 * @Title:  Organization.java
 * @Package:  com.cloud.erp.entities
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月2日  上午11:18:01
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
 * @ClassName Organization
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年2月2日 上午11:18:01
 *
 */

@Entity
@Table(name = "ORGANIZATION")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Organization implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer organizationId;
	private Integer companyId;
	private String myid;
	private Integer pid;
	private String fullName;
	private String ename;
	private Integer manager;
	private String iconCls;
	private Integer assistantManager;
	private Integer empQty;
	private String status;
	private Date created;
	private Date lastmod;
	private String shortName;
	private String tel;
	private String fax;
	private String description;
	private Integer creater;
	private Integer modifier;
	private String state = "closed";

	/**
	 * default constructor
	 */
	public Organization() {
	}

	/**
	 * full constructor
	 */
	public Organization(Integer companyId, String myid, Integer pid,
			String fullName, String ename, Integer manager, String iconCls,
			Integer assistantManager, Integer empQty, String status,
			Date created, Date lastmod, String shortName, String tel,
			String fax, String description, Integer creater, Integer modifier,
			String state) {
		this.companyId = companyId;
		this.myid = myid;
		this.pid = pid;
		this.fullName = fullName;
		this.ename = ename;
		this.manager = manager;
		this.iconCls = iconCls;
		this.assistantManager = assistantManager;
		this.empQty = empQty;
		this.status = status;
		this.created = created;
		this.lastmod = lastmod;
		this.shortName = shortName;
		this.tel = tel;
		this.fax = fax;
		this.description = description;
		this.creater = creater;
		this.modifier = modifier;
		this.state = state;
	}

	@Id
	@GeneratedValue
	@Column(name = "ORGANIZATION_ID", unique = true, nullable = false)
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "COMPANY_ID")
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Column(name = "MYID", length = 25)
	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	@Column(name = "PID")
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "FULL_NAME")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "ENAME", length = 100)
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "MANAGER")
	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	@Column(name = "ICONCLS", length = 100)
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "ASSISTANT_MANAGER")
	public Integer getAssistantManager() {
		return assistantManager;
	}

	public void setAssistantManager(Integer assistantManager) {
		this.assistantManager = assistantManager;
	}

	@Column(name = "EMP_QTY")
	public Integer getEmpQty() {
		return empQty;
	}

	public void setEmpQty(Integer empQty) {
		this.empQty = empQty;
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
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "SHORT_NAME", length = 50)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "TEL", length = 50)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "FAX", length = 50)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "DESCRIPTION", length = 2000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Column(name = "STATE", length = 20)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
