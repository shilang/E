package com.cloud.erp.service.common;

import java.util.List;

import com.cloud.erp.exceptions.UpdateReferenceException;

/**
 * share entry service
 * @author Administrator
 *
 * @param <T>  shared type
 * @param <E>  actual type
 */
public interface ShareEntryService<T, E> {
	
	List<T> findShareEntries(String entryType, Integer pid);
	
	void updateReferenceFor(String number, boolean mode) throws UpdateReferenceException;
	
	void updateReference(List<E> entries, boolean mode) throws UpdateReferenceException;
	
}
