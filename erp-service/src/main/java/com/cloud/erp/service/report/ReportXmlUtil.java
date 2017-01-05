package com.cloud.erp.service.report;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportXmlUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportXmlUtil.class);
	
	private static void setNamespace(SAXReader reader, String namespace){
		if(null != namespace && !"".equals(namespace)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("ns", namespace);
			reader.getDocumentFactory().setXPathNamespaceURIs(map);
		}
	}
	
	public static Document readXml(SAXReader reader, InputStream is){
		return readXml(reader, is, null);
	}

	public static Document readXml(SAXReader reader, String fileName){
		return readXml(reader, fileName, null);
	}
	
	public static Document readXml(SAXReader reader, String fileName, String namespace){
		InputStream is = ReportXmlUtil.class.getResourceAsStream("/reports/"+fileName);
		return readXml(reader, is, namespace);
	}
	
	public static Document readXml(SAXReader reader, InputStream is, String namespace){
		setNamespace(reader, namespace);
		Document document = null;
		try {
			document = reader.read(is);
		} catch (DocumentException e) {
			if(logger.isDebugEnabled()){
				logger.debug("read xml error.");
			}
		}
		return document;
	}
	
	public static SAXReader getReader(){
		return new SAXReader();
	}
	
}
