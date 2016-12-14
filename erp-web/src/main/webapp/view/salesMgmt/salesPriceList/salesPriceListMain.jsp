<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>销售报价单</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var selSta = <%=request.getParameter("selSta")%>;
	var $dg;
	var $grid;
	//var $entry;
	var queryParams = {sort:'interId',order:'desc'};
	$(function(){
		if(selSta){
			$("#addOper").hide();
			$("#updOper").hide();
			$("#delOper").hide();
			$("#showOper").hide();
			$("#checkOper").hide();
			$("#exportOper").hide();
			$("#commitOper").hide();
			$("#unCheckOper").hide();
			$("#pendingOper").hide();
			$.extend(queryParams, $.erp.searcher("result", $.erp.resultCheckOk, "int"));
		}
		
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'salesPriceList/findAll.action',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pageSize: 20,
			pagination: true,
			rownumbers: true,
			border: false,
			striped: true,
			singleSelect: true,
			fitColumns: false,
			queryParams: queryParams,
			columns:[[
				{field: 'result', title: '状态', width: parseInt($(this).width() * 0.08),
							formatter:function(value, row){
								var status = $.erp.getResultStatus(value);
								return '<span style="color:'+status.color+';">' + status.msg + '</span>';
				},sortable:true},
				{field: 'billNo', title: '单据编号', width: parseInt($(this).width() * 0.12),sortable:true},
				{field: 'date', title: '日期', width: parseInt($(this).width() * 0.1),sortable:true},
				{field: 'customerName', title: '购货单位', width: parseInt($(this).width() * 0.15),sortable:true},
				{field: 'currencyName', title: '货币', width: parseInt($(this).width() * 0.08),sortable:true},
				{field: 'managerName', title: '主管', width: parseInt($(this).width() * 0.08),sortable:true},
				{field: 'deptName', title: '部门', width: parseInt($(this).width() * 0.08),sortable:true},
				{field: 'employeeName', title: '业务员', width: parseInt($(this).width() * 0.08),sortable:true},
				{field: 'createrName', title: '制单', width: parseInt($(this).width() * 0.08),sortable:true},
				{field: 'exchangeRate', title: '汇率', width: parseInt($(this).width() * 0.08)},
				{field: 'checkerName', title: '审核人', width: parseInt($(this).width() * 0.08),sortable:true},
				{field: 'checkDate', title: '审核日期', width: parseInt($(this).width() * 0.16),sortable:true},
				{field: 'removeFlag', title: '作废标志', width: parseInt($(this).width() * 0.1)}
			]],toolbar: '#tb',
			//onClickRow: onClickRow,
			onDblClickRow: onDblClickRow
		});
		
		$("#searchbox").searchbox({
			width: 250,
			menu: '#mm',
			prompt: '模糊查询',
			searcher: function(value, name){
				$dg.datagrid('reload', $.erp.searcher(name, value));
			}
		});
		
	});
	
	function onDblClickRow(index, row){
		if(selSta){
			var interId = row.interId;
			var billNo = row.billNo;
			parent.$.modalDialog.handler.find("#sourceBillNo").textbox("setValue", billNo);
			//load form data
			var f = parent.$.modalDialog.handler.find('#form');
			$.erp.excludeAttrForFormFill(row);
			f.form('load', row);
			//load entry data
			$.erp.ajax('salesOrder/findEntriesByType.action',
					{entryType:'pricelist',interId:interId},
					function(data){
						$.each(data.rows,function(idx, value){
							value.sourceBillNo = billNo;
							var date = parent.$.modalDialog.date;
							value.date = date;
							value.adviceDate = date;
							parent.$.modalDialog.dg.datagrid('appendRow',value);
						});
					},true);
			
			parent.$.modalDialog.selDlg.dialog("destroy");
		}else{
			updateSalesPriceListDlg();
		}
	}
	
	function addSalesPriceListDlg(){
		showDlg('添加报价单','icon-add','add');
	}
	
	function updateSalesPriceListDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改报价单','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showSalesPriceListDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('查看报价单','icon-show','show',row);
		}
	}
	
	function showDlg(title,iconCls,type,row,status){
		
		var viewPath = 'view/salesMgmt/salesPriceList/salesPriceListEditDlg.jsp';
		var numMethod = '';//"getSalesPriceListNumber";
		var processDefKey = "sales-pricelist-process-apply";
		var entityName = "SalesPriceList";
		
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName);
	}
	
	function delSalesPriceList(){
		var row = $dg.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return;
			}
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("salesPriceList/delete.action",{interId:row.interId}, function(rsp){
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
	
	function checkPending(){
		$.erp.checkPending($dg);
	}
	
	function commitSalesPriceList(){
		return $.erp.commitBusinessList($dg);
	}
	
	function hold(){
		$.erp.hold();
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center', border:false">
			<div id="tb">
				<table>
					<tr>
						<td>
							<shiro:hasPermission name="salPriceAdd">
								<a id="addOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addSalesPriceListDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salPriceMod">
								<a id="updOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateSalesPriceListDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salPriceDel">
								<a id="delOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delSalesPriceList();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salPriceShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-show" plain="true" onclick="showSalesPriceListDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salPriceCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-datago" plain="true" onclick="commitSalesPriceList();">提交</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salPriceExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="hold();">导出Excel</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox"/>
						</td>
						<td>
							<shiro:hasPermission name="salPriceCheck">
								<a id="pendingOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
							</shiro:hasPermission>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级查询</a>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="mm">
				<div name="billNo">单据编号</div>
			</div>
		
			<table id="dg" title=""></table>
		</div>
		
	</div>
</body>
</html>