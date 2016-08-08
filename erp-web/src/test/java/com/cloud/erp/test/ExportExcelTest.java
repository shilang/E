package com.cloud.erp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cloud.erp.service.SalesPriceListService;
import com.cloud.erp.service.report.ReportPOI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ExportExcelTest {
	
	@Resource
	private SalesPriceListService salesPriceListServiceImpl;
	
	@Test
	public void test() {
		String fileName = "salesPriceListReport.xml";
		ReportPOI poi = new ReportPOI(fileName);
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("MYWBTest.xls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		poi.export(salesPriceListServiceImpl.findAll(null, null), os);
	}

}
