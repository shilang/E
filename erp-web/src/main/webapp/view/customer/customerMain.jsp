<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>客户管理</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'customer/find.action',
			width: $(this).width(),
			height: $(this).height(),
			pagination: true,
			collapsible: true,
			rownumbers: true,
			striped: true,
			singleSelect: true,
			border: false,
			idField: 'customerId',
			columns: [[
				{field: 'number', title: '代码', width: parseInt($(this).width()* 0.05)},
				{field: 'name', title: '名称', width: parseInt($(this).width()* 0.1)},
				{field: 'fullName', title: '全名', width: parseInt($(this).width()* 0.1)},
				{field: 'regionName', title: '国家', width: parseInt($(this).width()* 0.1)},
				{field: 'typeName', title: '客户分类', width: parseInt($(this).width()*0.1)},
				{field: 'salesModeName', title: '销售方式', width: parseInt($(this).width()*0.1)},
				{field: 'address', title: '地址', width: parseInt($(this).width()*0.1)},
				{field: 'phone', title: '电话', width: parseInt($(this).width()* 0.1)},
				{field: 'fax', title: '传真', width: parseInt($(this).width()* 0.1)},
				{field: 'email', title: '邮箱', width: parseInt($(this).width()* 0.1)},
				{field: 'homePage', title: '主页', width: parseInt($(this).width()* 0.1)},
				{field: 'remark', title: '备注', width: parseInt($(this).width()* 0.1)}
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
	
	function showDlg(title, iconCls, type, row, status){
		var viewPath = 'view/customer/customerEditDlg.jsp';
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,'',row,
				$grid,'','',600, 450);
	}
	
	function addCustomerDlg(){
		showDlg('添加客户','icon-add','add');
	}
	
	function updateCustomerDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改客户','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delCustomer(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("customer/delete.action",{"customerId": row.customerId},function(rsp){
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
		<div data-options="region:'center', border:false">
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
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>