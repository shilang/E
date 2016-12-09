<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>收款单</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $('#dg');
		$grid = $dg.datagrid({
			url: 'proceeds/list',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pageSize: 20,
			pagination: true,
			rownumbers: true,
			striped: true,
			border: false,
			singleSelect: true,
			queryParams: {sort: 'interId', order: 'desc'},
			idField: 'interId',
			columns: [[
			               {field: 'result', title: '状态', width: parseInt($(this).width() * 0.06),
							formatter:function(value, row){
								var status = $.erp.getResultStatus(value);
								return '<span style="color:'+status.color+';">' + status.msg + '</span>';
						   },sortable:true},
			        	   {field:'billNo',title:'单据号',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'date',title:'日期',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'subject',title:'核算项目类别',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'customerName',title:'核算项目',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'custBank',title:'核算项目开户银行',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'custBankNum',title:'核算项目银行账号',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'settleDate',title:'财务日期',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'settleName',title:'结算方式',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'currencyName',title:'币别',width:parseInt($(this).width()*0.1)},
				        	{field:'exchangeRate',title:'汇率',width:parseInt($(this).width()*0.1)},
				        	{field:'amount',title:'单据金额',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'settleAmount',title:'实收金额',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'bankCost',title:'银行费用',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'settleCurrencyName',title:'实收币别',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'bank',title:'收款银行',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'bankNum',title:'账号',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'explanation',title:'摘要',width:parseInt($(this).width()*0.1)},
				        	{field:'departmentName',title:'部门',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'employeeName',title:'业务员',width:parseInt($(this).width()*0.1),sortable:true},
				        	/* {field:'recType',title:'收款类型',width:parseInt($(this).width()*0.1),sortable:true}, */
				        	{field:'remark',title:'备注',width:parseInt($(this).width()*0.1),sortable:true},
				        	/* {field:'cashSubject',title:'现金类科目',width:parseInt($(this).width()*0.1),sortable:true}, */
				        /* 	{field:'settleNum',title:'结算号',width:parseInt($(this).width()*0.1),sortable:true}, */
				        	{field:'createrName',title:'制单人',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'checkerName',title:'审核人',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'checkDate',title:'审核日期',width:parseInt($(this).width()*0.16),sortable:true}
				        	/* {field:'',title:'收款币别',width:parseInt($(this).width()*0.1),sortable:true},
				        	{field:'',title:'收款金额',width:parseInt($(this).width()*0.1),sortable:true} */
			         ]],
			toolbar: '#tb',
			onDblClickRow: onDblClickRow
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
		updateProceedsDlg();
	}
	
	function showDlg(title, iconCls, type, row, status){
		
		var viewPath = 'view/receivables/proceeds/proceedsEditDlg.jsp';
		var numMethod = "getRecProceedsNumber";
		var processDefKey = "proceeds-process-apply";
		var entityName = "RecProceeds";
		
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName,820);
	}
	
	function addProceedsDlg(){
		showDlg('添加应收单','icon-add','add');
	}
	
	function updateProceedsDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改应收单','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showProceedsDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('查看应收单','icon-show','show',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delProceeds(){
		var row = $dg.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return;
			}
			parent.$.messager.confirm($.erp.hint,$.erp.deleteQueryMsg,function(r){
				if(r){
					$.post('proceeds/delete',{id:row.interId},function(rsp){
						if(rsp.status){
							var idx = $dg.datagrid('getRowIndex',row);
							$dg.datagrid('deleteRow', idx);
						}
						$.erp.submitSuccess(rsp.title, rsp.message);
					},"json").error(function(){
						$.erp.submitErr();
					});
				}
			});
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function commitProceeds(){
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
							<shiro:hasPermission name="recProceedsAdd">
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addProceedsDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="recProceedsMod">
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateProceedsDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="recProceedsDel">
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delProceeds();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="recProceedsShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-show" plain="true" onclick="showProceedsDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="recProceedsCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-datago" plain="true" onclick="commitProceeds();">提交</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="recProceedsExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="hold();">导出Excel</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox">
						</td>
						<td>
							<shiro:hasPermission name="recProceedsCheck">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
							</shiro:hasPermission>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="">高级查询</a> 
						</td>
					</tr>
				</table>
			</div> 
			<div id="mm">
				<div name="billNo">单据号</div>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>