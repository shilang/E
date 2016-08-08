package com.cloud.erp.entities.sys;

import java.lang.reflect.Method;

public class RObject {

	private Object obj;

	private Method method;

	private Object[] args;

	public RObject() {
		super();
	}

	public RObject(Object obj, Method method, Object[] args) {
		super();
		this.obj = obj;
		this.method = method;
		this.args = args;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
