package com.cloud.erp.activiti;

import com.cloud.erp.service.common.CheckService;
import com.cloud.erp.utils.SpringUtil;

public abstract class BusinessServiceJavaDelegate extends BaseJavaDelegate{
	
	public boolean commit(){
		getCheckService().commit(getBusinessClass(), getBusinessKey());
		
		return true;
	}
	
	public boolean check(){
		getCheckService().check(getBusinessClass(), getBusinessKey());
		
		return true;
	}
	
	public boolean changeCommit(){
		getCheckService().changeCommit(getBusinessClass(), getBusinessKey());
		
		return true;
	}
	
	public boolean cancelCheck(String cancelReason){
		getCheckService().cancelCheck(getBusinessClass(), getBusinessKey(), cancelReason);
		
		return true;
	}
	
	public boolean updateSettleInfo(boolean isDelete){
		getCheckService().updateSettleInfo(getBusinessClass(), getBusinessKey(), isDelete);
		
		return true;
	}
	
	private CheckService getCheckService(){
		return (CheckService) SpringUtil.getBean(CheckService.class);
	}
	
}
