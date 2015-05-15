/**
 * @Title:  FunctionDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.FunctionDao;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.entities.viewmodel.TreeGridModel;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName FunctionDaoImpl
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年2月13日 下午5:29:54
 *
 */
@Repository("functionDao")
public class FunctionDaoImpl implements FunctionDao {

	private BaseDao<Permission> baseDao;

	/**
	 * @param baseDao
	 *            the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Permission> baseDao) {
		this.baseDao = baseDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.FunctionDao#findAllFunctionList(java.lang.Integer)
	 */
	@Override
	public List<TreeGridModel> findAllFunctionList(Integer pid) {
		// TODO Auto-generated method stub
		String hql = "from Permission t where t.status='A' ";
		if (pid == null || "".equals(pid)) {
			hql += " and t.pid is null";
		} else {
			hql += " and t.pid=" + pid;
		}

		List<Permission> list = baseDao.find(hql);
		List<TreeGridModel> tempList = new ArrayList<TreeGridModel>();
		for (Permission function : list) {
			TreeGridModel treeGridModel = new TreeGridModel();
			try {
				BeanUtils.copyProperties(treeGridModel, function);
				if (pid == null || "".equals(pid)) {
					treeGridModel.setPid(null);
				}
				tempList.add(treeGridModel);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return tempList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.FunctionDao#delFunction(java.lang.Integer)
	 */
	@Override
	public boolean delFunction(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from Permission t where t.status='A' and t.pid=" + id;
		List<Permission> list = baseDao.find(hql);
		if (list.size() != 0) {
			return false;
		} else {
			Permission function = baseDao.get(Permission.class, id);
			function.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
			function.setLastmod(new Date());
			function.setModifier(Constants.getCurrentUser().getUserId());
			baseDao.deleteToUpdate(function);
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.FunctionDao#persistenceFunction(java.util.List)
	 */
	@Override
	public boolean persistenceFunction(List<Permission> list) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		for (Permission function : list) {
			function.setLastmod(new Date());
			function.setModifier(userId);
			if (Constants.TREE_GRID_ADD_STATUS.equals(function.getStatus())) {
				function.setPermissionId(null);
				function.setCreated(new Date());
				function.setLastmod(new Date());
				function.setModifier(userId);
				function.setCreater(userId);
				function.setStatus(Constants.PERSISTENCE_STATUS);
			}

			baseDao.saveOrUpdate(function);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.FunctionDao#findAllFunctionList()
	 */
	@Override
	public List<TreeModel> findAllFunctionList() {
		// TODO Auto-generated method stub
		String hql = "from Permission t where t.status='A' and t.type='F' ";
		List<Permission> list = baseDao.find(hql);
		List<TreeModel> tempList = new ArrayList<TreeModel>();
		for (Permission function : list) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(function.getPermissionId().toString());
			treeModel.setPid(function.getPid() == null ? "" : function.getPid()
					.toString());
			treeModel.setName(function.getName());
			treeModel.setIconCls(function.getIconCls());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			tempList.add(treeModel);
		}
		return tempList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cloud.erp.dao.FunctionDao#persistenceFunction(com.cloud.erp.entities
	 * .Permission)
	 */
	@Override
	public boolean persistenceFunction(Permission permission) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
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

}
