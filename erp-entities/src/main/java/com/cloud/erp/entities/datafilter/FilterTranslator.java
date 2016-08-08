package com.cloud.erp.entities.datafilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.sys.RObject;

/**
 * 将检索规则翻译成 where sql 语句， 并生成相应的参数列表 如果遇到{CurrentUserID}这种， 翻译成对应的参数
 * 
 * @author bollen
 *
 */
public class FilterTranslator {

	// private String leftToken = "[";
	private String leftToken = "";
	public static String paramPrefixToken = "_";
	private String rightToken = "";
	// private String rightToken = "]";
	private String groupLeftToken = "(";
	private String groupRightToken = ")";
	private String likeToken = "%";

	private int paramCounter = 0;

	private FilterGroup group;
	private String commandText;
	private List<FilterParam> params;

	public FilterGroup getGroup() {
		return group;
	}

	public void setGroup(FilterGroup group) {
		this.group = group;
	}

	public String getCommandText() {
		return commandText;
	}

	public List<FilterParam> getParams() {
		return params;
	}

	public FilterTranslator() {
		this(null);
	}

	public FilterTranslator(FilterGroup group) {
		this.group = group;
		this.params = new ArrayList<FilterParam>();
	}

	public void Translate() {
		this.commandText = TranslateGroup(this.group);
	}

	public String TranslateGroup(FilterGroup group) {
		StringBuilder bulider = new StringBuilder();
		if (group == null)
			return " 1=1 ";
		boolean appended = false;
		bulider.append(groupLeftToken);
		if (group.getRules() != null) {
			for (FilterRule rule : group.getRules()) {
				if (appended)
					bulider.append(GetOperatorQueryText(group.getOp()));
				bulider.append(TranslateRule(rule));
				appended = true;
			}
		}
		if (group.getGroups() != null) {
			for (FilterGroup subgroup : group.getGroups()) {
				if (appended)
					bulider.append(GetOperatorQueryText(group.getOp()));
				bulider.append(TranslateGroup(subgroup));
				appended = true;
			}
		}
		bulider.append(groupRightToken);
		if (appended == false)
			return " 1=1 ";
		return bulider.toString();
	}

	public static void RegCurrentParmMatch(String key, RObject value) {
		if (!currentParmMatch.containsKey(key))
			currentParmMatch.put(key, value);
	}

	// 匹配当前用户信息，都是int类型
	// 对于CurrentRoleID，只返回第一个角色
	private static Map<String, RObject> currentParmMatch = new HashMap<String, RObject>();

	public String TranslateRule(FilterRule rule) {
		
		if (rule == null) return " 1=1 ";
		
		StringBuilder builder = new StringBuilder();
		
		String pCurrField = "";
		String pCurrValue = "";

		String orgField = rule.getField();
		String orgValue = rule.getValue();
		String op = rule.getOp().toLowerCase();
		String type = rule.getType();
		

		if ("in".equals(op) || "notin".equals(op)) {

			if (currentParmMatch.containsKey(orgField)) {
				RObject field = currentParmMatch.get(orgField);
				pCurrField = paramPrefixToken + CreateFilterParam(field, type);
			} else {
				pCurrField = leftToken + orgField + rightToken;
			}

			String[] values = orgValue.toString().split(",");
			boolean appended = false;

			for (String value : values) {
				if (appended)
					builder.append(" or ");

				builder.append("(");

				builder.append(value);

				builder.append(GetOperatorQueryText(op));

				builder.append(pCurrField);

				builder.append(")");

				appended = true;

			}

		} else if ("isnull".equals(op) || "isnotnull".equals(op)) {

			if (currentParmMatch.containsKey(orgField)) {
				RObject field = currentParmMatch.get(orgField);
				pCurrField = paramPrefixToken + CreateFilterParam(field, type);
			} else {
				pCurrField = leftToken + orgField + rightToken;
			}

			builder.append(pCurrField);
			builder.append(GetOperatorQueryText(op));

		} else {
			// 字段包含变量
			if (currentParmMatch.containsKey(orgField)) {
				RObject field = currentParmMatch.get(orgField);
				pCurrField = paramPrefixToken + CreateFilterParam(field, type);

			} else {
				pCurrField = leftToken + orgField + rightToken;
			}

			builder.append(pCurrField);
			builder.append(GetOperatorQueryText(op));

			// 值包含变量
			if (currentParmMatch.containsKey(orgValue)) {
				RObject value = currentParmMatch.get(orgValue);
				pCurrValue = paramPrefixToken + CreateFilterParam(value, type);
			} else {
				pCurrValue = orgValue;
			}

			if ("like".equals(op)) {
				pCurrValue = this.likeToken + pCurrValue + this.likeToken;
			}

			builder.append(pCurrValue);
		}

		return builder.toString();
	}

	private String CreateFilterParam(Object value, String type) {
		String paramName = "p" + ++paramCounter;
		Object val = value;

		if (null != type) {
			if (type.equals("int") || type.equals("digits")) {
				// val = DataHelper.ConvertValue(typeof(int), val);
			} else if (type.equals("float") || type.equals("number")) {
				// val = DataHelper.ConvertValue(typeof(decimal),val);
			}
		}

		FilterParam param = new FilterParam(paramName, val);
		this.params.add(param);
		return paramName;
	}

	@Override
	public String toString() {
		StringBuilder bulider = new StringBuilder();
		bulider.append("CommandText:");
		bulider.append(this.commandText);
		// bulider.appendLine();
		// bulider.appendLine("Parms:");
		for (FilterParam param : this.params) {
			bulider.append(String.format("{0}:{1}", param.getName(),
					param.getValue()));
		}
		return bulider.toString();
	}

	public static String GetOperatorQueryText(String op) {
		switch (op.toLowerCase()) {
		case "add":
			return " + ";
		case "bitwiseand":
			return " & ";
		case "bitwisenot":
			return " ~ ";
		case "bitwiseor":
			return " | ";
		case "bitwisexor":
			return " ^ ";
		case "divide":
			return " / ";
		case "equal":
			return " = ";
		case "greater":
			return " > ";
		case "greaterorequal":
			return " >= ";
		case "isnull":
			return " is null ";
		case "isnotnull":
			return " is not null ";
		case "less":
			return " < ";
		case "lessorequal":
			return " <= ";
		case "like":
			return " like ";
		case "startwith":
			return " like ";
		case "endwith":
			return " like ";
		case "modulo":
			return " % ";
		case "multiply":
			return " * ";
		case "notequal":
			return " <> ";
		case "subtract":
			return " - ";
		case "and":
			return " and ";
		case "or":
			return " or ";
		case "in":
			return " in ";
		case "notin":
			return " not in ";
		default:
			return " = ";
		}
	}
}
