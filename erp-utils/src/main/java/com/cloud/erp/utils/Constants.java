/**
 * @Title:  Constants.java
 * @Package:  com.cloud.erp.utils
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  下午3:41:11
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.utils;


/**
 * @ClassName  Constants
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  下午3:41:11
 *
 */
public class Constants {
	
	public static final String UPLOAD_PATH = "/upload";
	public static final String LOGIN_SESSION_DATANAME = "users";
	public static final String LOGIN_URL = "login";
	public static final String CAPTCHA = "captcha";
	public static final String CAPTCHA_ERR = "验证吗错误";
	public static final String LOGIN_SUCCESS_URL = "index";
	public static final String LOGIN_LOGIN_OUT_URL = "loginout";
	public static final String LOGIN_MSG = "loginMsg";
	public static final String USERNAME_IS_NULL = "用户名为空!";
	public static final String LOGIN_IS_EXIST = "该用户已登录!";
	public static final String UNKNOWN_SESSION_EXCEPTION = "异常会话!";
	public static final String UNKNOWN_ACCOUNT_EXCEPTION = "账号错误!";
	public static final String INCORRECT_CREDENTIALS_EXCEPTION = "密码错误!";
	public static final String LOCKED_ACCOUNT_EXCEPTION = "账号已被锁定，请与系统管理员联系!";
	public static final String INCORRECT_CAPTCHA_EXCEPTION = "验证码错误!";
	public static final String AUTHENTICATION_EXCEPTION = "您没有授权!";
	public static final String UNKNOWN_EXCEPTION = "出现未知异常,请与系统管理员联系!";
	public static final String TREE_GRID_ADD_STATUS = "add";
	public static final String POST_DATA_SUCCESS = "数据更新成功!";
	public static final String POST_DATA_FAIL = "提交失败了!";
	public static final String GET_SQL_LIKE = "%";
	public static final String IS_FUNCTION = "F";
	public static final String PERSISTENCE_STATUS = "A";  //Action
	public static final String PERSISTENCE_DELETE_STATUS = "I";  //Inactive
	public static final String SYSTEM_ADMINISTRATOR = "admin";
	public static final String NULL_STRING = "";
	public static final String IS_DOT = ".";
	public static final String HQL_LIKE = "like";
	public static final String HQL_ASC = "asc";
	public static final String HQL_DESC = "desc";
	public static final String SEARCH_PARAM_TYPE_SORT = "s0";
	public static final String SEARCH_PARAM_TYPE_SIMPLE = "s1";
	public static final String SEARCH_PARAM_TYPE_HIGHGRADE = "s2";
	public static final String SEARCH_PARAM_SEP = ":";
	public static final String TEXT_TYPE_PLAIN = "text/plain";
	public static final String TEXT_TYPE_HTML = "text/html";
	public static final String FUNCTION_TYPE_O = "O";
	public static final String TREE_STATUS_OPEN = "open";
	public static final String TREE_STATUS_CLOSED = "closed";
	public static final String IS_EXT_SUBMENU = " 或可能包含菜单!";
	public static final String IS_EXT_SUBITEM = " 可能包含子项目!";
	public static final String SHIRO_USER = "shiroUser";
	public static final String LOGS_INSERT = "insert:";
	public static final String LOGS_INSERT_TEXT = "插入:";
	public static final String LOGS_INSERT_NAME = "insertLogs";
	public static final String LOGS_UPDATE = "update:";
	public static final String LOGS_UPDATE_TEXT = "更新:";
	public static final String LOGS_UPDATE_NAME = "updateLogs";
	public static final String LOGS_DELETE = "delete:";
	public static final String LOGS_DELETE_TEXT = "删除:";
	public static final String LOGS_DELETE_NAME = "deleteLogs";
	public static final String LOGS_TB_NAME = "Log";
	public static final String FILE_SUFFIX_SQL = ".sql";
	public static final String FILE_SUFFIX_ZIP = ".zip";
	public static final String NUMBER_TITLE = "BillNo";
	public static final int NUMBER_SALES_PRICE_LIST = 10;
	public static final int NUMBER_SALES_CONTRACT = 11;
	public static final int NUMBER_SALES_ORDER = 12;
	public static final int NUMBER_SALES_OUT_STOCK_NOTICE = 13;
	public static final int NUMBER_SALES_RETURN_GOODS_NOTICE = 14;
	public static final int NUMBER_SALES_OUT_STOCK = 15;
	public static final int NUMBER_SALES_INVOICE = 16;
	public static final int NUMBER_REC_PROCEEDS = 17;
	public static final int RESULT_NONE = 0;   //单据状态， 与jquery.erp.js 中配置的状态保持一致.
	public static final int RESULT_CHECK_PENDING = 1; //待审核
	public static final int RESULT_CHECK_OK = 2;  //已审核
	public static final int RESULT_CHECK_CHANGE = 3; //修改审核
	public static final String RESULT_NONE_DESC = "";   //单据状态， 与jquery.erp.js 中配置的状态保持一致.
	public static final String RESULT_CHECK_PENDING_DESC = "待审核"; //待审核
	public static final String RESULT_CHECK_OK_DESC = "已审核";  //已审核
	public static final String RESULT_CHECK_CHANGE_DESC = "修改审核"; //修改审核
	public static final String MESSAGE_CHECK_SUCCESS = "审核成功!";
	public static final String MESSAGE_CHECK_FAIL = "审核失败，可能已审核!";
	public static final int SALES_OUT_STOCK_TYPE_OUT = 1; //发货通知单
	public static final int SALES_OUT_STOCK_TYPE_IN = 2; //退货通知单
	public static final String ENTRY_LIST_TYPE_ADD = "addList";
	public static final String ENTRY_LIST_TYPE_UPD = "updList";
	public static final String ENTRY_LIST_TYPE_DEL = "delList";
	public static final String SHARE_ENTRY_TYPE_SALES_CONTRACT = "contract";
	public static final String SHARE_ENTRY_TYPE_SALES_PRICELIST = "pricelist";
	public static final String SHARE_ENTRY_TYPE_SALES_ORDER = "order";
	public static final String SHARE_ENTRY_TYPE_SALES_OUTNOTICE = "outnotice";
	public static final String SHARE_ENTRY_TYPE_SALES_OUTSTOCK = "outstock";
	public static final String SHARE_ENTRY_TYPE_SALES_INVOICE = "invoice";
	public static final String AUDIT_MODEL = "auditModel";
	public static final String AUDIT_MODEL_SUBPROCESS = "auditModelSubprocess";
	public static final String BUSINESS_TYPE_COMMIT = "commit";
	public static final String BUSINESS_TYPE_CHECK = "check";
	public static final String PROCESS_DEF_KEY_SALES_PRICELIST_PROCESS = "sales-pricelist-process";
	public static final String PROCESS_DEF_KEY_SALES_PRICELIST_PROCESS_APPLY = "sales-pricelist-process-apply";
	public static final String PROCESS_DEF_KEY_SALES_CONTRACT_PROCESS = "sales-contract-process";
	public static final String PROCESS_DEF_KEY_SALES_CONTRACT_PROCESS_APPLY = "sales-contract-process-apply";
	public static final String PROCESS_DEF_KEY_SALES_ORDER_PROCESS = "sales-order-process";
	public static final String PROCESS_DEF_KEY_SALES_ORDER_PROCESS_APPLY = "sales-order-process-apply";
	public static final String PROCESS_DEF_KEY_SALES_OUTSTOCK_NOTICE_PROCESS = "sales-outstock-notice-process";
	public static final String PROCESS_DEF_KEY_SALES_OUTSTOCK_NOTICE_PROCESS_APPLY = "sales-outstock-notice-process-apply";
	public static final String PROCESS_DEF_KEY_SALES_RETURNGOODS_NOTICE_PROCESS = "sales-returngoods-notice-process";
	public static final String PROCESS_DEF_KEY_SALES_RETURNGOODS_NOTICE_PROCESS_APPLY = "sales-returngoods-notice-process-apply";
	public static final String PROCESS_DEF_KEY_SALES_OUTSTOCK_PROCESS = "sales-outstock-process";
	public static final String PROCESS_DEF_KEY_SALES_OUTSTOCK_PROCESS_APPLY = "sales-outstock-process-apply";
	public static final String PROCESS_DEF_KEY_SALES_INVOICE_PROCESS = "sales-invoice-process";
	public static final String PROCESS_DEF_KEY_SALES_INVOICE_PROCESS_APPLY = "sales-invoice-process-apply";
	public static final String PROCESS_DEF_KEY_PROCEEDS_PROCESS = "proceeds-process";
	public static final String PROCESS_DEF_KEY_PROCEEDS_PROCESS_APPLY = "proceeds-apply";
	
}
