package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.TransportInfoDao;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.TransportInfo;
import com.cloud.erp.service.TransportInfoService;
import com.cloud.erp.utils.PageUtil;

@Service("transportInfoServiceImpl")
public class TransportInfoServiceImpl implements TransportInfoService{
	
	@Autowired
	private TransportInfoDao transportInfoDao;

	@Override
	public List<TransportInfo> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		
		return null;
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return 0;
	}

	@Override
	public TransportInfo get(Integer id) {
		
		return transportInfoDao.get(id);
	}

	@Override
	public void update(TransportInfo master) {
		
		transportInfoDao.update(master);
	}

	@Override
	public boolean persistence(TransportInfo master) throws Exception {
		
		return transportInfoDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return transportInfoDao.deleteToUpdate(pid);
	}

	@Override
	public List<TransportInfo> findByPid(Integer pid) {
		
		return transportInfoDao.findByPid(pid);
	}

	@Override
	public boolean persistence(SalesOutStock master, TransportInfo transportInfo, String taskId) throws Exception {
		
		transportInfo.setMaster(master);
		return this.persistence(transportInfo);
	}

}
