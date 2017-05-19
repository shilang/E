package com.cloud.erp.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.MessageDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Destination;
import com.cloud.erp.entities.table.Message;
import com.cloud.erp.utils.PageUtil;

@Repository("messageDao")
public class MessageDaoImpl implements MessageDao{
	
	@Resource
	private GeneralDaoSupport<Message> generalDao;
	
	@Resource
	private BaseDao<?> baseDao;
	
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
		return generalDao.persistence(master, new StatusFields());
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Message.class, pid, new StatusFields());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Destination> getDestinations(String flag) {
		String hql = "from Destination t where t.belongTo=:belongTo";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("belongTo", flag);
		return (List<Destination>) baseDao.find(hql, params);
	}

	@Override
	public List<String> getUsersByDestination(Destination destination, String initiator) {
		List<String> users = new ArrayList<String>();
		String type = destination.getType();
		if("role".equals(type)){
			String sql = "SELECT USER_ID_, GROUP_ID_ FROM ACT_ID_MEMBERSHIP t WHERE t.GROUP_ID_='"
				       + destination.getContent() + "'";
			List<?> list = baseDao.findBySQL(sql);
			for (Object object : list) {
				Object[] o = (Object[]) object;
				users.add(o[0].toString());
			}
		}else if("self".equals(type) && initiator != null){
			users.add(initiator);
		}else {
			users.add(destination.getContent());
		}
		return users;
	}

}
