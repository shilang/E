package com.cloud.erp.entities;

import java.util.Date;

public class Operator
{
	private Integer oid;
	
	private String loginName;
	
	private String password;
	
	private Date createTime;
	
	private Date loginTime;
	
	private String remarks;

	public Integer getOid()
	{
		return oid;
	}

	public void setOid(Integer oid)
	{
		this.oid = oid;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getLoginTime()
	{
		return loginTime;
	}

	public void setLoginTime(Date loginTime)
	{
		this.loginTime = loginTime;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	
	
}
