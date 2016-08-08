package com.cloud.erp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cloud.erp.dao.common.DataPermissionAssign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DataPermissionTest {
   
	@Autowired
	private DataPermissionAssign d;
	
	@Test
	public void test(){
	  String dataRule =	d.translate("SalesPriceListDaoImpl");
	  
	  System.out.println(dataRule);
	}
}
