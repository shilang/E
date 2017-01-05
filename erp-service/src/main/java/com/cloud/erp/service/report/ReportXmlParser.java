package com.cloud.erp.service.report;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;


public class ReportXmlParser {
	
	private static final String NAMESPACE = "http://www.orbitatech.com/schema/module";

	public static Document readXml(String fileName){
		return ReportXmlUtil.readXml(ReportXmlUtil.getReader(), fileName, NAMESPACE);
	}
	
	private static String getXpathForModuleName(){
		return "/ns:module/ns:name";
	}
	
	private static String getXpathForMasterNode(){
		return "/ns:module/ns:master";
	}
	
	private static String getXpathForEntryNodes(){
		return "/ns:module/ns:entries/ns:entry";
	}
	
	private static String getXpathForFieldNodes(){
		return "ns:fields/ns:field";
	}
	
	private static String getXpathForNameAttr(){
		return "@name";
	}
	
	private static String getXpathForClassAttr(){
		return "@class";
	}
	
	private static String getXpathForTitleAttr(){
		return "@title";
	}
	
	private static String getNodeText(Node node){
		return node.getText();
	}
	
	@SuppressWarnings("rawtypes")
	private static List getFieldNodes(Node node){
		return node.selectNodes(getXpathForFieldNodes());
	}
	
	public static String getModuleName(Document document){
		return document.selectSingleNode(getXpathForModuleName()).getText();
	}
	
	public static String getFieldText(Node fieldNode){
		return getNodeText(fieldNode);
	}
	
	public static String getFieldTitle(Node fieldNode){
		return fieldNode.selectSingleNode(getXpathForTitleAttr()).getText();
	}
	
	public static String getNodeNameAttr(Node node){
		return node.selectSingleNode(getXpathForNameAttr()).getText();
	}
	
	public static String getNodeClassAttr(Node node){
		return node.selectSingleNode(getXpathForClassAttr()).getText();
	}
	
	public static Node getMasterNode(Document document){
		return document.selectSingleNode(getXpathForMasterNode());
	}
	
	public static String getMasterClass(Node masterNode){
		return getNodeClassAttr(masterNode);
	}
	
	public static String getMasterName(Node masterNode){
		return getNodeNameAttr(masterNode);
	}
	
	@SuppressWarnings("rawtypes")
	public static List getMasterFields(Node masterNode){
		return getFieldNodes(masterNode);
	}
	
	@SuppressWarnings("rawtypes")
	public static List getEntryNodes(Document document){
		return document.selectNodes(getXpathForEntryNodes());
	}
	
	@SuppressWarnings("rawtypes")
	public static List getEntryFields(Node entryNode){
		return getFieldNodes(entryNode);
	}
	
	
}
