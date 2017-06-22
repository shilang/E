package com.cloud.erp.dao.common;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Bollen
 *
 * @param <T>
 */
public interface SingleEntry<T> {

	<E> List<E> findEntriesById(Integer pid, Class<E> entry);

	<E> boolean persistenceEntries(T master, Map<String, List<E>> entries);

	boolean deleteToUpdateEntries(Integer pid) throws Exception;
}
