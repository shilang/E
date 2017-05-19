package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.entities.table.Destination;
import com.cloud.erp.entities.table.Message;

public interface MessageDao extends GeneralDao<Message>{
	
	public List<Destination> getDestinations(String flag);
	
	public List<String> getUsersByDestination(Destination destination, String initiator);
	
}
