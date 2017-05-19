<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>销售出库</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var selSta = <%=request.getParameter("selSta")%>
	var $dg;
	var $grid;
	//var $entry;
	var queryParams = {sort:'interId',order:'desc'};
	$(function(){
		
		if(selSta){
			$(".submain").hide();
			$.extend(queryParams, $.erp.searcher("result", $.erp.resultCheckOk, "int"));
		}
		
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url:'salesOutStock/findAll.action',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pageSize: 20,
			pagination: true,
			pagePosition: 'top',
			rownumbers: true,
			striped: true,
			border: false,
			singleSelect: true,
			queryParams: queryParams,
			idField: 'interId',
			columns:[[
					{field: 'result', title: '状态', width: parseInt($(this).width() * 0.08),
						formatter:function(value, row){
							return $.erp.getResultStatus(value);
					},sortable:true},
			        {field:'billNo',title:'单据编号',width:parseInt($(this).width()*0.12),sortable:true},
			        {field:'date',title:'日期',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'marketingWayName',title:'销售业务类型',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'salesWayName',title:'销售方式',width:parseInt($(this).width()*0.1),sortable:true},
			        /* {field:'customerName',title:'购货单位',width:parseInt($(this).width()*0.1),sortable:true}, */
			        {field:'fetchAddrName',title:'交货地点',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'stockName',title:'发货仓库',width:parseInt($(this).width()*0.1)},
			        {field:'settlementDate',title:'收款日期',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'senderName',title:'发货',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'defenderName',title:'保管',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'departmentName',title:'部门',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'employeeName',title:'业务员',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'managerName',title:'主管',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'checkerName',title:'审核人',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'checkDate',title:'审核日期',width:parseInt($(this).width()*0.16),sortable:true},
			        {field:'createrName',title:'制单',width:parseInt($(this).width()*0.08)},
			        {field:'sourceType',title:'源单类型',width:parseInt($(this).width()*0.1)},
			        {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.12)},
			        {field:'explanation',title:'摘要',width:parseInt($(this).width()*0.4)}
			        ]],
			//toolbar: '#tb',
			//onClickRow: onClickRow,
			onDblClickRow: onDblClickRow
		}).datagrid('getPager').pagination({
			buttons:'#tb'
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
			var type = parent.$.modalDialog.type;
			var url = "";
			if(type == "XSCK"){
				url = "salesOutStock/findEntriesByType.action";
			}else if(type == "XSFP"){
				url = "salesInvoice/shareEntries";
			}else{
				return;
			}
			//load form data
			var f = parent.$.modalDialog.handler.find('#form');
			$.erp.excludeAttrForFormFill(row);
			f.form('load', row);
			//load entry data
			$.erp.ajax(url,
					{entryType:'outstock',interId:interId},
					function(data){
						$.each(data.rows,function(idx, value){
							value.sourceBillNo = billNo;
							parent.$.modalDialog.dg.datagrid('appendRow',value);
						});
					},true);
			parent.$.modalDialog.selDlg.dialog("destroy");
		}else{
			updateSalesOutStockDlg();
		}
	}
	
	function addSalesOutStockDlg(){
		showDlg('添加销售出库单','icon-add','add');
	}
	
	function updateSalesOutStockDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改销售出库单','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showSalesOutStockDlg(){
		var row = $dg.datagrid('getSelected')
		if(row){
			showDlg('查看销售出库单','icon-show','show',row);			
		}
	}
	
	function showDlg(title,iconCls,type,row,status){
		var viewPath = 'view/salesMgmt/salesOutStock/salesOutStockEditDlg.jsp';
		var numMethod = "getSalesOutStockNumber";
		var processDefKey = "sales-outstock-process-apply";
		var entityName = "ICStockBill";
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName);
	}
	
	function delSalesOutStock(){
		var row = $dg.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return;
			}
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("salesOutStock/delete.action",{interId:row.interId},function(rsp){
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
	
	function commitSalesOutStock(){
		return $.erp.commitBusinessList($dg);
	}
	
	function checkPending(){
		$.erp.checkPending($dg);
	}
	
	function exportExcel(){	
		var beanName = 'salesOutStockServiceImpl';
		var reportSchemaName = 'salesOutStockReport.xml';
		$.erp.exportExcel(beanName,reportSchemaName,$dg.datagrid('options'));
	}
	
	function hold(){
		$.erp.hold();
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
							<shiro:hasPermission name="salOutStockAdd">
								<a id="addOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-add',plain:true" onclick="addSalesOutStockDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutStockMod">
								<a id="updOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-edit',plain:true" onclick="updateSalesOutStockDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutStockDel">
								<a id="delOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-remove',plain:true" onclick="delSalesOutStock();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutStockShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-show" plain="true" onclick="showSalesOutStockDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutStockCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-datago" plain="true" onclick="commitSalesOutStock();">提交</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutStockExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-excel" plain="true" onclick="exportExcel();">导出Excel</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox">
						</td>
						<%-- <td>
							<shiro:hasPermission name="salOutStockCheck">
								<a id="pendingOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
							</shiro:hasPermission>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="">高级查询</a> 
						</td> --%>
					</tr>
				</table>
			</div>
			<div id="mm">
				<div name="billNo">单据编号</div>
				<div name="sourceBillNo">源单单号</div>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>