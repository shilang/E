<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>销售报价单</title>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$gird = $dg.datagrid({
			url: 'salesPriceList/salesPriceListAction!findSalesPriceList.action',
			width: 'auto',
			height: $(this).height() - 85,
			pagination: true,
			rownumbers: true,
			border: true,
			striped: true,
			singleSelect: true,
			columns:[[
				{field: 'date', title: '日期'},
				{field: 'buyUnit', title: '购货单位'},
				{field: 'currency', title: '货币'},
				{field: 'manager', title: '主管'},
				{field: 'dept', title: '部门'},
				{field: 'sales', title: '业务员'},
				{field: 'creater', title: '制单'},
				{field: 'exchangeRate', title: '汇率'},
				{field: 'productName', title: '产品名称'},
				{field: 'productModel', title: '规格型号'},
				{field: 'unit', title: '单位'},
				{field: 'quantity', title: '数量'},
				{field: 'price', title: '单价'},
				{field: 'discountRate', title: '折扣率'},
				{field: 'remark', title: '备注'},
				{field: 'audit', title: '审核人'},
				{field: 'removeFlag', title: '作废标志'},
				{field: 'auditDate', title: '审核日期'},
				{field: 'secondaryAttr', title: '辅助属性'},
			]],toolbar: '#tb'
		});
	});
</script>
</head>
<body>
		<div class="well well-small" style="margin-left: 5px; margin-top: 5px;">
			<span class="badge">提示</span>
			<p>
				在此你可以对<span class="label-info"><strong>销售报价单</strong></span>进行编辑!
			</p>
		</div>
		
		<div id="tb" style="padding:10px;height:auto">
			<div style="margin-bottom: 5px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="">编辑</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="">删除</a>
			</div> 
		</div>
		
		<table id="dg" title="销售报价单"></table>
</body>
</html>