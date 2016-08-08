package com.cloud.erp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.entities.table.MessageEntity;
import com.cloud.erp.service.Message;

@Service("businessMessage")
public class BusinessMessage implements Message {
	
	@Autowired
	private BaseDao<MessageEntity> baseDao;

	@Override
	public void sendMessage(MessageEntity messageEntity) {
		
		baseDao.save(messageEntity);
	}

}
