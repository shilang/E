package com.cloud.erp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.erp.service.SalesInvoiceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
public class ICSalesTest {

	@Autowired
	private SalesInvoiceService salesInvoiceService;
	
	@Test
	@Rollback(false)
	public void testICSales(){
		

	}
}
