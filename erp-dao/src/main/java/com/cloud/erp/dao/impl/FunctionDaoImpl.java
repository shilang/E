/**
 * @Title:  FunctionDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月13日  下午5:29:54
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.FunctionDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName FunctionDaoImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年2月13日 下午5:29:54
 *
 */
@Repository("functionDao")
public class FunctionDaoImpl implements FunctionDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("permissionId");
	}
	
	@Resource
	private GeneralDaoSupport<Permission> generalDao;

	@Autowired
	private BaseDao<Permission> baseDao;
	
	@Override
	public List<Permission> findFunctionsById(Integer pid) {
		String hql = "from Permission t where t.status='A' ";
		if (pid == null || "".equals(pid)) {
			hql += " and t.pid is null";
		} else {
			hql += " and t.pid=" + pid;
		}
		return baseDao.find(hql);
	}
	
	@Override
	public List<Permission> findAllWithExtHql() {
		return generalDao.findAll(Permission.class, null, null, "and type='F'");
	}

	@Override
	public List<Permission> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(Permission.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Permission.class, params);
	}

	@Override
	public Permission get(Integer id) {
		return generalDao.get(Permission.class, id);
	}

	@Override
	public void update(Permission master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(Permission permission) throws Exception {
		Integer userId = Commons.getCurrentUser().getUserId();
		if (null == permission.getPermissionId()
				|| "".equals(permission.getPermissionId())) {
			permission.setCreated(new Date());
			permission.setLastmod(new Date());
			permission.setCreater(userId);
			permission.setModifier(userId);
			permission.setStatus(Constants.PERSISTENCE_STATUS);
			if (Constants.IS_FUNCTION.equals(permission.getType())) {
				permission.setState(Constants.TREE_STATUS_CLOSED);
			} else {
				permission.setState(Constants.TREE_STATUS_OPEN);
			}
			baseDao.save(permission);
		} else {
			if (Constants.IS_FUNCTION.equals(permission.getType())) {
				permission.setState(Constants.TREE_STATUS_CLOSED);
			} else {
				permission.setState(Constants.TREE_STATUS_OPEN);
			}
			permission.setLastmod(new Date());
			permission.setModifier(userId);
			baseDao.update(permission);
		}
		return true;
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		String hql = " from Permission t where t.status='A' and t.pid=" + pid;
		List<Permission> list = baseDao.find(hql);
		if (list.size() != 0) {
			return false;
		} 
		
		return generalDao.deleteToUpdate(Permission.class, pid, statusFields);
	}

}
