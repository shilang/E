package com.cloud.erp.activiti;

import com.cloud.erp.service.common.CheckService;
import com.cloud.erp.utils.SpringUtil;

public abstract class CheckServiceJavaDelegate extends BaseJavaDelegate{
	
	public boolean commit(){
		return getCheckService().commit(getBusinessClass(), getBusinessKey());
	}
	
	public boolean check(){
		return getCheckService().check(getBusinessClass(), getBusinessKey());
	}
	
	public boolean changeCommit(){
		return getCheckService().changeCommit(getBusinessClass(), getBusinessKey());
	}
	
	public boolean cancelCheck(){
		return getCheckService().cancelCheck(getBusinessClass(), getBusinessKey());
	}
	
	private CheckService getCheckService(){
		return (CheckService) SpringUtil.getBean(CheckService.class);
	}
	
}
