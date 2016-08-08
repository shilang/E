package com.cloud.erp.dao.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.exceptions.UpdateReferenceException;

@Repository("referenceDao")
public class ReferenceDaoSupport<T> implements ReferenceDao<T> {
	
	private String children = METHOD_CHILDREN;
	private String billNo = METHOD_BILLNO;
	
	private static final String METHOD_CHILDREN = "children";
	private static final String METHOD_BILLNO = "billNo";
	
	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	/**
	 * clazz master type
	 */
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	@Override
	public boolean updateReference(Class<T> clazz, String number, boolean mode)
			throws UpdateReferenceException {
			if(null == number || "".equals(number)){
				return false;
			}
			String hql = "update " + clazz.getSimpleName() + " t set t." + getChildren() +" = t." + getChildren() + " + " + (mode?1:-1) + " where t." + getBillNo() + "='" + number + "'";
			if(baseDao.executeUpdate(hql) > 0){
				return true;
			}else {
				throw new UpdateReferenceException("更新引用异常");
			}
	}
}
