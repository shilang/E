<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>日志管理</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
var $dg;
var $grid;
$(function() {
	 $dg = $("#dg");
	 $grid=$dg.datagrid({
		url : "log/find.action",
		width : $(this).width() - 10,
		height : $(this).height() - 255 + 250,
		pagination:true,
		rownumbers:true,
		border:true,
		striped:true,
		singleSelect:true,
		columns : [ [ {field : 'date',title : '日期',width : parseInt($(this).width()*0.12)},
		              {field : 'name',title : '用户名称',width : parseInt($(this).width()*0.12)},
		              {field : 'module',title : '操作模块',width : parseInt($(this).width()*0.2)},
		              {field : 'content',title : '内容描述',width : parseInt($(this).width()*0.2)},
		              {field : 'machine',title : '机器名',width :parseInt($(this).width()*0.15)},
		              {field : 'ipAddr',title : 'IP地址',width :parseInt($(this).width()*0.15)},
		              ] ],toolbar:'#tb'
	});
	 
	//搜索框
	$("#searchbox").searchbox({
		 width: 250,
		 menu:"#mm", 
		 prompt :'模糊查询',
	    searcher:function(value,name){   
            $dg.datagrid('reload', $.erp.searcher(name,value)); 
	    }
	}); 
});

//删除
function delRows(){
	var row = $dg.datagrid('getSelected');
	if(row){
		parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
			if(r){
				$.post("log/delete.action",{logId:row.logId},function(rsp){
					if(rsp.status){
						var idx = $dg.datagrid('getRowIndex', row);
						$dg.datagrid('deleteRow', idx);
					}
					$.erp.submitSuccess(rsp.title, rsp.message);
				},"json").error(function(){
					$.erp.submitErr();
				});
			}
		});
	}else{
		$.erp.noSelectErr();
	}
}

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center', border:false" style="padding:5px;">
		  <div id="tb">
			<table>
				<tr>
					<td>
						<shiro:hasPermission name="logDel">
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="delRows();">删除</a>
						</shiro:hasPermission>
					</td>
					<td>
						<input id="searchbox" type="text"/>
					</td>
					<!--  <td style="padding-left:2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="tbsCompanySearch();">高级查询</a>
					</td>-->
				</tr>
			</table>
		</div>
		<div id="mm">
				<div name="date">日期</div>
				<div name="name">用户名称</div>
		</div>
		<table id="dg" title="日志管理"></table>
		</div>
  	</div>	
</body>
</html>