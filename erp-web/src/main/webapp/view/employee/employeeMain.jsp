<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>员工管理</title>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'employee/employeeAction!findEmployees.action',
			width: $(this).width() - 10,
			height: $(this).height() - 82,
			pagination: true,
			collapsible: true,
			rownumbers: true,
			striped: true,
			border: true,
			singleSelect: true,
			idField: 'employeeId',
			buttons: [[
				{field: 'number', title: '代码'},
				{field: 'name', title: '名称'},
				{field: 'gender', title: '性别'},
				{field: 'birthday', title: '出生日期'},
				{field: 'degree', title: '文化程度'},
				{field: 'tel', title: '电话'},
				{field: 'duty', title: '职位'},
				{field: 'hireDate', title: '入职日期'},
				{field: 'leaveDate', title: '离职日期'},
				{field: 'address', title: '住址'},
				{field: 'email', title: '电子邮件'},
				{field: 'remark', title: '备注'}
			]],
			toolbar: '#tb'
		});
		
		$("#searchbox").searchbox({ 
			 width: 250,
			 menu:"#mm", 
			 prompt :'模糊查询',
		    searcher:function(value,name){    
	            $dg.datagrid('reload',$.erp.searcher(name, value)); 
		    }
		});
	});
	
	function addEmployeeDlg(){
		parent.$.modalDialog({
			title: '添加职员',
			iconCls: 'icon-add',
			width: 600,
			height: 450,
			href: 'view/employee/employeeEditDlg.jsp',
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
				iconCls: 'icon-cancel',
				handler: function(){
					parent.$.modalDialog.handler.dialog("destroy");
					parent.$.modalDialog.handler = undefined;
				}
			}]
		});
	}
	
	function updateEmployeeDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title: '修改职员',
				iconCls: 'icon-edit',
				width: 600,
				height: 450,
				href: 'view/employee/employeeEditDlg.jsp',
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
						parent.$.modalDialog.handler.dialog("destroy");
						parent.$.modalDialog.handler = undefined;
					}
				}]
			});
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delEmployee(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint,$.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("employee/employeeAction!delEmployee.action", {"employeeId": row.employeeId}, function(rsp){
						if(rsp.status){
							var idx = $dg.datagrid('getRowIndex', row);
							$dg.datagrid('deleteRow', idx);
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
		<div data-options="region: 'center', border: false" style="padding: 5px;">
			<div class="well well-small" style="margin-bottom: 5px;">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>职员</strong></span>进行编辑!
				</p>
			</div>
			
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addEmployeeDlg();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateEmployeeDlg();">修改</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delEmployee();">删除</a> 
							<a href="javascript:void(0);" class="easyui-linkbutton" icnoCls="icon-excel" plain="true" onclick="">导出Excel</a>
						</td>
						<td>
							<input id="searchbox" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级查询</a>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="mm">
				<div name="test">测试</div>
			</div>
			
			<table id="dg" title="职员管理"></table>
		</div>
	</div>
</body>
</html>