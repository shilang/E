<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>退货通知</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	//var $entry;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url:'salesReturnGoodsNotice/findAll.action',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pagination: true,
			rownumbers: true,
			striped: true,
			border: false,
			singleSelect: true,
			queryParams: {sort:'interId',order:'desc'},
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
			        {field:'note',title:'退料原因',width:parseInt($(this).width()*0.1)},
			        {field:'stockName',title:'收货仓库',width:parseInt($(this).width()*0.1)},
			        {field:'fetchAddrName',title:'交货地点',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'salesScopeName',title:'销售范围',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'salesWayName',title:'销售方式',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'currencyName',title:'币别',width:parseInt($(this).width()*0.1)},
			        {field:'exchangeRate',title:'汇率',width:parseInt($(this).width()*0.1)},
			        {field:'managerName',title:'主管',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'departmentName',title:'部门',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'employeeName',title:'业务员',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'createrName',title:'制单',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'cancellation',title:'作废标志',width:parseInt($(this).width()*0.1)},
			        {field:'checkerName',title:'审核人',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'checkDate',title:'审核日期',width:parseInt($(this).width()*0.14),sortable:true},
			        {field:'sourceType',title:'源单类型',width:parseInt($(this).width()*0.1)},
			        {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.1)},
			        {field:'explanation',title:'摘要',width:parseInt($(this).width()*0.1)}
			        ]],
			toolbar: '#tb',
			//onClickRow: onClickRow
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
		updateReturnGoodsNoticeDlg();
	}
	
	function addReturnGoodsNoticeDlg(){
		showDlg('添加退货通知单','icon-add','add');
	}
	
	function updateReturnGoodsNoticeDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改退货通知单','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showReturnGoodsNoticeDlg(){
		var row = $dg.datagrid('getSelected')
		if(row){
			showDlg('查看退货通知单','icon-show','show',row);
		}
	}
	
	function showDlg(title,iconCls,type,row,status){
		var viewPath = 'view/salesMgmt/returnGoodsNotice/returnGoodsNoticeEditDlg.jsp';
		var numMethod = "getSalesReturnGoodsNoticeNumber";
		var processDefKey = "sales-returngoods-notice-process-apply";
		var entityName = "SalesOutStock";
		
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName);
	}
	
	function delReturnGoodsNotice(){
		var row = $dg.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return;
			}
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("salesReturnGoodsNotice/delete.action",{interId:row.interId},
					function(rsp){
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
	
	function commitReturnGoodsNotice(){
		return $.erp.commitBusinessList($dg);
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
							<shiro:hasPermission name="salReturnNoticeAdd">
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addReturnGoodsNoticeDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salReturnNoticeMod">
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateReturnGoodsNoticeDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salReturnNoticeDel">
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delReturnGoodsNotice();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salReturnNoticeShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-show" plain="true" onclick="showReturnGoodsNoticeDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salReturnNoticeCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-datago" plain="true" onclick="commitReturnGoodsNotice();">提交</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salReturnNoticeExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="hold();">导出Excel</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox">
						</td>
						<td>
							<shiro:hasPermission name="salReturnNoticeCheck">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
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