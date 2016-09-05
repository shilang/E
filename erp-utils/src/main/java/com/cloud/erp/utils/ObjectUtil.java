package com.cloud.erp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

public class ObjectUtil {

	public static Object deepClone(Object object) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(object);
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return oi.readObject();
	}
	
	public static boolean isExistField(Object obj, String fieldName){
		Field[] fields = obj.getClass().getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			if(fields[i].getName().equals(fieldName)){
				return true;
			}
		}
		return false;
	}
}
