<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>欢迎</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		iniMenu();
	});
	
	function iniMenu(){
		var $ma=$("#menuAccordion");
		$ma.accordion({animate:true,fit:true,border:false});
		$.post("loginAction!findAllFunctionList.action", {username:'1'}, function(rsp){
			$.each(rsp,function(i,e){
				var menulist ="<div class=\"well well-small\">";
				if(e.child && e.child.length>0){
					$.each(e.child,function(ci,ce){
						var effort=ce.name+"||"+ce.iconCls+"||"+ce.url;
						menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+ce.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+ce.name+"</a><br/>";
					});
				}
				menulist+="</div>";
				$ma.accordion('add', {
		            title: e.name,
		            content: menulist,
					border:false,
		            iconCls: e.iconCls,
		            selected: false
		        });
			});
		},"JSON").error(function(){
			$.messager.alert("提示","获取菜单出错，请重新登录!");
		});
	}
</script>

<style type="text/css">

	#menuAccordion a.l-btn span span.l-btn-text {
		text-align: left;
		width: 126px;
		padding: 0 0 0 15px;
	}
	
	#menuAccordion a.l-btn span span.l-btn-icon{
	    background-position: left center;
	    left: 15px;
	}

	#menuAccordion .panel-body {
		padding:5px;
	}
	
	#menuAccordion span:focus{
		outline: none;
	}
</style>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 40px; background: #EEE; padding: 10px; overflow: hidden;" href="layout/north.jsp"></div>
	<div data-options="region:'west',split:true,title:'主要菜单'" style="width: 200px;">
		<div id="menuAccordion"></div>
	</div>
	<div data-options="region:'south',border:false" style="height: 25px; background: #EEE; padding: 5px;" href="layout/south.jsp"></div>
	<div data-options="region:'center',plain:true,title:'欢迎使用ERP'" style="overflow: hidden;" href="layout/center.jsp"></div>
</body>
</html>