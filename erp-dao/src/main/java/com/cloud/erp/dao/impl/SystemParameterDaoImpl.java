/**
 * @Title:  SystemParameterDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午9:33:55
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.SystemParameterDao;
import com.cloud.erp.entities.Parameter;
import com.cloud.erp.entities.shiro.ShiroUser;
import com.cloud.erp.entities.viewmodel.CheckBoxModel;
import com.cloud.erp.entities.viewmodel.Options;
import com.cloud.erp.entities.viewmodel.ParameterModel;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName  SystemParameterDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月31日 上午9:33:55
 *
 */
@Repository("systemParameterDao")
public class SystemParameterDaoImpl implements SystemParameterDao {

	private BaseDao<Parameter> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Parameter> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.SystemParameterDao#findParameterList(java.lang.String)
	 */
	@Override
	public List<ParameterModel> findParameterList(String type) {

		String hql="from Parameter t where t.status='A'";
		 List<Parameter> temp = baseDao.find(hql);
		 List<ParameterModel> list2=new ArrayList<ParameterModel>();
		 for (Parameter p : temp)
		{
			ParameterModel pm=new ParameterModel();
			try
			{
				BeanUtils.copyProperties(pm, p);
				if ("checkbox".equals(p.getEditorType()))
				{
					CheckBoxModel cm=new CheckBoxModel();
					cm.setType("checkbox");
					cm.setOptions(new Options());
					pm.setEditor(cm);
				}else {
					pm.setEditor(p.getEditorType());
				}
				list2.add(pm);
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			} catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		return list2;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.SystemParameterDao#persistenceParameter(java.util.Map)
	 */
	@Override
	public boolean persistenceParameter(Map<String, List<Parameter>> map) {
		this.addParameter(map.get("addList"));
		this.updParameter(map.get("updList"));
		this.delParameter(map.get("delList"));
		return true;
	}
	
	public boolean addParameter(List<Parameter> addlist) 
	{
		
		if (addlist!=null&&addlist.size()!=0) {
			ShiroUser users = Constants.getCurrentUser();
			for (Parameter companyInfo : addlist) {
				companyInfo.setCreated(new Date());
				companyInfo.setLastmod(new Date());
				companyInfo.setStatus("A");
				if (users!=null)
				{
					companyInfo.setCreater(users.getUserId());
					companyInfo.setModifier(users.getUserId());
				}
				baseDao.save(companyInfo);
			}
		}
		return true;
	}
	
	public boolean updParameter(List<Parameter>  updlist) 
	{
		if (updlist!=null&&updlist.size()!=0) {
			ShiroUser users = Constants.getCurrentUser();
			for (Parameter companyInfo : updlist) {
				
				companyInfo.setLastmod(new Date());
				companyInfo.setModifier(users.getUserId());
				baseDao.update(companyInfo);
			}
		}
		return true;
	}
	
	public boolean delParameter(List<Parameter>  dellist)
	{
		if (dellist!=null&&dellist.size()!=0) {
			for (Parameter companyInfo : dellist) {
				companyInfo.setStatus("I");
				companyInfo.setLastmod(new Date());
				baseDao.deleteToUpdate(companyInfo);
			}
		}
		return true;
	}

}
