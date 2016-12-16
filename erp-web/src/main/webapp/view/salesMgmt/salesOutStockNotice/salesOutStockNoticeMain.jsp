<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>发货通知</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var selSta = <%=request.getParameter("selSta")%>;
	var $dg;
	var $grid;
	//var $entry;
	var queryParams = {sort:'interId',order:'desc'};
	$(function(){
		if(selSta){
			$(".submain").hide();
			$.extend(queryParams, $.erp.searcher('result', $.erp.resultCheckOk,'int'));
		}
		
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url:'salesOutStockNotice/findAll.action',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pageSize: 20,
			pagination: true,
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
			        {field:'customerName',title:'购货单位',width:parseInt($(this).width()*0.15),sortable:true},
			        {field:'salesScopeName',title:'运输方式',width:parseInt($(this).width()*0.18),sortable:true},
			        {field:'salesWayName',title:'销售方式',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'fetchAddrName',title:'交货地点',width:parseInt($(this).width()*0.1),sortable:true},
			        {field:'stockName',title:'仓库',width:parseInt($(this).width()*0.1)},
			        {field:'managerName',title:'主管',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'departmentName',title:'部门',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'employeeName',title:'业务员',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'createrName',title:'制单',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'settlementName',title:'结算方式',width:parseInt($(this).width()*0.1)},
			        {field:'currencyName',title:'币别',width:parseInt($(this).width()*0.06)},
			        {field:'exchangeRate',title:'汇率',width:parseInt($(this).width()*0.06)},
			        {field:'checkerName',title:'审核人',width:parseInt($(this).width()*0.08),sortable:true},
			        {field:'checkDate',title:'审核日期',width:parseInt($(this).width()*0.16),sortable:true},
			        {field:'sourceType',title:'源单类型',width:parseInt($(this).width()*0.1)},
			        {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.12)},
			        {field:'explanation',title:'摘要',width:parseInt($(this).width()*0.4)}
			        ]],
			toolbar: '#tb',
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
	
	/*
	function onClickRow(index, row){
		$entry.datagrid('reload',{parentId:row.interId});
	}*/
	
	function onDblClickRow(index, row){
		if(selSta){
			var billNo = row.billNo;
			var interId = row.interId;
			parent.$.modalDialog.handler.find("#sourceBillNo").textbox("setValue", billNo);
			var type = parent.$.modalDialog.type;
			var url = "";
			if (type == "THTZ"){
				url = "salesReturnGoodsNotice/findEntriesByType.action";
			}else if (type = "XSCK"){
				url = "salesOutStock/findEntriesByType.action";
			}else{
				return;
			}
			//load form data
			var f = parent.$.modalDialog.handler.find('#form');
			$.erp.excludeAttrForFormFill(row);
			f.form('load', row);
			
			//load entry data
			$.erp.ajax(url,
					{entryType:'outnotice',interId:interId},
					function(data){
						$.each(data.rows,function(idx, value){
							value.fetchDate = parent.$.modalDialog.date;
							value.sourceBillNo = billNo;
							parent.$.modalDialog.dg.datagrid('appendRow',value);
						});
					},true);
			parent.$.modalDialog.selDlg.dialog("destroy");
		}else{
			updateSalesOutStockNoticeDlg();
		}
	}
	
	function addSalesOutStockNoticeDlg(){
		showDlg('添加发货通知单','icon-add','add');
	}
	
	function updateSalesOutStockNoticeDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改发货通知单','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showSalesOutStockNoticeDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('查看发货通知单','icon-show','show',row);
		}
	}
	
	function showDlg(title,iconCls,type,row,status){
		var viewPath = 'view/salesMgmt/salesOutStockNotice/salesOutStockNoticeEditDlg.jsp';
		var numMethod = "getSalesOutStockNoticeNumber";
		var processDefKey = "sales-outstock-notice-process-apply";
		var entityName = "SalesOutStock";
		
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName);
	
	}
	
	function delSalesOutStockNotice(){
		var row = $dg.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return;
			}
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("salesOutStockNotice/delete.action",{interId:row.interId},
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
	
	function commitSalesOutStockNotice(){
		return $.erp.commitBusinessList($dg);
	}
	
	function baseCost(){
		var row = $dg.datagrid('getSelected');
		if(row){
			var formData = null;
			var taskId = '';
			var url = 'transportInfo/findByPid';
			var params = {pid:row.interId};
			var url2 = 'task/getTaskId';
			var params2 = {processInstanceId:row.procInstId,key:'salesOutStockNoticeBaseCostCommit'};
		    $.when($.ajax({url:url, data:params}), $.ajax({url:url2, data:params2})).done(function(d1, d2){
				formData = d1[0].rows[0];		
				taskId = d2[0].message;
		    }).done(function(){
		    	var perm = taskId && $('#chuanwu').val()?true:false;
		    	parent.$.modalDialog({
					title: '基本费用',
					width: 800,
					height: 520,
					href: 'view/salesMgmt/salesOutStockNotice/baseCostEditDlg.jsp',
					buttons: [{
						text: '保存',
						iconCls: 'icon-save',
						disabled: !perm,
						width: 80,
						handler: function(){
							var f = parent.$.modalDialog.handler.find('#form');
							f.find('#taskId').val(taskId);
							f.submit();
						}
					},{
						text: '取消',
						iconCls: 'icon-cancel',
						width: 80,
						handler: function(){
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}],
					onLoad: function(){
						parent.init();
						var $interId = parent.$.modalDialog.handler.find('#interId');
						$interId.val(row.interId); 
						if(formData && formData.id){
							var $f = parent.$.modalDialog.handler.find('#form');
							$f.form('load', formData);
						}
					}
				});
		    });
		}
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
							<shiro:hasPermission name="salOutNoticeAdd">
								<a id="addOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-add',plain:true" onclick="addSalesOutStockNoticeDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutNoticeMod">
								<a id="updOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-edit',plain:true" onclick="updateSalesOutStockNoticeDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutNoticeDel">
								<a id="delOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-remove',plain:true" onclick="delSalesOutStockNotice();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutNoticeShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-show" plain="true" onclick="showSalesOutStockNoticeDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOutNoticeCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-datago" plain="true" onclick="commitSalesOutStockNotice();">提交</a>
							</shiro:hasPermission>
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-datago" plain="true" onclick="baseCost();">基本费用</a>
							<shiro:hasPermission name="salOutNoticeExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-excel" plain="true" onclick="hold();">导出Excel</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox">
						</td>
						<td>
							<shiro:hasPermission name="salOutNoticeCheck">
								<a id="pendingOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
							</shiro:hasPermission>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="">高级查询</a>
							<shiro:hasPermission name="chuanwu">
								<input id="chuanwu" type="hidden" value="chuanwu">
							</shiro:hasPermission>
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