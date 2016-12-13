package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.erp.dao.MessageDao;
import com.cloud.erp.entities.table.Message;
import com.cloud.erp.service.MessageService;
import com.cloud.erp.utils.PageUtil;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Resource
	private MessageDao messageDao;
	
	@Override
	public List<Message> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return messageDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return messageDao.getCount(params);
	}

	@Override
	public Message get(Integer id) {
		return messageDao.get(id);
	}

	@Override
	public void update(Message master) {
		messageDao.update(master);
	}

	@Override
	public boolean persistence(Message master) throws Exception {
		return messageDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return messageDao.deleteToUpdate(pid);
	}

	@Override
	public void sendMessage(Message message) throws Exception {
		persistence(message);
	}

}
