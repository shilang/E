<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro"  uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>公司管理</title>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'companyInfo/companyInfoAction!findCompanyInfos.action',
			width: $(this).width() - 10,
			height: $(this).height() - 82,
			collapsible: true,
			pagination: false,
			rownumbers: true,
			striped: true,
			border: true,
			singleSelect: true,
			columns: [[
				{field: 'name', title: '公司名称', width: parseInt($(this).width() * 0.1), editor: {type: 'validatebox', options:{
					required: true
				}}},
				{field: 'tel', title: '公司电话', width: parseInt($(this).width() * 0.1), editor: 'validatebox'},
				{field: 'fax', title: '传真', width: parseInt($(this).width() * 0.1), align: 'left', editor: 'text'},
				{field: 'address', title: '地址', width: parseInt($(this).width() * 0.1), align: 'left', editor: 'text'},
				{field: 'zip', title: '邮政编码', width: parseInt($(this).width() * 0.1), align: 'left', editor: 'text'},
				{field: 'email', title: '邮箱', width: parseInt($(this).width() * 0.1), align: 'left', editor: {type:'validatebox', options:{
					required: true, validType:'email'}}},
				{field: 'contact', title: '联系人', width: parseInt($(this).width() * 0.1), align: 'left', editor: 'text'},
				{field: 'description', title: '描述', width: parseInt($(this).width() * 0.1), align: 'left', editor: 'text'}
			]],toolbar: '#tb'
		});
		
	});
	
	//删除
	function delCompanyInfo(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm("提示","确认要删除记录吗？", function(r){
				if(r){
					$.post("companyInfo/companyInfoAction!delCompanyInfo.action",{companyId:row.companyId},function(rsp){
						if(rsp.status){
							var idx = $dg.datagrid('getRowIndex', row);
							$dg.datagrid('deleteRow', idx);
						}
						parent.$.messager.show({
							title: rsp.title,
							msg: rsp.message,
							timeout: 1000 * 2
						});
					},"JSON").error(function(){
						parent.$.messager.show({
							title: '提示',
							msg: '提交错误',
							timeout: 1000 * 2
						});
					});
				}
			});
		}else{
			parent.$.messager.show({
				title: "提示",
				msg: "请选择一行记录！",
				timeout: 1000 * 2
			});
		}
	}
	
	function updateCompanyInfoDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title: '修改公司',
				iconCls: 'icon-edit',
				width: 600,
				height: 400,
				href: 'view/company/companyEditDlg.jsp',
				onLoad: function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form("load", row);
				},
				buttons: [{
					text: '修改',
					iconCls: 'icon-edit',
					handler: function(){
						parent.$.modalDialog.openner=$grid;
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
				msg: '请选择一行记录!',
				timeout: 1000 * 2
			});
		}
	}
	
	//弹窗增加公司
	function addCompanyInfoDlg(){
		parent.$.modalDialog({
			title: '添加公司',
			iconCls: 'icon-add',
			width: 600,
			height: 400,
			href: 'view/company/companyEditDlg.jsp',
			buttons: [{
				text: '保存',
				iconCls: 'icon-ok',
				handler: function(){
					parent.$.modalDialog.openner=$grid;
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
	
	//导出Excel
	function exportExcel(){
		var rows = $dg.datagrid("getRows");
		if(rows.length){
			var isCheckedIds=[];
			$.each(rows,function(i, row){
				if(row){
					isCheckedIds.push(row.companyId);
				}
			});
			window.location.href="excel/excelAction!CompanyInfoExcelExport.action?isCheckedIds="+isCheckedIds;
		}else{
			parent.$.messager.show({
				title: '提示',
				msg: '暂无数据导出!',
				timeout: 1000 * 2
			});
		}
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center', border: false" style="padding: 5px;">
			<div class="well well-small" style="margin-bottom: 5px;">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>公司</strong></span>进行编辑!
				</p>
			</div>

			<div id="tb">
				<table>
					<tr>
						<td><shiro:hasPermission
								name="compAdd">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-add" plain="true" onclick="addCompanyInfoDlg();">添加</a>
							</shiro:hasPermission> <shiro:hasPermission name="compEdit">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-edit" plain="true" onclick="updateCompanyInfoDlg();">修改</a>
							</shiro:hasPermission> <shiro:hasPermission name="compDel">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-remove" plain="true" onclick="delCompanyInfo();">删除</a>
							</shiro:hasPermission> <a href="javascript:void(0);" class="easyui-linkbutton"
							iconCls="icon-excel" plain="true" onclick="exportExcel();">导出Excel</a>
						</td>
					</tr>
				</table>
			</div>
			<table id="dg" title="公司管理"></table>
		</div>
	</div>
</body>
</html>