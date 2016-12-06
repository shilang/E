package com.cloud.erp.dao.common;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.RelationDao;
import com.cloud.erp.entities.table.Relation;
import com.cloud.erp.entities.type.OperType;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.ObjectUtil;
import com.cloud.erp.utils.PageUtil;
import com.cloud.erp.utils.Reflect;

@Repository("generalDao")
public class GeneralDaoSupport<M> extends UpdateStatus{
	
	private static final Logger log = LoggerFactory.getLogger(GeneralDaoSupport.class);
	
	private static final String HQL_ALIAS = "t";
	private static final String HQL_PREFIX_FIND = "";
	private static final String HQL_PREFIX_COUNT = "select count(*)";
	
	private String hqlFindPrefix = HQL_PREFIX_FIND;
	private String hqlCntPrefix = HQL_PREFIX_COUNT;

	public String getHqlFindPrefix() {
		return hqlFindPrefix;
	}

	public void setHqlFindPrefix(String hqlFindPrefix) {
		this.hqlFindPrefix = hqlFindPrefix;
	}
	
	public String getHqlCntPrefix() {
		return hqlCntPrefix;
	}

	public void setHqlCntPrefix(String hqlCntPrefix) {
		this.hqlCntPrefix = hqlCntPrefix;
	}
	
	@Autowired
	private DataPermissionAssign dataPermissionAssign;

	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;

	@Autowired
	private RelationDao relationDao;
	
	private String analyseExtHql(String alias, String[] extHql){
		String extHqlString = " ";
		if(null != extHql && extHql.length > 0){
			for(String e : extHql){
				String[] tokens = e.split(" ");
				for(int i = 0; i < tokens.length; i++){
					if("and".equalsIgnoreCase(tokens[i]) || "or".equalsIgnoreCase(tokens[i])){
						if("(".equals(tokens[i+1])){
							tokens[i+2] = alias + "." + tokens[i+2];
						}else {
							if(tokens[i+1].startsWith("(")){
								tokens[i+1] = "(" + alias + "." + tokens[i+1].substring(1);
							}else {
								tokens[i+1] = alias + "." + tokens[i+1];
							}
						}
					}
					extHqlString += tokens[i] + " ";
				}
			}
		}
		return extHqlString;
	}
	
	private String analyseSelectHql(Class<?> clazz, String hqlPrefix, Map<String, Object> params, String[] extHql){
		
		String dataRule = "";
		String entityName = clazz.getSimpleName();
		
		Relation relation = relationDao.findByModuleClass(entityName);
		
		// TODO cancel data permission
		if(null != relation){
			dataRule = dataPermissionAssign.translate(relation.getModuleClass());
			entityName = relation.getTableName();
		}
	
		String hql = " from " + entityName  + " " + HQL_ALIAS + " where " + HQL_ALIAS + ".status = 'A' " + dataRule;
		
		if(log.isDebugEnabled()){
			log.debug("analyseSelectHql: {}", hql);
		}
		
		//extend sql
		hql = hql + analyseExtHql(HQL_ALIAS, extHql);
		
		//add sql prefix
		if (null != hqlPrefix && !hqlPrefix.isEmpty()){
			hql = hqlPrefix + hql;
		}
		
		hql = Commons.getCriteriaHQL(hql, HQL_ALIAS, params);
		
		return hql; 
	}
	
	private Map<String, Object> analyseSearchParams(Map<String, Object> params){
		return Commons.getSearchParamsHQL(params);
	}
	
	@SuppressWarnings("unchecked")
	public List<M> findAll(Class<?> clazz, Map<String, Object> params, PageUtil pageUtil, String... extHql) {
		return baseDao.find(analyseSelectHql(clazz, getHqlFindPrefix(), params, extHql),
				      analyseSearchParams(params), pageUtil);
	}

	@SuppressWarnings("unchecked")
	public long getCount(Class<?> clazz, Map<String, Object> params, String... extHql) {
		return baseDao.count(analyseSelectHql(clazz, getHqlCntPrefix(), params, extHql), 
				analyseSearchParams(params));
	}
	
	@SuppressWarnings("unchecked")
	public M get(Class<M> clazz, Integer id) {
		return (M)baseDao.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public void update(M master) {
		baseDao.update(master);
	}

	@SuppressWarnings("unchecked")
	public boolean persistence(M master, StatusFields statusFields) throws Exception {
			Object id = Reflect.invokeGetMethod(master, statusFields.getInterId());
		    //new creation
			if(null == id){
				baseDao.save(updateEntityInfo(master, statusFields, OperType.create));
			}else {
				//get old object from database
				Object dest = baseDao.get(master.getClass(), (Serializable)id);
				ObjectUtil.copyNonNullOrEmptyProperties(dest, master);
				baseDao.update(updateEntityInfo(dest, statusFields, OperType.update));
			}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteToUpdate(Class<M> clazz, Integer pid, StatusFields statusFields) {
		//get master
		M master = get(clazz, pid);
		//delete master
		baseDao.deleteToUpdate(updateEntityInfo(master, statusFields, OperType.delete));
		return true;
	}
}
