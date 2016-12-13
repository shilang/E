package com.cloud.erp.service;

import com.cloud.erp.entities.table.Message;
import com.cloud.erp.service.common.GeneralService;

public interface MessageService extends GeneralService<Message>{

	public void sendMessage(Message message) throws Exception;
	
}
