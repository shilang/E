package com.cloud.erp.dao.common;

import com.cloud.erp.exceptions.UpdateReferenceException;

public interface ReferenceDao<T> {

	boolean updateReference(Class<T>clazz, String number, boolean mode) throws UpdateReferenceException;
	
}
