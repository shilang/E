<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>欧比特云ERP</title>
<jsp:include page="euinc.jsp"></jsp:include>
<script type="text/javascript" src="web-static/script/menu.js"></script>
<script type="text/javascript">
	$(function(){
		$('body').load('layout/main.jsp',null, function(){
			$.parser.parse();
			setTimeout(function(){
				initMenu();
			},0);
		});
	});
</script>
</head>
<body>

</body>
</html>