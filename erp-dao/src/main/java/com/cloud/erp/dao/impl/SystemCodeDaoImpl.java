/**
 * @Title:  SystemCodeDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月30日 上午11:01:24
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.SystemCodeDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.entities.table.SystemCode;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName  SystemCodeDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月30日 上午11:01:24
 *
 */
@Repository("systemCodeDao")
public class SystemCodeDaoImpl implements SystemCodeDao {

	private BaseDao<SystemCode> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<SystemCode> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<SystemCode> findSystemCodeList(Integer id) {
		
		String hql = "from SystemCode t where t.status = 'A'";
		if(null == id || "".equals(id)){
			hql += " and t.parentId is null";
		}else {
			hql += " and t.parentId=" + id;
		}
		return baseDao.find(hql);
	}

	@Override
	public List<TreeModel> findSystemCodeList() {
		
		String hql = "from SystemCode t where t.status = 'A'";
		List<SystemCode> list = baseDao.find(hql);
		List<TreeModel> tempList = new ArrayList<TreeModel>();
		for(SystemCode s : list){
			TreeModel treeModel = new TreeModel();
			treeModel.setId(s.getCodeId().toString());
			treeModel.setPid(s.getParentId() == null ? "": s.getParentId().toString());
			treeModel.setName(s.getName());
			treeModel.setIconCls(s.getIconCls());
			treeModel.setState(s.getState());
			treeModel.setState("open");
			treeModel.setPermissionId(s.getPermissionId());
			tempList.add(treeModel);
		}
		return tempList;
	}

	@Override
	public boolean persistenceSystemCodeDig(SystemCode systemCode,
			String permissionName, Integer codePid) {
		
		
		Integer userid = Commons.getCurrentUser().getUserId();
		Integer pid = systemCode.getParentId();
		Integer codeId = systemCode.getCodeId();
		if(null == codeId || "".equals(codeId)){
			systemCode.setCreated(new Date());
			systemCode.setLastmod(new Date());
			systemCode.setCreater(userid);
			systemCode.setModifier(userid);
			systemCode.setState(Constants.PERSISTENCE_STATUS);
			systemCode.setType("D");
			if(null == pid || "".equals(pid)){
				systemCode.setState(Constants.TREE_STATUS_OPEN);
			}else{
				SystemCode pCode = baseDao.get(SystemCode.class, pid);
				if(!Constants.TREE_STATUS_CLOSED.equals(pCode.getState())){
					pCode.setState(Constants.TREE_STATUS_CLOSED);
					baseDao.update(pCode);
				}
				systemCode.setState((Constants.TREE_STATUS_OPEN));
			}
			List<SystemCode> list = isExtPermissionId(systemCode.getPermissionId());
			if(list.size() != 0){
				if(pid == null || "".equals(pid)){
					SystemCode sysc = list.get(0);
					systemCode.setParentId(sysc.getCodeId());
				}
			}else {
				SystemCode ss=new SystemCode();
				ss.setCreated(new Date());
				ss.setLastmod(new Date());
				ss.setCreater(userid);
				ss.setModifier(userid);
				ss.setStatus(Constants.PERSISTENCE_STATUS);
				ss.setPermissionId(systemCode.getPermissionId());
				String[] temp=permissionName.split(",");
				ss.setName(temp[0]);
				ss.setState(Constants.TREE_STATUS_CLOSED);
				ss.setIconCls(temp[1]);
				ss.setType("M");
				baseDao.save(ss);
				systemCode.setParentId(ss.getCodeId());
			}
			baseDao.save(systemCode);
		}else {
			systemCode.setLastmod(new Date());
			systemCode.setModifier(userid);
			baseDao.update(systemCode);
		}
		return true;
	}
	
	/**
	 * function: check
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月30日 上午11:24:42
	 * @Title: isExtPermissionId
	 * @param permissionId
	 * @return
	 */
	public List<SystemCode> isExtPermissionId(Integer permissionId)
	{
		String hql=" from SystemCode t where t.status='A' and t.type='M' and t.permissionId="+permissionId;
		return baseDao.find(hql);
	}

	@Override
	public boolean delSystemCode(Integer codeId) {
		String hql=" from SystemCode t where t.status='A' and t.parentId="+codeId;
		List<SystemCode> list = baseDao.find(hql);
		if (list.size()!=0)
		{
			return false;
		}else {
			Integer userid = Commons.getCurrentUser().getUserId();
			SystemCode s = baseDao.get(SystemCode.class,codeId);
			s.setLastmod(new Date());
			s.setModifier(userid);
			s.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
			baseDao.deleteToUpdate(s);
			return true;
		}
	}

	@Override
	public List<SystemCode> findSystemCodeByType(String codeMyid) {
		String hql="from SystemCode t where t.status='A' and t.type='D' and t.codeMyid='"+codeMyid+"'";
		List<SystemCode> list = baseDao.find(hql);
		if (list.size()==1)
		{
			SystemCode ss = list.get(0);
			String hql2="from SystemCode t where t.status='A' and t.parentId="+ss.getCodeId();
			return baseDao.find(hql2);
		}
		return null;
	}

}
