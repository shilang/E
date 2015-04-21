<%@ page language="java" pageEncoding="utf-8" %>

<% String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<link href="web-static/script/jquery-easyui-1.4.1/themes/metro/easyui.css" rel="stylesheet" type="text/css"/>
<link href="web-static/script/jquery-easyui-1.4.1/themes/icon.css" rel="stylesheet" type="text/css"/>
<link href="web-static/style/common.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="web-static/script/jquery-2.1.3.js"></script>
<script type="text/javascript" src="web-static/script/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="web-static/script/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="web-static/script/jqueryUtil.js"></script>

<style type="text/css">
	body{
		font-family:helvetica,tahoma,verdana,sans-serif;
		font-size: 13px;
		margin: 0px 0px 0px 0px;
		padding: 0px 0px 0px 0px;
	}
</style>