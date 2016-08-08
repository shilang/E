package com.cloud.erp.dao.common;

import java.util.List;
import java.util.Map;

public interface MultiEntry<T> {

	<E> List<E> findEntriesById(Integer pid, Class<E> clazz);

	<E> boolean persistenceEntries(T master, Map<String, List<E>> entries);

	boolean deleteToUpdateEntries(Integer pid);
}
