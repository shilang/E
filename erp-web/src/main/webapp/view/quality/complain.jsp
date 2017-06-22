<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>品质投诉</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var _width = $(this).width();
	$(function(){
		$dg = $('#dg');
		$dg.datagrid({
			url:'',
			width: _width,
			height: $(this).height(),
			collapsible: true,
			pageSize: 20,
			pagination: true,
			pagePosition: 'top',
			rownumbers: true,
			fitColumns: true,
			striped: true,
			border: false,
			singleSelect: false,
			idField: 'interId',
			columns: [[
				{field:'interId',title:'',width:_width*0.05}, 
				{field:'interId',title:'',width:_width*0.05}, 
				{field:'interId',title:'',width:_width*0.05}, 
				{field:'interId',title:'',width:_width*0.05}, 
				{field:'interId',title:'',width:_width*0.05}
			]]
		}).datagrid('getPager').pagination({
			buttons: '#tb'
		});
	});
	
	function addComplainDlg(){
		
		function closeWindow(){
			parent.$.modalDialog.handler.dialog('destroy');
			parent.$.modalDialog.handler = undefined;
		}
		
		parent.$.modalDialog({
			title:'添加品质投诉',
			iconCls: 'icon-add',
			width: 800,
			height: 700,
			href: 'view/quality/complainEditDlg.jsp',
			onLoad: function(){
				
			},
			buttons: [{
				text: '提交',
				iconCls: 'icon-ok',
				width: 80,
				handler: function(){
					
				}
			},{
				text: '退回',
				iconCls: 'icon-undo',
				width: 80,
				handler: function(){
					
				}
			},{
				text: '取消',
				iconCls: 'icon-cancel',
				width: 80,
				handler: function(){
					closeWindow();
				}
			}]
		});
		
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false">
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addComplainDlg();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="addComplainDlg();">修改</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="addComplainDlg();">删除</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-audit',plain:true" onclick="">处理</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<table id="dg" title=""></table>
	</div>
</body>
</html>