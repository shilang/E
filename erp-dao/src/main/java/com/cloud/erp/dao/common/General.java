package com.cloud.erp.dao.common;

import java.util.List;
import java.util.Map;

import com.cloud.erp.utils.PageUtil;

/**
 * 
 * @author Bollen
 *
 * @param <M>
 */
public interface General<M> {

	List<M> findAll(Map<String, Object> params, PageUtil pageUtil);

	long getCount(Map<String, Object> params);

	M get(Integer id);

	void update(M master);

	boolean persistence(M master) throws Exception;

	boolean deleteToUpdate(Integer pid);
}
