package com.cloud.erp.entities.datafilter;

public class FilterRule {

	private String field;
	private String value;
	private String op;
	private String type;

	public FilterRule() {
	}
	
	public FilterRule(String field, String value)
	{
		this(field, value, "");
	}

	public FilterRule(String field, String value, String op){
		this.field = field;
		this.value = value;
		this.op = op;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
