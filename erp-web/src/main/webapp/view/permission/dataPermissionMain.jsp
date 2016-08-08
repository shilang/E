<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>数据权限</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var entry;
	var $form;
	$(function(){
		$form = $("#form");
		$form.form({
			url: 'dataPermission/persist.action',
			onSubmit: submit,
			success: success
		});
		
		$dg = $("#dg");
		$dg.datagrid({
			url: 'dataPermission/find.action',
			width: 'auto',
			height: $(this).height(),
			collapsible:true,
			rownumbers: true,
			border: false,
			singleSelect: true,
			striped: true,
			nowrap: false,
			idField: 'id',
			columns: [[
			         {field:'dataView',title:'视图',width:parseInt($(this).width()*0.2)},
			         {field:'dataRule',title:'规则',width:parseInt($(this).width()*0.5),editor:{type:'textbox',options:{required:true}}}
			         ]],
			toolbar: '#tb',
			onDblClickRow: onDblClickRow
		});
		
		entry = new $.erp.entry($dg);
	});	
	
	function onDblClickRow(index, row){
		entry.onClickRow(index);
	}
	
	function submit(entryRows){
		entry.saveRows();
		return entry.submit(entryRows, $form);
	}
	
	function success(result){
		entry.success(result, process, true);
	}
	
	function process(){
		entry.acceptRows();
	}
	
	function updateDataPermission(){
		$form.submit();
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" >
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="updateDataPermission();">保存</a>
						</td>
					</tr>
				</table>
			</div>
			
			<table id="dg" title=""></table>
			
			<form id="form" method="post"></form>
		</div>
	</div>
</body>
</html>