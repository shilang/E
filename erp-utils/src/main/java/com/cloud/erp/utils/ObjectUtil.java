package com.cloud.erp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
	
	/**
	 * copyNonNullOrEmptyProperties
	 * @param dest
	 * @param orig
	 */
	public static void copyNonNullOrEmptyProperties(Object dest, Object orig){
		
		if(dest == null){
			throw new IllegalArgumentException("No destination bean specified");
		}
		if(orig == null){
			throw new IllegalArgumentException("No origin bean specified");
		}
		if(dest.getClass() != orig.getClass()){
			throw new IllegalArgumentException("difference object error!");
		}
		
		Field[] fields = orig.getClass().getDeclaredFields();
		for(Field field : fields){
			 Type type = field.getGenericType();
			if(type instanceof ParameterizedType){
				continue;
			}
			String name = field.getName();
			Object value = Reflect.invokeGetMethod(orig, name);
			if(value != null && !"".equals(value)){
				Reflect.invokeSetMethod(dest, name, value);
			}
		}
		
	}
}
