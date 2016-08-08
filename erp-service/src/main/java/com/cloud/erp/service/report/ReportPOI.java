package com.cloud.erp.service.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloud.erp.entities.viewmodel.ReportField;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.POIUtil;
import com.cloud.erp.utils.Reflect;

public class ReportPOI {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportPOI.class);
	
	private static final int ROWNUM = 0;
	private static final int COLNUM = 0;
	private static final String STATUSFIELD = "result";
	private Map<String, String> reportEntries = new HashMap<String, String>();
	private Map<String, List<ReportField>> reportFieldsMap = 
			new HashMap<String, List<ReportField>>();
	
	private String sheetName;
	private Document doc;
	private Workbook wb;
	private int rowNum = ROWNUM;
	private int colNum = COLNUM;
	private Sheet sheet;
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public ReportPOI(String fileName) {
		doc = ReportXmlParser.readXml(fileName);
		wb = POIUtil.createWorkbook();
		sheetName = ReportXmlParser.getModuleName(doc);
		sheet = POIUtil.createSheet(wb, sheetName);
	}
	
	private void close(){
		try {
			wb.close();
		} catch (IOException e) {
			if(logger.isDebugEnabled()){
				logger.debug("Report POI close failure.");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void createCellTitle() {
		Node fieldNode = null;
		String fieldTitle = null;
		String fieldName = null;
		//create row
		int rownum = getRowNum();
		int colnum = getColNum();
		Row row = POIUtil.createRow(sheet, rownum);
		//master node
		Node masterNode = ReportXmlParser.getMasterNode(doc);
		String masterClass = ReportXmlParser.getMasterClass(masterNode);
		List masterFields = ReportXmlParser.getMasterFields(masterNode);
		List<ReportField> masterReportFields = new ArrayList<ReportField>();
		for (int i = 0; i < masterFields.size(); i++) {
			fieldNode = (Node) masterFields.get(i);
			fieldName = ReportXmlParser.getFieldText(fieldNode);
			fieldTitle = ReportXmlParser.getFieldTitle(fieldNode);
			POIUtil.createCell(wb, row, colnum, fieldTitle);
			ReportField reportField = new ReportField();
			reportField.setName(fieldName);
			reportField.setTitle(fieldTitle);
			reportField.setColumn(colnum);
			masterReportFields.add(reportField);
			colnum++;
		}
		reportFieldsMap.put(masterClass, masterReportFields);
		
		// entry node
		List entryNodes = ReportXmlParser.getEntryNodes(doc);
		for (int i = 0; i < entryNodes.size(); i++) {
			List<ReportField> entryReportFields = new ArrayList<ReportField>();
			Node entryNode = (Node) entryNodes.get(i);
			String entryName = ReportXmlParser.getNodeNameAttr(entryNode);
			String entryClass = ReportXmlParser.getNodeClassAttr(entryNode);
			List entryFields = ReportXmlParser.getEntryFields(entryNode);
			for (int j = 0; j < entryFields.size(); j++) {
				fieldNode = (Node) entryFields.get(j);
				fieldName = ReportXmlParser.getFieldText(fieldNode);
				fieldTitle = ReportXmlParser.getFieldTitle(fieldNode);
				POIUtil.createCell(wb, row, colnum, fieldTitle);
				ReportField reportField = new ReportField();
				reportField.setName(fieldName);
				reportField.setTitle(fieldTitle);
				reportField.setColumn(colnum);
				entryReportFields.add(reportField);
				colnum++;
			}
			reportEntries.put(entryClass, entryName);
			reportFieldsMap.put(entryClass, entryReportFields);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	private void fillData(List dataList){
		int rownum = getRowNum() + 1;
		for (Object businessObj : dataList){
			//create row
			Row row = POIUtil.createRow(sheet,rownum);
			
			// master field
			List<ReportField> masterReportFields = reportFieldsMap.get(businessObj.getClass().getName());
			for (ReportField rf : masterReportFields) {
				String field = rf.getName();
				Object value = Reflect.invokeGetMethod(businessObj,field);
				if (STATUSFIELD.equals(field)) {
					value = Commons.convertResult((int)value);
				}
				POIUtil.createCell(wb, row,  rf.getColumn(), value);
			}
			
			// entry field
			int entryRowNum = rownum;
			Row entryRow = null;
			for (String entryClass : reportEntries.keySet()) {
				List entries = (List) Reflect.invokeGetMethod(businessObj, reportEntries.get(entryClass));
				List<ReportField> entryReportFields = reportFieldsMap.get(entryClass);
				entryRowNum = rownum;
				entryRow = row;
				boolean append = false;
				for (int i = 0; i < entries.size(); i++) {
					if (append) {
						entryRow = POIUtil.createRow(sheet, entryRowNum);
					}
					Object entry = entries.get(i);
					for (ReportField rf : entryReportFields) {
						String field = rf.getName();
						Object value = Reflect.invokeGetMethod(entry, field);
						POIUtil.createCell(wb, entryRow, rf.getColumn(), value);
					}
					entryRowNum++;
					append = true;
				}
			}
			rownum = entryRowNum;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public String export(List list, OutputStream os){
		try {
			createCellTitle();
			fillData(list);
			wb.write(os);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("Report POI export failure!");
			}
		}finally{
			close();
		}
		return sheetName;
	}
}
