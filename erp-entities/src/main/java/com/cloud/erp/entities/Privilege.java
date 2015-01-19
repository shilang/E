package com.cloud.erp.entities;

public class Privilege
{
	private Integer pid;
	
	private String operation;
	
	private String remarks;

	public Integer getPid()
	{
		return pid;
	}

	public void setPid(Integer pid)
	{
		this.pid = pid;
	}

	public String getOperation()
	{
		return operation;
	}

	public void setOperation(String operation)
	{
		this.operation = operation;
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
