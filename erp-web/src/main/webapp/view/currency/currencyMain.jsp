<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro"  uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>币别</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'currency/find.action',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pagination: false,
			rownumbers: true,
			striped: true,
			border: false,
			singleSelect: true,
			idField: 'currencyId',
			columns: [[
				{field: 'number', title: '代码', width: parseInt($(this).width() * 0.05)},
				{field: 'name', title: '名称', width: parseInt($(this).width() * 0.1)},
				{field: 'exchangeRate', title: '汇率', width: parseInt($(this).width() * 0.1)},
				{field: 'scale', title: '小数位数', width: parseInt($(this).width() * 0.1)},
				{field: 'fixRate', title: '固定汇率', width: parseInt($(this).width() * 0.1)},
			]],toolbar: '#tb'
		});
		
	});
	
	//删除
	function delCurrency(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm("提示","确认要删除记录吗？", function(r){
				if(r){
					$.post("currency/delete.action",{"currencyId":row.currencyId},function(rsp){
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
	
	function showDlg(title, iconCls, type, row, status){
		var viewPath = 'view/currency/currencyEditDlg.jsp';
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,'',row,
				$grid,'','',600, 450);
	}
	
	function updateCurrencyDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改币别','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	//弹窗增加公司
	function addCurrencyDlg(){
		showDlg('添加币别','icon-add','add');
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
		<div data-options="region:'center', border: false" >
			<div id="tb">
				<table>
					<tr>
						<td>
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-add" plain="true" onclick="addCurrencyDlg();">添加</a>
						
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-edit" plain="true" onclick="updateCurrencyDlg();">修改</a>
						
							
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-remove" plain="true" onclick="delCurrency();">删除</a>
						
						</td>
					</tr>
				</table>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>