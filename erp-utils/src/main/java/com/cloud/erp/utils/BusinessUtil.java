package com.cloud.erp.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BusinessUtil {

	private static List<String> attrs = new ArrayList<String>();
	
	static{
		attrs.add("entries");
		attrs.add("schemes");
	}
	
	public static void loadCollectionAttr(Object object){
		for(String attr : attrs){
			if(ObjectUtil.isExistField(object, attr)){
				Object o = Reflect.invokeGetMethod(object, attr);
				if(o instanceof Collection<?>){
					Collection<?> c = (Collection<?>) o;
					c.iterator();
				}
			}
		}
	}
}
