<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/euinc.jsp"></jsp:include>
<title>销售订单</title>
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
			$.extend(queryParams, $.erp.searcher('result', $.erp.resultCheckOk, 'int'));
		}
		
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url:'salesOrder/findAll.action',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pagination: true,
			rownumbers: true,
			border: false,
			striped: true,
			singleSelect: true,
			queryParams: queryParams,
			idField: 'interId',
			columns:[[
						{field: 'result', title: '状态', width: parseInt($(this).width() * 0.06),
							formatter:function(value, row){
								var status = $.erp.getResultStatus(value);
								return '<span style="color:'+status.color+';">' + status.msg + '</span>';
						},sortable:true},
			        	{field:'billNo',title:'单据编号',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'date',title:'日期',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'customerName',title:'购货单位',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'salesScopeName',title:'销售范围',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'salesWayName',title:'销售方式',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'fetchWayName',title:'交货方式',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'fetchAddrName',title:'交货地点',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'transitAheadTime',title:'运输提前期',width:parseInt($(this).width()*0.1)},
			        	{field:'settlementName',title:'结算方式',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'settlementDate',title:'结算日期',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'currencyName',title:'币别',width:parseInt($(this).width()*0.1)},
			        	{field:'exchangeRate',title:'汇率',width:parseInt($(this).width()*0.1)},
			        	{field:'managerName',title:'主管',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'departmentName',title:'部门',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'employeeName',title:'业务员',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'createrName',title:'制单',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'checkerName',title:'审核人',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'checkDate',title:'审核日期',width:parseInt($(this).width()*0.14),sortable:true},
			        	{field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'sourceType',title:'源单类型',width:parseInt($(this).width()*0.1)},
			        	{field:'explanation',title:'摘要',width:parseInt($(this).width()*0.1)}
			        ]],
			toolbar: '#tb',
			//onClickRow: onClickRow,
			onDblClickRow: onDblClickRow
		});
		
		$("#searchbox").searchbox({
			width: 250,
			menu: '#mm',
			prompt: '模糊查询',
			searcher: function(value,name){
				$dg.datagrid('reload', $.erp.searcher(name, value));
			}
		});
		
	});
	
	
	function onDblClickRow(index, row){
		if(selSta){
			var interId = row.interId;
			var billNo = row.billNo;
			parent.$.modalDialog.handler.find("#sourceBillNo").textbox("setValue", billNo);
			var type = parent.$.modalDialog.type;
			var url = "";
			if(type == "FHTZ"){
				url = "salesOutStockNotice/findEntriesByType.action";
			}else if(type == "XSCK"){
				url = "salesOutStock/findEntriesByType.action";
			}else if(type == "XSFP"){
				url = "salesInvoice/shareEntries";
			}else if(type == "YSK"){
				url = "proceeds/shareEntries";	
			}else{
				return;
			}
			//load form data
			var f = parent.$.modalDialog.handler.find('#form');
			$.erp.excludeAttrForFormFill(row);
			f.form('load', row);
			
			//load entry data
			$.erp.ajax(url,
					{entryType:'order',interId:interId},
					function(data){
						$.each(data.rows,function(idx, value){
							value.fetchDate = parent.$.modalDialog.date;
							value.sourceBillNo = billNo;
							parent.$.modalDialog.dg.datagrid('appendRow',value);
						});
					},true);
			parent.$.modalDialog.selDlg.dialog("destroy");
		}else{
			updateSalesOrderDlg();
		}
	}
	
	function addSalesOrderDlg(){
		showDlg('添加订单','icon-add','add');
	}
	
	function updateSalesOrderDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改订单', 'icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}

	function showSalesOrderDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('查看订单','icon-show','show',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showDlg(title, iconCls,type,row){
		var viewPath = 'view/salesMgmt/salesOrder/salesOrderEditDlg.jsp';
		var numMethod = "getSalesOrderNumber";
		var processDefKey = "sales-order-process-apply";
		var entityName = "SalesOrder";
		
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName);
	}
	
	function delSalesOrder(){
		var row = $dg.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return;
			}
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("salesOrder/delete.action",{interId:row.interId},function(rsp){
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
	
	function commitSalesOrder(){
		return $.erp.commitBusinessList($dg);
	}
	
	function hold(){
		$.erp.hold();
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
							<shiro:hasPermission name="salOrderAdd">
								<a id="addOper" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addSalesOrderDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderMod">
								<a id="updOper" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateSalesOrderDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderDel">
								<a id="delOper" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delSalesOrder();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-show" plain="true" onclick="showSalesOrderDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-datago" plain="true" onclick="commitSalesOrder();">提交</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="hold();">导出Excel</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox">
						</td>
						<td>
							<shiro:hasPermission name="salOrderCheck">
								<a id="pendingOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
							</shiro:hasPermission>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="">高级查询</a>
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