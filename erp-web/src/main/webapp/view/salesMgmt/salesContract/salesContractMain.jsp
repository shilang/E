<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>销售合同</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var selSta = <%=request.getParameter("selSta")%>;
	var $dg;
	var $grid;
	//var $entry;
	//var $detail;
	var queryParams = {sort:'interId', order:'desc'};
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
			url: 'salesContract/findAll.action',
			width: $(this).width(),
			height: $(this).height(),
			pagination: true,
			pagePosition: 'top',
			collapsible: true,
			pageSize: 20,
			rownumbers: true,
			border: false,
			striped: true,
			singleSelect: true,
			fitColumns: false,
			idField:'interId',
			queryParams: queryParams,
			columns:[[
			   {field: 'result', title: '状态', width: parseInt($(this).width() * 0.06),
							formatter:function(value, row){
								var status = $.erp.getResultStatus(value);
								return '<span style="color:'+status.color+';">' + status.msg + '</span>';
				},sortable:true},
				{field: 'billNo', title: '合同号',width:parseInt($(this).width()*0.1),sortable:true},
				{field: 'contractName', title: '合同名称',width:parseInt($(this).width()*0.1),sortable:true},
				{field: 'date', title: '合同日期',width:parseInt($(this).width()*0.1),sortable:true},
				{field: 'classType', title: '项目类别',width:parseInt($(this).width()*0.1)},
				{field: 'itemClassName', title: '项目目标',width:parseInt($(this).width()*0.1)},
				{field: 'departmentName', title: '部门',width:parseInt($(this).width()*0.1),sortable:true},
				{field: 'employeeName', title: '业务员',width:parseInt($(this).width()*0.1),sortable:true},
				{field: 'currencyName', title: '币别',width:parseInt($(this).width()*0.05)},
				{field: 'exchangeRate', title: '汇率',width:parseInt($(this).width()*0.1)},
				{field: 'totalAmount', title: '总金额',width:parseInt($(this).width()*0.1),sortable:true},
				{field: 'explanation', title: '摘要',width:parseInt($(this).width()*0.1)},
				{field: 'remark', title: '备注',width:parseInt($(this).width()*0.1)},
				{field: 'createrName', title: '制单人',width:parseInt($(this).width()*0.1),sortable:true},
				{field: 'checkerName', title: '审核人',width:parseInt($(this).width()*0.1),sortable:true},
				{field: 'checkDate', title: '审核日期',width:parseInt($(this).width()*0.14),sortable:true}
			]],
			//toolbar: '#tb',
			//onClickRow: onClickRow,
			onDblClickRow: onDblClickRow
		});
		
		$grid.datagrid('getPager').pagination({
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
			var billNo = row.billNo;
			parent.$.modalDialog.handler.find("#sourceBillNo").textbox("setValue", billNo);
			var type = parent.$.modalDialog.type;
			var url = "";
			if(type == "XSDD"){
				url = "salesOrder/findEntriesByType.action";
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
			row.customerId = row.itemClassId;
			f.form('load',row);
			
			//load entry data
			$.erp.ajax(url,
					{entryType:'contract',interId:row.interId},
					function(data){
						$.each(data.rows,function(idx, value){
							var date = parent.$.modalDialog.date;
							value.date = date;
							value.adviceDate = date;
							value.sourceBillNo = billNo;
							parent.$.modalDialog.dg.datagrid('appendRow',value);
						});
					},true);
			parent.$.modalDialog.selDlg.dialog("destroy");
		}else{
			updateSalesContractDlg();
		}
	}
	
	function addSalesContractDlg(){
		showDlg('添加合同(应收)','icon-add','add');
	}
	
	function updateSalesContractDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改合同(应收)','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showSalesContractDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('查看合同(应收)','icon-show','show',row);
		}
	}
	
	function showDlg(title,iconCls,type,row,status){
		var viewPath = 'view/salesMgmt/salesContract/salesContractEditDlg.jsp';
		var numMethod = "getSalesContractNumber";
		var processDefKey = "sales-contract-process-apply";
		var entityName = "SalesContract";
		
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName,1000);
	}
	
	function delSalesContract(){
		var row = $dg.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return;
			}
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("salesContract/delete.action",{interId:row.interId}, function(rsp){
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
	
	function commitSalesContract(){
		return $.erp.commitBusinessList($dg);
	}
	
	function checkPending(){
		$.erp.checkPending($dg);
	}
	
	function hold(){
		$.erp.hold();
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center', border:false" >
			<div id="tb">
				<table>
					<tr>
						<td>
							<shiro:hasPermission name="salContractAdd">
								<a id="addOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addSalesContractDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salContractMod">
								<a id="updOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateSalesContractDlg()">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salContractDel">
								<a id="delOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delSalesContract();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salContractShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-show" plain="true" onclick="showSalesContractDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salContractCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-datago" plain="true" onclick="commitSalesContract();">提交</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salContractExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="hold();">导出Excel</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox"/>
						</td>
						<%-- <td>
							<shiro:hasPermission name="salContractCheck">
								<a id="pendingOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
							</shiro:hasPermission>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级查询</a>
						</td> --%>
					</tr>
				</table>
			</div>
			<div id="mm">
				<div name="billNo">合同号</div>
			</div>
			<table id="dg" title=""></table>
		</div>

	</div>
</body>
</html>