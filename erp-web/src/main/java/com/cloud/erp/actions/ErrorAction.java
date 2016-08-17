package com.cloud.erp.actions;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;

@Namespace("/error")
public class ErrorAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Action(value="error")
	public String error() throws Exception{
		Map<String, Object> parameters = ServletActionContext.getContext().getParameters();
		JSONWriterError(parameters.get("error").toString());
		return RJSON;
	}
}
