<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Expires" content="0">

<script type="text/javascript" src="web-static/script/jquery-1.11.3.min.js"></script>
<link href="web-static/style/common.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

<!--[if lt IE 9]>
<script type="text/javascript" src="web-static/script/html5shiv.js"></script>
<![endif]-->

<script type="text/javascript" src="web-static/script/jquery.cookie.js"></script> 