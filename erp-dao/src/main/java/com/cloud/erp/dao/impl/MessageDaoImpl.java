package com.cloud.erp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.MessageDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.entities.table.Message;
import com.cloud.erp.utils.PageUtil;

@Repository("messageDao")
public class MessageDaoImpl implements MessageDao{
	
	@Resource
	private GeneralDaoSupport<Message> generalDao;
	
	@Override
	public List<Message> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(Message.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Message.class, params);
	}

	@Override
	public Message get(Integer id) {
		return generalDao.get(Message.class, id);
	}

	@Override
	public void update(Message master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(Message master) throws Exception {
		return generalDao.persistence(master, null);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Message.class, pid, null);
	}

}
