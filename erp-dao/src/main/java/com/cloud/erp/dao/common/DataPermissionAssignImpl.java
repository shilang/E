package com.cloud.erp.dao.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.cloud.erp.entities.datafilter.FilterGroup;
import com.cloud.erp.entities.datafilter.FilterTranslator;
import com.cloud.erp.entities.sys.RObject;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.table.UserRole;
import com.cloud.erp.utils.Commons;

/**
 * 
 * @author Bollen
 *
 */
@Repository("dataPermissionAssign")
public class DataPermissionAssignImpl extends DataPermissionAssign {
	
	private static final Logger logger = LoggerFactory.getLogger(DataPermissionAssignImpl.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void RegCurrentParmMatch(){
		try {
			FilterTranslator.RegCurrentParmMatch("{CurrentRoleIDs}", new RObject(this, this.getClass().getMethod("getRolesByUserId"), null));
			FilterTranslator.RegCurrentParmMatch("{CurrentUserID}", new RObject(this, this.getClass().getMethod("getUserId"), null));
		} catch (NoSuchMethodException | SecurityException e) {
			if(logger.isDebugEnabled()){
				logger.debug("RegCurrentParmMatch failed");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getRolesByUserId(){
		Integer userId = getUserId();
		String hql = "from User u inner join fetch u.userRoles ur inner join fetch ur.role r where " 
		+ "u.userId=" + userId + " and u.status='A' and ur.status='A' and r.status='A'";
		List<User> users = baseDao.find(hql);
		List<Integer> roleIds = new ArrayList<Integer>();
		if(null != users && !users.isEmpty()){
			 User user = users.get(0);
			 Iterator<UserRole> iterator = user.getUserRoles().iterator();
			 while (iterator.hasNext()) {
				 roleIds.add(iterator.next().getRole().getRoleId());
			}
		}
		return roleIds;
	}
	
	public Integer getUserId(){
		return Commons.getCurrentUser().getUserId();
	}
	
	@SuppressWarnings("rawtypes")
	public FilterGroup getRule(String moduleName){
		FilterGroup filterGroup = null;
		String sql = "SELECT t.DATA_RULE FROM DATA_PERMISSION t WHERE t.STATUS='A' and t.DATA_TABLE='" 
		+ moduleName + "'";
		List list = baseDao.findBySQL(sql);
		if(null != list && list.size() > 0){
			String rule = list.get(0).toString();
			filterGroup = JSON.parseObject(rule, FilterGroup.class);
		}
		return filterGroup;
	}
	
	private boolean isAdmin(){
		return "admin".equals(Commons.getCurrentUser().getAccount().toLowerCase());
	}

	@Override
	public String translate(String target) {
		if (isAdmin()){
			return "";
		}
		return " and " + super.translate(target);
	}		
}
