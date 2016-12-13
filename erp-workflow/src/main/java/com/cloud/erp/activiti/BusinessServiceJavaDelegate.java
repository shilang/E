package com.cloud.erp.activiti;

import java.util.Date;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.entities.common.MessageType;
import com.cloud.erp.entities.table.Message;
import com.cloud.erp.service.MessageService;
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
	
	private Message MessageWrapper(Integer flag, String action){
		AuditModel auditModel = getAuditModel();
		
		Message message = new Message();
		message.setType(MessageType.process);
		message.setFlag(flag);
		message.setName("单据["+auditModel.getNumber()+"]");
		message.setContent("");
		message.setCreater(auditModel.getCreater());
		message.setCreated(new Date());
		
		return null;
	}
	
	private CheckService getCheckService(){
		return (CheckService) SpringUtil.getBean(CheckService.class);
	}
	
	private MessageService getMessageService(){
		return (MessageService)SpringUtil.getBean(MessageService.class);
	}
	
}
