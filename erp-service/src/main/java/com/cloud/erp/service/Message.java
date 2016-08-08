package com.cloud.erp.service;

import com.cloud.erp.entities.table.MessageEntity;

public interface Message {
	
	void sendMessage(MessageEntity messageEntity);
	
}
