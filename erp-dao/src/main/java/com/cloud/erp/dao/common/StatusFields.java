package com.cloud.erp.dao.common;

public class StatusFields {

	private static final String METHOD_MASTER = "master";
	private static final String METHOD_MASTER_ID = "interId";
	private static final String METHOD_CREATER = "creater";
	private static final String METHOD_CREATED = "created";
	private static final String METHOD_MODIFIER = "modifier";
	private static final String METHOD_LASTMOD = "lastmod";
	private static final String METHOD_STATUS = "status";
	private static final String METHOD_ENTRIES = "entries";

	private String master = METHOD_MASTER;
	private String interId = METHOD_MASTER_ID;
	private String creater = METHOD_CREATER;
	private String created = METHOD_CREATED;
	private String modifier = METHOD_MODIFIER;
	private String lastmod = METHOD_LASTMOD;
	private String status = METHOD_STATUS;
	private String entries = METHOD_ENTRIES;

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getInterId() {
		return interId;
	}

	public void setInterId(String interId) {
		this.interId = interId;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getLastmod() {
		return lastmod;
	}

	public void setLastmod(String lastmod) {
		this.lastmod = lastmod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEntries() {
		return entries;
	}

	public void setEntries(String entries) {
		this.entries = entries;
	}
}
