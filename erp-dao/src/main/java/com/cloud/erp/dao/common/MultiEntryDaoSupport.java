package com.cloud.erp.dao.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.entities.type.OperType;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.Reflect;

/**
 * 
 * @author Bollen
 *
 * @param <T>
 */
@Repository("multiEntryDao")
public class MultiEntryDaoSupport<T> extends UpdateStatus {

	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	@SuppressWarnings("unchecked")
	public <E> List<E> findEntriesById(Class<T> clazz,
			Integer pid, Class<E> eclazz, String entry) {
		
		if(null == pid || null == entry){
			return new ArrayList<E>(0);
		}
		
		Object master = baseDao.get(clazz, pid);
	    List<E> list = (List<E>) Reflect.invokeGetMethod(master, entry);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private <E> void addEntries(List<E> entries){
		if(null != entries && !entries.isEmpty()){
			for(E entry : entries){
				baseDao.save(entry);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private <E> void updEntries(List<E> entries){
		if(null != entries && !entries.isEmpty()){
			for(E entry : entries){
				baseDao.update(entry);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private <E> void delEntries(List<E> entries){
		if(null != entries && !entries.isEmpty()){
			for(E entry : entries){
				//baseDao.deleteToUpdate(entry);
				baseDao.delete(entry);
			}
		}
	}
	
	private <E> void persistenceEntry(Object master, Map<String, List<E>> entries, StatusFields statusFields){
		this.addEntries(updateEntityInfo(master,entries.get(Constants.ENTRY_LIST_TYPE_ADD), statusFields, OperType.create));
	    this.updEntries(updateEntityInfo(master,entries.get(Constants.ENTRY_LIST_TYPE_UPD), statusFields, OperType.update));
		//this.delEntries(updateEntityInfo(master,entries.get(Constants.ENTRY_LIST_TYPE_DEL), statusFields, OperType.delete));
		this.delEntries(entries.get(Constants.ENTRY_LIST_TYPE_DEL));
	}

	public <E> boolean persistenceEntries(T master, Map<String, List<E>> entries, StatusFields statusFields) {
		persistenceEntry(master, entries, statusFields);
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteToUpdateEntries(Class<T> clazz, Integer pid, StatusFields statusFields, String... entry) {
		
		if(null == pid || null == entry){
			return false;
		}
		
		Object master = baseDao.get(clazz, pid);
		
		for(int i = 0; i < entry.length; i++)
		{
			List<Object> list = updateEntityInfo(master, 
					(List<Object>) Reflect.invokeGetMethod(master, entry[i]), statusFields, OperType.delete);
			for(Object o : list)
			{
				baseDao.deleteToUpdate(o);
			}	
		}
		
		return true;
	}
	
}
