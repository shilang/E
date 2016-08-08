package com.cloud.erp.test;

import org.junit.Test;

public class InstanceOfTest {

	@Test
	public void test(){
		
		Object v = 1.0;
		
		boolean result = v instanceof Short;
		
		System.out.println(result);
		
	}
}
