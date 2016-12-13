package com.cloud.erp.dao.common;

import java.util.Date;
import java.util.List;

import com.cloud.erp.entities.type.OperType;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.Reflect;

public class UpdateStatus {
	
	private static final String DELETE_STATUS = Constants.PERSISTENCE_DELETE_STATUS;
	private static final String PERSISTENCE_STATUS = Constants.PERSISTENCE_STATUS;
	
	public Integer getCurrUserId() {
		return Commons.getCurrentUser().getUserId();
	}

	public Date getCurrDate() {
		return new Date();
	}
	
	public Object updateEntityInfo(Object master, StatusFields statusFields, OperType operType) {

		if(statusFields == null){
			return master;
		}
		
		try {
			if (OperType.create.equals(operType)) {
				Reflect.invokeSetMethod(master, statusFields.getCreater(), getCurrUserId());
				Reflect.invokeSetMethod(master, statusFields.getCreated(), getCurrDate());
				Reflect.invokeSetMethod(master, statusFields.getStatus(), PERSISTENCE_STATUS);
			}

			if (OperType.delete.equals(operType)) {
				Reflect.invokeSetMethod(master, statusFields.getStatus(), DELETE_STATUS);
			}

			Reflect.invokeSetMethod(master, statusFields.getModifier(), getCurrUserId());
			Reflect.invokeSetMethod(master, statusFields.getLastmod(), getCurrDate());
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}

		return master;
	}

	public <E> List<E> updateEntityInfo(Object master, List<E> entries, StatusFields statusFields, 
			OperType operType){
		
		if(statusFields == null){
			return entries;
		}

		try {
			
			if (null != entries ) {

				for (Object o : entries) {
					Reflect.invokeSetMethod(o, statusFields.getMaster(), master);
					
					if (OperType.create.equals(operType)) {
						Reflect.invokeSetMethod(o, statusFields.getCreater(), getCurrUserId());
						Reflect.invokeSetMethod(o, statusFields.getCreated(), getCurrDate());
						Reflect.invokeSetMethod(o, statusFields.getStatus(), PERSISTENCE_STATUS);
					}

					if (OperType.delete.equals(operType)) {
						Reflect.invokeSetMethod(o, statusFields.getStatus(), DELETE_STATUS);
					}

					Reflect.invokeSetMethod(o, statusFields.getModifier(), getCurrUserId());
					Reflect.invokeSetMethod(o, statusFields.getLastmod(), getCurrDate());
				}
				
				return entries;

			}
		} catch (Exception ex) {
			return null;
		}

		return null;
	}
	
}
