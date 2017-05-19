<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>销售发票</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var selSta = <%=request.getParameter("selSta") %>
	var $dg;
	var $grid;
	var queryParams = {sort: 'interId', order: 'desc'};
	$(function(){
		if(selSta){
			$(".submain").hide();
			$.extend(queryParams, $.erp.searcher('result', $.erp.resultCheckOk,'int'));
		}
		
		$dg = $('#dg');
		$grid = $dg.datagrid({
			url: 'salesInvoice/list',
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
			columns: [[
			               {field: 'result', title: '状态', width: parseInt($(this).width() * 0.08),
							formatter:function(value, row){
								return $.erp.getResultStatus(value);
						   },sortable:true},
			        	   {field:'billNo',title:'发票号码',width:parseInt($(this).width()*0.12),sortable:true},
				        	{field:'date',title:'日期',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'customerName',title:'购货单位',width:parseInt($(this).width()*0.15),sortable:true},
				        	{field:'salesWayName',title:'销售方式',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'settleName',title:'结算方式',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'settleDate',title:'结算日期',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'currencyName',title:'币别',width:parseInt($(this).width()*0.06)},
				        	{field:'exchangeRate',title:'汇率',width:parseInt($(this).width()*0.06)},
				        	{field:'managerName',title:'主管',width:parseInt($(this).width()*0.08),sortable:true},
				        	{field:'departmentName',title:'部门',width:parseInt($(this).width()*0.08),sortable:true},
				        	{field:'employeeName',title:'业务员',width:parseInt($(this).width()*0.08),sortable:true},
				        	{field:'createrName',title:'制单',width:parseInt($(this).width()*0.08),sortable:true},
				        	{field:'checkerName',title:'审核人',width:parseInt($(this).width()*0.08),sortable:true},
				        	{field:'checkDate',title:'审核日期',width:parseInt($(this).width()*0.16),sortable:true},
				        	{field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.12),sortable:true},
				        	{field:'sourceType',title:'源单类型',width:parseInt($(this).width()*0.1)},
				        	{field:'explanation',title:'摘要',width:parseInt($(this).width()*0.4)}
			         ]],
			//toolbar: '#tb',
			onDblClickRow: onDblClickRow
		}).datagrid('getPager').pagination({
			buttons:'#tb'
		});
		
		$('#searchbox').searchbox({
			width: 250,
			menu: '#mm',
			prompt: '模糊查询',
			searcher: function(value, name){
				$dg.datagrid('reload', $.erp.searcher(name,value));
			}
		});
	});
	
	function onDblClickRow(index, row){
		if(selSta){
			var billNo = row.billNo;
			var interId = row.interId;
			parent.$.modalDialog.handler.find("#sourceBillNo").textbox("setValue", billNo);
			var type = parent.$.modalDialog.type;
			var url = "";
			if(type == "YSK"){
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
					{entryType: 'invoice', interId: interId},
					function(data){
						$.each(data.rows, function(idx,value){
							value.sourceBillNo = billNo;
							parent.$.modalDialog.dg.datagrid('appendRow',value);
						});
					},true);
			parent.$.modalDialog.selDlg.dialog('destroy');
		}else{
			updateSalesInvoiceDlg();
		}
	}
	
	function showDlg(title, iconCls, type, row, status){
		var viewPath = 'view/salesMgmt/salesInvoice/salesInvoiceEditDlg.jsp';
		var numMethod = "getSalesInvoiceNumber";
		var processDefKey = "sales-invoice-process-apply";
		var entityName = "ICSales";
		
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName);
	}
	
	function addSalesInvoiceDlg(){
		showDlg('添加发票','icon-add','add');
	}
	
	function updateSalesInvoiceDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改发票','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showSalesInvoiceDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('查看发票','icon-show','show',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delSalesInvoice(){
		var row = $dg.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return;
			}
			parent.$.messager.confirm($.erp.hint,$.erp.deleteQueryMsg,function(r){
				if(r){
					$.post('salesInvoice/delete',{id:row.interId},function(rsp){
						if(rsp.status){
							var idx = $dg.datagrid('getRowIndex',row);
							$dg.datagrid('deleteRow', idx);
						}
						$.erp.submitSuccess(rsp.title,rsp.message);
					},"json").error(function(){
						$.erp.submitErr();
					});
				}
			});
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function commitSalesInvoice(){
		return $.erp.commitBusinessList($dg);
	}
	
	function checkPending(){
		$.erp.checkPending($dg);
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
							<shiro:hasPermission name="salInvoiceAdd">
								<a id="addOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-add',plain:true" onclick="addSalesInvoiceDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salInvoiceMod">
								<a id="updOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-edit',plain:true" onclick="updateSalesInvoiceDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salInvoiceDel">
								<a id="delOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-remove',plain:true" onclick="delSalesInvoice();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salInvoiceShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-show" plain="true" onclick="showSalesInvoiceDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salInvoiceCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-datago" plain="true" onclick="commitSalesInvoice();">提交</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salInvoiceExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-excel" plain="true" onclick="hold();">导出Excel</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox">
						</td>
						<%-- <td>
							<shiro:hasPermission name="salOutStockCheck">
								<a href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
							</shiro:hasPermission>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="">高级查询</a> 
						</td> --%>
					</tr>
				</table>
			</div> 
			<div id="mm">
				<div name="billNo">发票号码</div>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>