package com.cloud.erp.test;

import org.junit.Test;

public class AnalyseExtHqlTest {

	private String analyseExtHql(String alias, String[] extHql){
		String extHqlString = "";
		if(null != extHql && extHql.length > 0){
			for(String e : extHql){
				String[] tokens = e.split(" ");
				for(int i = 0; i < tokens.length; i++){
					if("and".equalsIgnoreCase(tokens[i]) || "or".equalsIgnoreCase(tokens[i])){
						if("(".equals(tokens[i+1])){
							tokens[i+2] = alias + "." + tokens[i+2];
						}else {
							if(tokens[i+1].startsWith("(")){
								tokens[i+1] = "(" + alias + "." + tokens[i+1].substring(1);
							}else {
								tokens[i+1] = alias + "." + tokens[i+1];
							}
						}
					}
					extHqlString += tokens[i] + " ";
				}
			}
		}
		return extHqlString;
	}
	
	@Test
	public void testAnalyseExtHql(){
		String result = analyseExtHql("t", new String[]{"and type='A'", "and menu='B'", "and ( jj='Q' or tt='G')"});
		System.out.println(result);
	}
}
