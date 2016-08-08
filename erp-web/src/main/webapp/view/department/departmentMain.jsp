<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>部门</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.treegrid({
			url:'department/findById.action',
			width: $(this).width(),
			height: $(this).height(),
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: false,
			striped: true,
			border: false,
			idField: 'departmentId',
			treeField: 'name',
			frozenColumns: [[
				{field: 'name', title: '名称', width:parseInt($(this).width() * 0.1),
					formatter:function(value){
						return '<span style="color:purple">' + value + '</span>';
					}}
			]],
			columns:[[
			         {field: 'number', title:'代码',width:parseInt($(this).width() * 0.1)},
			         {field: 'fullName', title: '全名', width: parseInt($(this).width()* 0.15)},
			         {field: 'ename', title: '英文名', width: parseInt($(this).width()* 0.15)},
			         {field: 'tel', title: '电话', width: parseInt($(this).width()* 0.1)},
			         {field: 'fax', title: '传真', width: parseInt($(this).width()* 0.1)},
			         {field: 'managerName', title: '主管', width: parseInt($(this).width()* 0.1)},
			         {field: 'description', title: '描述', width: parseInt($(this).width()* 0.1)},
			]],toolbar: '#tb'
		});
	});
	
	function addDepartmentDlg(){
		var row = $dg.treegrid('getSelected');
		parent.$.modalDialog({
			title: '添加部门',
			iconCls: 'icon-add',
			width: 600,
			height: 400,
			href: 'view/department/departmentEditDlg.jsp',
			onLoad: function(){
				if(row){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form('load',{"pid": row.departmentId})
				}
			},
			buttons:[{
				text: '保存',
				iconCls: 'icon-ok',
				handler: function(){
					parent.$.modalDialog.openner = $grid;
					var f = parent.$.modalDialog.handler.find("#form");
					f.submit();
				}
			},{
				text: '取消',
				iconCls: 'icon-edit',
				handler: function(){
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
			}]
			
		});
	}
	
	function updateDepartmentDlg(){
		var row = $dg.treegrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title: '修改部门',
				iconCls: 'icon-edit',
				width: 600,
				height: 400,
				href: 'view/department/departmentEditDlg.jsp',
				onLoad: function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form('load', row);
				},
				buttons: [{
					text: '修改',
					iconCls: 'icon-edit',
					handler: function(){
						parent.$.modalDialog.openner = $grid;
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					handler: function(){
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				}]
			});
		}else{
			parent.$.messager.show({
				title: '提示',
				msg: '请选择一行记录！',
				timeout: 1000 * 2
			});
		}
	}
	
	function delDepartment(){
		var node = $dg.treegrid('getSelected');
		if(node){
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("department/delete.action",{departmentId: node.departmentId}, function(rsp){
						if(rsp.status){
							$dg.treegrid('remove', node.departmentId);
						}
						$.erp.submitSuccess(rsp.title, rsp.message);
					},"JSON").error(function(){
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
		<div data-options="region:'center',border:false" >
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addDepartmentDlg();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateDepartmentDlg();">修改</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delDepartment();">删除</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="">导出Excel</a>
						</td>						
					</tr>
				</table>
			</div>
				
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>