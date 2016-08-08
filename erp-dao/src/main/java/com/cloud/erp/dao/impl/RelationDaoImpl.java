package com.cloud.erp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.RelationDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.entities.table.Relation;

@Repository("relationDao")
public class RelationDaoImpl implements RelationDao {

	@Autowired
	private BaseDao<Relation> baseDao;
	
	@Override
	public List<Relation> findAll() {
		String hql = "from Relation";
		return baseDao.find(hql);
	}

	@Override
	public Relation findByModuleClass(String moduleClass) {
		String hql = "from Relation t where t.moduleClass = :modulClass";
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("modulClass", moduleClass);
		return baseDao.get(hql, param);
	}

	@Override
	public Relation findById(Integer id) {
		return baseDao.get(Relation.class, id);
	}

}
