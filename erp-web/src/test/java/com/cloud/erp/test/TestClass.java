package com.cloud.erp.test;

public class TestClass implements TestInterface {

	private String name;
	
	private Integer id;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	@Override
	public void doSomething() {
		
		System.out.println("do something");
	}

}
