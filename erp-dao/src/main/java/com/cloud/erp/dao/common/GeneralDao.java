package com.cloud.erp.dao.common;


/**
 * 
 * @author Administrator
 *
 * @param <S> module service name
 * @param <M> module master entity name
 * @param <E> module entry name
 */
public interface GeneralDao<M> extends General<M>{

/*	List<M> findAll(Class<?>clazz, Map<String, Object> params, PageUtil pageUtil, String... extHql);
	
	long getCount(Class<?>clazz, Map<String, Object> params, PageUtil pageUtil, String... extHql);
	
    M get(Class<M> clazz, Integer id);
	
	void update(M master);
	
	boolean persistence(M master) throws Exception;
	
	boolean deleteToUpdate(Class<M>clazz, Integer pid);*/
	
}
