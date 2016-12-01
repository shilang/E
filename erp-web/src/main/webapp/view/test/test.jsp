<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/euinc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	$(function(){
		$("#button").on('click', function(){
			var a = getFileExt($('#file').val());
			console.info(a);
		});
	});

</script>

</head>
<body>
	<input id="file" name="file" type="file" />
	<input id="button" type="button" value="button" />
</body>
</html>