<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $dg;
	var $form;
	var entry;
	$(function(){

		$form = $('#form');
		$form.form({
			url: 'salesInvoice/persist',
			onSubmit: submit,
			success: success
		});
		
		$dg = $('#dg');
		$dg.success = false;
		
		var entryArr = [[]];
		var currArr = [
		               {field:'quantity',title:'数量',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{required:true}}},
		               {field:'price',title:'单价',width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'amount',title:'金额',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'remark',title:'备注',width:parseInt($(this).width()*0.1),editor:'textbox'},
		               {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.1),editor:{type:'textbox',options:{editable:false}}}
		               ];
		entryArr[0] = new $.erp.materialCol($dg).mergeCol(currArr);

		$dg.datagrid({
			url: 'salesInvoice/entries',
			width: 'auto',
			height: 260,
			rownumbers: true,
			collapsible: true,
			triped: true,
			border:true,
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
		$("#customerId").erpCustomer();
		$("#settleDate").erpCurrDate();
		$("#currencyId").erpCurrency($("#exchangeRate"));
		$("#settleId").erpResGrid({}, 6);
		$("#salesWay").erpResGrid({}, 7);
		$("#fetchAddr").erpResGrid({},10);
		$("#date").erpCurrDate();
		$("#managerId").erpEmployee();
		$("#checker").erpUsers(true);
		$("#employeeId").erpEmployee();
		$("#creater").erpUsers(true);
		
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
				text:'销售订单',
				value: '销售订单'
			},{
				text:'销售出库',
				value: '销售出库'
			},{
				text: '合同(应收)',
				value:'合同(应收)'
			}],
			onSelect: function(record){
				$("#sourceBillNo").textbox("setValue","");
				//clear data
				$.erp.dgClean($dg);		
				if(record.value == '销售订单'){
					selUrl = "view/salesMgmt/salesOrder/salesOrderMain.jsp?selSta=true";
				}else if(record.value == '销售出库'){
					selUrl = "view/salesMgmt/salesOutStock/salesOutStockMain.jsp?selSta=true";
				}else if(record.value == '合同(应收)'){
					selUrl = "view/salesMgmt/salesContract/salesContractMain.jsp?selSta=true";
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
				parent.$.modalDialog.type = "XSFP";
				//parent.$.modalDialog.date = date.datebox('getValue');
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
				<th>销售方式</th>
				<td>
					<input id="salesWay" name="salesWay" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>收款日期</th>
				<td>
					<input id="settleDate" name="settleDate" class="easyui-datebox" data-options="required:true"/>
				</td>
				<th>发票号码</th>
				<td>
					<input id="billNo" name="billNo" class="easyui-textbox" data-options="required:true,editable:false"/>
				</td>
			</tr>
			<tr>
				<th>购货单位</th>
				<td>
					<input id="customerId" name="customerId" class="easyui-textbox" data-options="required:true"/>
				</td>
	
				<th>结算方式</th>
				<td>
					<input id="settleId" name="settleId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>币别</th>
				<td>
					<input id="currencyId" name="currencyId" class="easyui-textbox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				
				<th>纳税号</th>
				<td>
					<input id="ratepayingNum" name="ratepayingNum" class="easyui-textbox" data-options="required:false,editable:false"/>
				</td>
				
				<th>开户银行</th>
				<td>
					<input id="bank" name="bank" class="easyui-textbox" data-options="required:false,editable:false"/>
				</td>

				<th>汇率</th>
				<td>
					<input id="exchangeRate" name="exchangeRate" class="easyui-numberbox" data-options="required:true,precision:2"/>
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td>
					<input id="address" name="address" class="easyui-textbox" data-options="required:false,editable:false"/>
				</td>
				
				<th>往来科目</th>
				<td>
					<input id="subject" name="subject" class="easyui-textbox" data-options="required:false,editable:false"/>
				</td>
				
				<th>年利率</th>
				<td>
					<input id="ratePerYear" name="ratePerYear" class="easyui-textbox" data-options="required:false,editable:false"/>
				</td>
			</tr>
			<tr>
				<th>源单类型</th>
				<td>
					<input id="sourceType" name="sourceType" class="easyui-combobox" />
				</td>
				<th>选单号</th>
				<td>
					<input id="sourceBillNo" name="sourceBillNo" class="easyui-textbox" />
				</td>
				<th>日期</th>
				<td>
					<input id="date" name="date" class="easyui-datebox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>主管</th>
				<td>
					<input id="managerId" name="managerId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>部门</th>
				<td>
					<input id="departmentId" name="departmentId" class="easyui-combotree" data-options="requried:true" />
				</td>
				<th>业务员</th>
				<td>
					<input id="employeeId" name="employeeId" class="easyui-textbox" data-options="required:true"/>
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
			<tr>
				<th>摘要</th>
				<td>
					<input id="explanation" name="explanation" class="easyui-textbox"/>
				</td>
			</tr>
		</table>
	</form>
	<div style="margin-top: 12px;">
		<div id="tb">
			<table>
				<tr>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendRow();">添加</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeRow();">删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="rejectRows();">撤销</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<table id="dg" title="项目内容"></table>
</div>