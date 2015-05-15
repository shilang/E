/**
 * @Title:  CustomerDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午9:18:26
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.CustomerDao;
import com.cloud.erp.entities.table.Customer;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  CustomerDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午9:18:26
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	private BaseDao<Customer> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Customer> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.CustomerDao#findCustomers(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Customer> findCustomers(Map<String, Object> params,
			PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "from Customer t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDao.find(hql, params, pageUtil.getPage(), pageUtil.getRows());
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.CustomerDao#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Customer t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDao.count(hql, params);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.CustomerDao#persistenceCustomer(com.cloud.erp.entities.table.Customer)
	 */
	@Override
	public boolean persistenceCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if(null == customer.getCustomerId() || "".equals(customer.getCustomerId())){
			customer.setCreated(new Date());
			customer.setCreater(userId);
			customer.setLastmod(new Date());
			customer.setModifier(userId);
			customer.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(customer);
		}else {
			customer.setLastmod(new Date());
			customer.setModifier(userId);
			baseDao.update(customer);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.CustomerDao#delCustomer(java.lang.Integer)
	 */
	@Override
	public boolean delCustomer(Integer id) {
		// TODO Auto-generated method stub
		Customer customer = baseDao.get(Customer.class, id);
		customer.setLastmod(new Date());
		customer.setModifier(Constants.getCurrentUser().getUserId());
		customer.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		baseDao.deleteToUpdate(customer);
		
		return true;
	}

}
