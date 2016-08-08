package com.cloud.erp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeCast {
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> toListGenericForList(Class<E> clazz, List<Object> list){
		
		List<E> newList = new ArrayList<E>();

		if(null != list){
			for(Object obj : list){
				newList.add((E)obj);
			}
		}
		return newList;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> Map<String, List<E>> toListGenericForMap(Class<E> clazz, Map<String, List<Object>> map){
		Map<String, List<E>> newMap = new HashMap<String, List<E>>();
		for(String key : map.keySet()){
			List<Object> objs = map.get(key);
			List<E> list = null;
			if(null != objs){
				list = new ArrayList<E>();
				for(Object obj : objs){
					list.add((E)obj);
				}
			}
			newMap.put(key, list);
		}
		return newMap;
	}
	
	public static <E> Map<String, List<Object>> toListObjectForMap(Class<E> clazz, Map<String, List<E>> map){
		Map<String, List<Object>> newMap = new HashMap<String, List<Object>>();
		for(String key : map.keySet()){
			List<E> objs = map.get(key);
			List<Object> list = null;
			if(null != objs){
				list = new ArrayList<Object>();
				for(E obj : objs){
					list.add(obj);
				}
			}
			newMap.put(key, list);
		}
		return newMap;
	}
	
}
