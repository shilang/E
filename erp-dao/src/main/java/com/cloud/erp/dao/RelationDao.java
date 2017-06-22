package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.table.Relation;

/**
 * 
 * @author Bollen
 *
 */
public interface RelationDao {

	List<Relation> findAll();
	
	Relation findByModuleClass(String moduleClass);
	
	Relation findById(Integer id);
	
}
