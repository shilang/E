<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>客户管理</title>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'customer/customerAction!findCustomers.action',
			width: $(this).width() - 10,
			height: $(this).height() - 82,
			pagination: true,
			collapsible: true,
			rownumbers: true,
			striped: true,
			simpleSelect: true,
			border: true,
			idField: 'customerId',
			columns: [[
				{field: 'number', title: '代码'},
				{field: 'name', title: '名称'},
				{field: 'fullName', title: '全名'},
				{field: 'country', title: '国家'},
				{field: 'phone', title: '电话'},
				{field: 'fax', title: '传真'},
				{field: 'email', title: '邮箱'},
			]],
			toolbar: '#tb'
		});
		
		$("#searchbox").searchbox({
			width: 250,
			menu: '#mm',
			prompt: '模糊查询',
			searcher: function(value, name){
				$dg.datagrid('reload', $.erp.searcher(name,value));
			}
		});
	});
	
	function addCustomerDlg(){
		parent.$.modalDialog({
			title: '添加客户',
			iconCls: 'icon-add',
			width: 600,
			height: 450,
			href: 'view/customer/customerEditDlg.jsp',
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
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
			}]
		});
	}
	
	function updateCustomerDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title: '修改客户',
				iconCls: 'icon-edit',
				width: 600,
				height: 450,
				href: 'view/customer/customerEditDlg.jsp',
				onLoad: function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form('load',row);
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
			$.erp.noSelectErr();
		}
	}
	
	function delCustomer(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("customer/customerAction!delCustomer.action",{id: customerId},function(rsp){
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
	<div class="easyui-layout" data-options="fit:true,border:false" >
		<div data-options="region:'center', border:false" style="padding: 5px;">
			<div class="well well-small" style="margin-bottm: 5px;">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>客户</strong></span>进行编辑!
				</p>
			</div>
			
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCustomerDlg();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCustomerDlg();">修改</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delCustomer();">删除</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="">导出Excel</a>
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
				<div name="name">客户名称</div>
				<div email="email">客户邮件</div>
			</div>
			<table id="dg" title="客户管理"></table>
		</div>
	</div>
</body>
</html>