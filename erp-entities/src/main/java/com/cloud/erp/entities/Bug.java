/**
 * @Title:  Bug.java
 * @Package:  com.cloud.erp.entities
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午10:03:03
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
 * @ClassName Bug
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月31日 上午10:03:03
 *
 */
@Entity
@Table(name = "BUG")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Bug implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer bugId;
	private String bugName;
	private String status;
	private String attachmentUrl;
	private String description;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifier;

	public Bug() {
	}

	public Bug(String bugName, String description, Date created, Date lastmod,
			Integer creater, Integer modifier) {
		this.bugName = bugName;
		this.description = description;
		this.created = created;
		this.lastmod = lastmod;
		this.creater = creater;
		this.modifier = modifier;
	}

	@Id
	@GeneratedValue
	@Column(name = "BUG_ID", unique = true, nullable = false)
	public Integer getBugId() {
		return bugId;
	}

	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}

	@Column(name = "BUG_NAME", length = 300)
	public String getBugName() {
		return bugName;
	}

	public void setBugName(String bugName) {
		this.bugName = bugName;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ATTACHMENT_URL", length = 300)
	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	@Column(name = "DESCRIPTION", length = 2000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
