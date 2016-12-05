<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $dg;
	var $form;
	var entry;
	var date;
	
	$(function(){
		$form = $("#form");
		$form.form({
			url: 'salesOutStockNotice/persistence.action',
			onSubmit: submit,
			success: success
		});
		
		$dg = $("#dg");
		$dg.success = false;
		
		var entryArr = [[]];
		var currArr = [
		              {field:'quantity',title:'数量',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{required:true}}},
		              {field:'price',title:'单价',width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{required:true,precision:2}}},
		              {field:'amount',title:'金额',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{required:true,precision:2}}},
		              {field:'remark',title:'备注',width:parseInt($(this).width()*0.1),editor:'textbox'},
		              {field:'fetchDate',title:'交货日期',width:parseInt($(this).width()*0.1),editor:{type:'datebox',options:{required:true}}},
		              {field:'stockId',title:'仓库',width:parseInt($(this).width()*0.1),editor:{type:'textbox',options:{editable:false}}},
		              {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.1),editor:{type:'textbox',options:{editable:false}}}
		              ];
		entryArr[0] = new $.erp.materialCol($dg).mergeCol(currArr);
		$dg.datagrid({
			url: 'salesOutStockNotice/findEntriesById.action',
			width: 'auto',
			height: 260,
			rownumbers: true,
			collapsible: true,
			striped: true,
			border: true,
			singleSelect: true,
			nowrap: false,
			showFooter:true,
			idField: 'entryId',
			columns: entryArr,
			toolbar: '#tb',
			onClickRow: onClickRow
		});
		$dg.success = true;
		
		entry = new $.erp.entry($dg);
	});
	
	function init(){
		$("#fetchAddr").erpResGrid({},10,true);
		$("#salesScope").erpResGrid({}, 9);
		$("#salesWay").erpResGrid({}, 7);
		$("#settlementId").erpResGrid({}, 6);
		$("#currencyId").erpCurrency($("#exchangeRate"));
		$("#customerId").erpCustomer();
		date = $("#date").erpCurrDate();
		$("#managerId").erpEmployee();
		$("#employeeId").erpEmployee();
		$("#checker").erpUsers(true);
		$('#creater').erpUsers(true);
		
		var selUrl = "";
		$("#sourceType").combobox({
			width: 176,
			valueField:'value',
			textField:'text',
			editable: false,
			data: [{
				text:'',
				value:''
			},{
				text: '销售订单',
				value:'销售订单'
			}],
			onSelect: function(record){
				$("#sourceBillNo").textbox('setValue',"");
				$.erp.dgClean($dg);
				if(record.value == '销售订单'){
					selUrl = "view/salesMgmt/salesOrder/salesOrderMain.jsp?selSta=true";
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
				parent.$.modalDialog.type = 'FHTZ';
				parent.$.modalDialog.date = date.datebox('getValue');
				parent.$.modalDialog.selDlg = $dlg;
				parent.$.modalDialog.dg = $dg; 
			}
		});
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
				<th>交货地点</th>
				<td>
					<input id="fetchAddr" name="fetchAddr" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>运输方式</th>
				<td>
					<input id="salesScope" name="salesScope" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>编号</th>
				<td>
					<input id="billNo" name="billNo" class="easyui-textbox" data-options="required:true,editable:false"/>
				</td>
			</tr>
			<tr>
				<th>销售方式</th>
				<td>
					<input id="salesWay" name="salesWay" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>结算方式</th>
				<td>
					<input id="settlementId" name="settlementId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>币别</th>
				<td>
					<input id="currencyId" name="currencyId" class="easyui-textbox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>购货单位</th>
				<td>
					<input id="customerId" name="customerId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>仓库</th>
				<td>
					<input id="stockId" name="stockId" class="easyui-textbox" data-options="editable:false"/>
				</td>
				<th>汇率</th>
				<td>
					<input id="exchangeRate" name="exchangeRate" class="easyui-numberbox" data-options="required:true,precision:2"/>
				</td>
			</tr>	
			<tr>
				<th>摘要</th>
				<td>
					<input id="explanation" name="explanation" class="easyui-textbox" />
				</td>
				<th>源单类型</th>
				<td>
					<input id="sourceType" name="sourceType" class="easyui-combobox"/>
				</td>
				<th>选单号</th>
				<td>
					<input id="sourceBillNo" name="sourceBillNo" class="easyui-textbox"/>
				</td>
			</tr>
			<tr>
				<th>日期</th>
				<td>
					<input id="date" name="date" class="easyui-datebox" data-options="required:true"/>
				</td>
				<th>主管</th>
				<td>
					<input id="managerId" name="managerId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>部门</th>
				<td>
					<input id="departmentId" name="departmentId" class="easyui-combotree" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>业务员</th>
				<td>
					<input id="employeeId" name="employeeId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>审核</th>
				<td>
					<input id="checker" name="checker" class="easyui-textbox" data-options="editable:false"/>
				</td>
				<th>审核日期</th>
				<td>
					<input id="checkDate" name="checkDate" class="easyui-textbox" data-options="editable:false"/>
				</td>
			</tr>
			<tr>
				<th>制单</th>
				<td>
					<input id="creater" name="creater" class="easyui-combogrid" data-options="required:true"/>
				</td>
			</tr>
		</table>
	</form>
	
	<div style="margin-top: 12px;">
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
</div>