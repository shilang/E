package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.Destination;
import com.cloud.erp.entities.table.Message;
import com.cloud.erp.service.common.GeneralService;

public interface MessageService extends GeneralService<Message>{
	
	public List<Destination> getDestinations(String flag);
	
	public List<String> getUsersByDestination(Destination destination, String initiator);

	public void sendMessage(Message message, String initiator) throws Exception;
	
	public boolean updateReadStatus(Integer id, int read);
	
}
