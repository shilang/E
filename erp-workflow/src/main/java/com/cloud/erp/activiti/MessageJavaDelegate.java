package com.cloud.erp.activiti;

import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricTaskInstance;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.entities.MessageType;
import com.cloud.erp.entities.table.Message;
import com.cloud.erp.service.MessageService;
import com.cloud.erp.utils.SpringUtil;

/**
 * 
 * @author Bollen
 *
 */
//process message 
public class MessageJavaDelegate extends BaseJavaDelegate {
	
	private Expression msg;

	@SuppressWarnings("deprecation")
	@Override
	public void doExecute(DelegateExecution execution) throws Exception {
		
		AuditModel auditModel = getAuditModel();
		
	     List<HistoricTaskInstance> historicTaskInstances = execution.getEngineServices()
	    .getHistoryService()
	    .createHistoricTaskInstanceQuery()
	    .processInstanceId(execution.getProcessInstanceId())
	    .orderByHistoricTaskInstanceStartTime()
	    .desc()
	    .list();
	     
	    String initiator = execution.getVariable("initiator").toString();
	    
	    String assignee;
	    if(historicTaskInstances != null && historicTaskInstances.size() > 0){
	    	assignee = historicTaskInstances.get(0).getAssignee();
	    }else {
	    	assignee = initiator;
		}
	    
	    
	    String flag = execution.getProcessDefinitionId().split(":")[0] + ":" + execution.getCurrentActivityId();
	    //String creater = auditModel.getCreater();
	    String msgstr = msg.getValue(execution).toString();
	    String content = /*"<"+ assignee + ">" + msgstr + */"单据: " + auditModel.getNumber();
		
		Message message = new Message();
		message.setType(MessageType.process);
		message.setFlag(flag);
		message.setName(msgstr);
		message.setContent(content);
		message.setSender(assignee);
		message.setCreated(new Date());
		
		getMessageService().sendMessage(message,initiator);
	}
	
	private MessageService getMessageService(){
		return (MessageService) SpringUtil.getBean(MessageService.class);
	}

}
