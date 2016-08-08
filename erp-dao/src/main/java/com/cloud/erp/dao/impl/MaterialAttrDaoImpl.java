package com.cloud.erp.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.MaterialAttrDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.ICItemAttr;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Repository("materialAttrDao")
public class MaterialAttrDaoImpl implements MaterialAttrDao {
	
    private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("attrId");
	}
	
	@Resource
	private GeneralDaoSupport<ICItemAttr> generalDao;

	@Autowired
	private BaseDao<ICItemAttr> baseDao;

	@Override
	public List<ICItemAttr> findMaterialAttrsByPId(Integer pid) {
		String hql = "from ICItemAttr t where t.status='A' ";
		if(null == pid || "".equals(pid)){
			hql += " and t.pid is null";
		}else{
			hql += " and t.pid=" + pid;
		}
		return baseDao.find(hql);
	}

	@Override
	public List<ICItemAttr> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(ICItemAttr.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(ICItemAttr.class, params);
	}

	@Override
	public ICItemAttr get(Integer id) {
		return generalDao.get(ICItemAttr.class, id);
	}

	@Override
	public void update(ICItemAttr master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(ICItemAttr icItemAttr) throws Exception {
		
		Integer userId = Commons.getCurrentUser().getUserId();
		if(null == icItemAttr.getAttrId()||"".equals(icItemAttr.getAttrId())){
			icItemAttr.setCreated(new Date());
			icItemAttr.setCreater(userId);
			icItemAttr.setLastmod(new Date());
			icItemAttr.setModifier(userId);
			icItemAttr.setStatus(Constants.PERSISTENCE_STATUS);
			icItemAttr.setState(Constants.TREE_STATUS_CLOSED);
			baseDao.save(icItemAttr);
		}else {
			icItemAttr.setLastmod(new Date());
			icItemAttr.setModifier(userId);
			icItemAttr.setState(Constants.TREE_STATUS_CLOSED);
			baseDao.update(icItemAttr);
		}
		
		return true;
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		String hql = "from ICItemAttr t where t.status='A' and t.pid=" + pid;
		List<ICItemAttr> list = baseDao.find(hql);
		if(list.size() != 0){
			return false;
		}
		return generalDao.deleteToUpdate(ICItemAttr.class, pid, statusFields);
	}

}
