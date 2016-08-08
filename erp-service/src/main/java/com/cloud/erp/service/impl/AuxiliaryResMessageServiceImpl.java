/**
 * @Title:  AuxiliaryResMessageServiceImpl.java
 * @Package:  AuxiliaryResMessageService
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月5日 上午11:14:16
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.AuxiliaryResMessageDao;
import com.cloud.erp.entities.table.AuxiliaryResMessage;
import com.cloud.erp.service.AuxiliaryResMessageService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName AuxiliaryResMessageServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月5日 上午11:14:16
 *
 */
@Service("auxiliaryResMessageService")
public class AuxiliaryResMessageServiceImpl implements
		AuxiliaryResMessageService {

	@Autowired
	private AuxiliaryResMessageDao auxiliaryResMessageDao;

	@Override
	public List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId) {
		return auxiliaryResMessageDao.findAuxiliaryResMessages(resId);
	}

	@Override
	public Long getCount(Integer resId) {
		return auxiliaryResMessageDao.getCount(resId);
	}

	@Override
	public List<AuxiliaryResMessage> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return auxiliaryResMessageDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return auxiliaryResMessageDao.getCount(params);
	}

	@Override
	public AuxiliaryResMessage get(Integer id) {
		return auxiliaryResMessageDao.get(id);
	}

	@Override
	public void update(AuxiliaryResMessage master) {
		auxiliaryResMessageDao.update(master);
	}

	@Override
	public boolean persistence(AuxiliaryResMessage master) throws Exception {
		return auxiliaryResMessageDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return auxiliaryResMessageDao.deleteToUpdate(pid);
	}

}
