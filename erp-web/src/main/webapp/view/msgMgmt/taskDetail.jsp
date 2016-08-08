<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<style type="text/css">

	.dlgcontent #form .simple{
		font-size: 12px;
	}

</style>
<script type="text/javascript">
	$(function(){
		var auditModel = parent.pAuditModel;
		//request row data
		var row = undefined;
		var url = 'task/getEntity';
		var param = {businessClass:auditModel.businessClass,businessKey:auditModel.businessKey};
		$.when($.ajax({url:url,data:param})).done(function(data){
			row = data;
		}).done(function(){
			var $parentWindow = $('#amendPanel').panel({
				width: 'auto',
				height: 'auto',
				href: auditModel.path,
				border: false,
				onLoad: function(){
					if(row){
						
						if(init){
							init();
						}
						
						function fillData(){
							//load form data
							var f = $parentWindow.find("#form");
							if(f){
								f.form('load',row);
							}
						}
						
						var $deptId = $parentWindow.find("#departmentId");
						if($deptId){
							var deptIdDefer = $deptId.erpDept();
							$.when(deptIdDefer).done(function(){
								fillData();
							});
						}else{
							fillData();
						}
						
						//load entry data
						var entry = $parentWindow.find('table[id^=dg]');
						if(entry){
							entry.datagrid('reload',{interId:row.interId});
						}
					}
				}
			});
		});
	});
</script>
</head>
<body>
   <div id="amendPanel"></div>
</body>
</html>