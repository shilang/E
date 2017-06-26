package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.MessageDao;
import com.cloud.erp.entities.table.Destination;
import com.cloud.erp.entities.table.Message;
import com.cloud.erp.service.MessageService;
import com.cloud.erp.utils.PageUtil;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Resource
	private MessageDao messageDao;
	
	@Autowired
	private SimpMessagingTemplate template;
	
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
	public List<String> getUsersByDestination(Destination destination, String initiator) {
		return messageDao.getUsersByDestination(destination, initiator);
	}
	
	@Override
	public List<Destination> getDestinations(String flag) {
		return messageDao.getDestinations(flag);
	}

	@Override
	public void sendMessage(Message message, String initiator) throws Exception {
		List<Destination> destinations = getDestinations(message.getFlag());
		for (Destination destination : destinations) {
			List<String> users = getUsersByDestination(destination, initiator);
			for (String user : users) {
				message.setOwner(user);
				
				String dest = "/topic/" + user;
				this.template.convertAndSend(dest, message);
				
				Message newmsg = new Message();
				BeanUtils.copyProperties(newmsg, message);
				persistence(newmsg);
			}
		}
	}

	@Override
	public boolean updateReadStatus(Integer id, int read) {
		Message message = get(id);
		message.setRead(read);
		update(message);
		return true;
	}
}
