/**
 * @Title:  LoginDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月16日 下午4:41:07
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.LoginDao;
import com.cloud.erp.entities.shiro.ShiroUser;
import com.cloud.erp.entities.viewmodel.MenuModel;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName  LoginDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月16日 下午4:41:07
 *
 */
@SuppressWarnings("rawtypes")
@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

	private BaseDao baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.LoginService#findMenuList()
	 */
	@Override
	public List<MenuModel> findMenuList() {
		// TODO Auto-generated method stub
		
		ShiroUser user = Constants.getCurrentUser();
		String sql = null;
		
		//by default, super administrator owns all function permissions
		if (Constants.SYSTEM_ADMINISTRATOR.equals(user.getAccount()))
		{
			sql="SELECT p.PERMISSION_ID,p.PID,p.NAME,p.ICONCLS,p.URL FROM PERMISSION AS p\n" +
					"where p.STATUS='A' and p.TYPE='F' and p.ISUSED='Y' ORDER BY SORT";
		}
		else 
		{
			sql="SELECT DISTINCT p.PERMISSION_ID,p.PID,p.NAME,p.ICONCLS,p.URL FROM\n" +
					"ROLE_PERMISSION AS rp\n" +
					"INNER JOIN ROLE AS r ON rp.ROLE_ID = r.ROLE_ID\n" +
					"INNER JOIN USER_ROLE AS ur ON rp.ROLE_ID = ur.ROLE_ID\n" +
					"INNER JOIN USERS AS u ON u.USER_ID = ur.USER_ID\n" +
					"INNER JOIN PERMISSION AS p ON rp.PERMISSION_ID = p.PERMISSION_ID\n" +
					"WHERE rp.STATUS='A' and r.STATUS='A' and ur.STATUS='A' and u.STATUS='A' and p.STATUS='A' and p.TYPE='F' and p.ISUSED='Y'\n" +
					"and u.USER_ID="+user.getUserId()+" ORDER BY SORT";
		}
		
		List listmenu = baseDao.findBySQL(sql);
		List<MenuModel> parentList = new ArrayList<MenuModel>();
		for(Object object : listmenu){
			Object[] objs = (Object[]) object;
			String id = String.valueOf(objs[0]);
			if (objs[1] == null){
				MenuModel menuModel=new MenuModel();
				menuModel.setName(String.valueOf(objs[2]));
				menuModel.setIconCls(String.valueOf(objs[3]));
				menuModel.setUrl(String.valueOf(objs[4]));
				List<MenuModel> childList=new ArrayList<MenuModel>();
				for (Object obj2 : listmenu)
				{
					MenuModel menuChildModel=new MenuModel();
					Object[] objs2=(Object[])obj2;
					String sid = String.valueOf(objs2[1]);
					if (sid.equals(id))
					{
						menuChildModel.setName(String.valueOf(objs2[2]));
						menuChildModel.setIconCls(String.valueOf(objs2[3]));
						menuChildModel.setUrl(String.valueOf(objs2[4]));
						childList.add(menuChildModel);
					}
				}
				menuModel.setChild(childList);
				parentList.add(menuModel);
			}
		}
		
		return parentList;
	}

}
