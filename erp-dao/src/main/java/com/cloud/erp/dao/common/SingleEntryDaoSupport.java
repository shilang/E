package com.cloud.erp.dao.common;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author Bollen
 *
 * @param <T>
 */
@Repository("singleEntryDao")
public class SingleEntryDaoSupport<T> extends MultiEntryDaoSupport<T>{
	
	private static final String ENTRY_NAME = "entries";
	
	private String entries = ENTRY_NAME;
	
	public String getEntries() {
		return entries;
	}

	public void setEntries(String entries) {
		this.entries = entries;
	}

	public <E> List<E> findEntriesById(Class<T> clazz, Integer pid, Class<E> entry) {
		
	    List<E> list = super.findEntriesById(clazz, pid, entry, getEntries());
		return list;
	}

	public boolean deleteToUpdateEntries(Class<T> clazz, Integer pid, StatusFields statusFields) {
		
		return super.deleteToUpdateEntries(clazz, pid, statusFields, getEntries());
	}

}
