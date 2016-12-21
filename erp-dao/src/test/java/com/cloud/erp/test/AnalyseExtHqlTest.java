package com.cloud.erp.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.cloud.erp.dao.MaterialAttrDao;

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
	
	public String analyseExtHqlWithRegex(String alias, String[] extHql){
		StringBuffer extHqlBuffer = new StringBuffer();
		String regex = "\\[(\\w+)\\]";
		Pattern pattern = Pattern.compile(regex);
		for(String e : extHql){
			Matcher matcher = pattern.matcher(e);
			StringBuffer sb = new StringBuffer();
			while(matcher.find()){
				matcher.appendReplacement(sb, alias + "." + matcher.group(1));
			}
			matcher.appendTail(sb);
			extHqlBuffer.append(" ");
			extHqlBuffer.append(sb.toString());
		}
		return extHqlBuffer.toString();
	}
	
	@Test
	public void testAnalyseExtHql(){
		String result = analyseExtHql("t", new String[]{"and type='A'", "and menu='B'", "and ( jj='Q' or tt='G')"});
		System.out.println(result);
	}
	
	@Test
	public void testAnalyseExtHqlWithRegex(){
		
		String input = "from SalesOrder where 1=1 and [status1]='A' group by [status2],[interId3] order by [interId4], [status5] @bollen";
		
		analyseExtHqlWithRegex("t",new String[]{"and [type]='A'", "and [menu]='B'"});
		
		
	}
}
