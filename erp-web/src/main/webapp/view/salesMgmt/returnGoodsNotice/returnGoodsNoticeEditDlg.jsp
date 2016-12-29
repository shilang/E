<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
	var $dg;
	var $form;
	var entry;
	var date;
	var salReturnGoodsNoticeShowPrice = false;
	$(function(){
		initOtherPerm();
		$form = $("#form");
		$form.form({
			url: 'salesReturnGoodsNotice/persistence.action',
			onSubmit: submit,
			success: success
		});
		
		$dg = $("#dg");
		$dg.success = false;
		
		var entryArr = [[]];
		var currArr = [
		              {field:'quantity',title:'数量',sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{required:true}}},
		              {field:'price',title:'单价',hidden:!salReturnGoodsNoticeShowPrice,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{precision:2,required:true}}},
		              {field:'amount',title:'金额',hidden:!salReturnGoodsNoticeShowPrice,sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{precision:2,required:true}}},
		              {field:'fetchDate',title:'交货日期',width:parseInt($(this).width()*0.1),editor:{type:'datebox',options:{required:true}}},
		              {field:'stockId',title:'收货仓库',width:parseInt($(this).width()*0.1),editor:{type:'textbox',options:{editable:false}}},
		              {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.12),editor:{type:'textbox',options:{editable:false}}},
		              {field:'remark',title:'备注',width:parseInt($(this).width()*0.4),editor:'textbox'}
		              ];
		entryArr[0] = new $.erp.materialCol($dg).mergeCol(currArr);
		$dg.datagrid({
			url: 'salesReturnGoodsNotice/findEntriesById.action',
			width: 'auto',
			height: 260,
			rownumbers: true,
			collapsible: true,
			striped: true,
			border: true,
			singleSelect: true,
			nowrap: false,
			showFooter: true,
			idField: 'entryId',
			columns: entryArr,
			toolbar: '#tb',
			onClickRow: onClickRow
		});
		$dg.success = true;
		
		entry = new $.erp.entry($dg);
	});
	
	function init(){
		$("#salesScope").erpResGrid({}, 9);
		$("#salesWay").erpResGrid({}, 7);
		$("#fetchAddr").erpResGrid({}, 10);
		$("#customerId").erpCustomer();
		$("#currencyId").erpCurrency($("#exchangeRate"));
		date = $("#date").erpCurrDate(true);
		$("#managerId").erpEmployee();
		$("#employeeId").erpEmployee();
		$("#checker").erpUsers(true);
		$("#creater").erpUsers(true);
		
		var selUrl = "";
		$("#sourceType").combobox({
			width: 176,
			valueField: 'value',
			textField: 'text',
			editable: false,
			data: [{
				text: '',
				value: '',
			},{
				text: '发货通知',
				value: '发货通知',
			}],
			onSelect: function(record){
				$("#sourceBillNo").textbox("setValue","");
				$.erp.dgClean($dg);
				if(record.value == '发货通知'){
					selUrl = "view/salesMgmt/salesOutStockNotice/salesOutStockNoticeMain.jsp?selSta=true";
				}else{
					selUrl = "";
				}
			}
		});
		
		$("#sourceBillNo").textbox({
			width: 176,
			editable: false,
			buttonText: '浏览',
			buttonIcon: 'icon-search',
			onClickButton: function(){
				//clean data
				$.erp.dgClean($dg);
				if("" == selUrl){
					return;
				}
				var iframe = "<iframe src='" + selUrl + "' frameborder=\"0\" style=\"border:0;width:100%;height:99.4%;\"></iframe>";
				var $dlg =  $('<div/>').dialog({   
					title: '请选择记录',    
					width: 800,    
					height: 600,    
					closed: false,    
					cache: false,  
					content: iframe,
					modal: true
					});	
				parent.$.modalDialog.type = "THTZ";
				parent.$.modalDialog.date = date.datebox('getValue');
				parent.$.modalDialog.selDlg = $dlg;
				parent.$.modalDialog.dg = $dg;
			}
		});
	}
	
	function initOtherPerm(){
		if($('#salReturnGoodsNoticeShowPrice').length > 0){
			salReturnGoodsNoticeShowPrice = true;
		}
	}
	
	function gridRowTotal(index){
		$.erp.gridRowTotal($dg,index,['quantity','price'],'amount','*');
	}
	
	function appendRow(){
		entry.appendRow();
		var index = $dg.datagrid('getRows').length - 1;
		gridRowTotal(index);
	}
	
	function removeRow(){
		entry.removeRow();
	}
	
	function rejectRows(){
		entry.rejectRows();
	}
	
	function onClickRow(index){
		entry.onClickRow(index);
		gridRowTotal(index);
	}
	
	function submit(entryRows){
		entry.saveRows();
		return entry.submit(entryRows, $form);
	}
	
	function success(result){
		entry.success(result, process, true);
	}
	
	function process(){
		entry.acceptRows();
		parent.$.modalDialog.openner.datagrid('reload');
		parent.$.modalDialog.handler.dialog('close');
	}
	
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="interId" name="interId" type="hidden"/>
		<input id="created" name="created" type="hidden"/>
		<input id="status" name="status" type="hidden"/>
		<table class="simple">
			<tr>
				<th>编号</th>
				<td>
					<input id="billNo" name="billNo" class="easyui-textbox" data-options="required:true,editable:false"/>
				</td>
				
				<th>购货单位</th>
				<td>
					<input id="customerId" name="customerId" class="easyui-textbox" data-options="required:true"/>
				</td>
				
				<th>退料原因</th>
				<td rowspan="3">
					<!-- 
					<input id="Note" name="Note" class="easyui-textbox" data-options="required:true"/>
					 -->
					 <textarea id="note" name="note" style="width: 170px; height: 75px;"></textarea>
				</td>
			</tr>
			<tr>
				<th>销售方式</th>
				<td>
					<input id="salesWay" name="salesWay" class="easyui-textbox" data-options="required:true"/>
				</td>
				
				<th>交货地点</th>
				<td>
					<input id="fetchAddr" name="fetchAddr" class="easyui-textbox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>运输方式</th>
				<td >
					<input id="salesScope" name="salesScope" class="easyui-textbox" data-options="required:true" />
				</td>
				
				<th>收货仓库</th>
				<td>
					<input id="" name="" class="easyui-textbox" data-options="editable:false"/>
				</td>
			</tr>	
			<tr>
				<th>源单类型</th>
				<td>
					<input id="sourceType" name="sourceType" class="easyui-combobox"/>
				</td>
				<th>选单号</th>
				<td>
					<input id="sourceBillNo" name="sourceBillNo" class="easyui-textbox"/>
				</td>
				
				<th>币别</th>
				<td>
					<input id="currencyId" name="currencyId" class="easyui-textbox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>摘要</th>
				<td>
					<input id="explanation" name="explanation" class="easyui-textbox" />
				</td>
				
				<th>部门</th>
				<td>
					<input id="departmentId" name="departmentId" class="easyui-combotree" data-options="required:true"/>
				</td>
				
				<th>汇率</th>
				<td>
					<input id="exchangeRate" name="exchangeRate" class="easyui-numberbox" data-options="required:true,precision:2"/>
				</td>
			</tr>
			<tr>
			
				<th>主管</th>
				<td>
					<input id="managerId" name="managerId" class="easyui-textbox" data-options="required:true"/>
				</td>
				
				
				<th>业务员</th>
				<td>
					<input id="employeeId" name="employeeId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>日期</th>
				<td>
					<input id="date" name="date" class="easyui-datebox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>审核</th>
				<td>
					<input id="checker" name="checker" class="easyui-textbox" data-options="editable:false"/>
				</td>
				<th>审核日期</th>
				<td>
					<input id="checkDate" name="checkDate" class="easyui-textbox" data-options="editable:false"/>
				</td>
					<th>制单</th>
				<td>
					<input id="creater" name="creater" class="easyui-combogrid" data-options="required:true"/>
				</td>
			</tr>
		</table>
		<div style="margin-top: 5px; margin-bottom:5px;">
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendRow();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeRow();">删除</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="rejectRows();">撤销</a>
						</td>
					</tr>
				</table>
			</div>
			<table id="dg" title="项目内容"></table>
		</div>
		<div class="easyui-panel" data-options="title:'改单记录',collapsible:true,collapsed:true">
			<textarea id="changeReason" name="changeReason" readonly="readonly" style="margin:0;padding:0;border:0;width:100%;height:150px;"></textarea>
		</div>
	</form>
	<shiro:hasPermission name="salReturnGoodsNoticeShowPrice">
		<label id="salReturnGoodsNoticeShowPrice"></label>
	</shiro:hasPermission>
</div>