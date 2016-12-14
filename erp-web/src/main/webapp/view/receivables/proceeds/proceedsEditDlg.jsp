<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $dg;
	var $form;
	var entry;
	var $amount;
	var $settleAmount;
	$(function(){
		
		$amount = $('#amount');
		$settleAmount = $('#settleAmount');
		
		$form = $('#form');
		$form.form({
			url: 'proceeds/persist',
			onSubmit: submit,
			success: success
		});
		
		$dg = $('#dg');
		$dg.success = false;

		var entryArr = [[]];
		var currArr = [
		               {field:'quantity',title:'数量',sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{required:true}}},
		               {field:'price',title:'单价',width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'amount',title:'金额',sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'settleQty',title:'结算数量',sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{required:true}}},
		               {field:'settleAmount',title:'结算实收金额',sum:true,width:parseInt($(this).width()*0.08),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.12),editor:{type:'textbox',options:{editable:false}}},
		               {field:'remark',title:'备注',width:parseInt($(this).width()*0.4),editor:'textbox'}
		               ];
		entryArr[0] = new $.erp.materialCol($dg).mergeCol(currArr);

		$dg.datagrid({
			url: 'proceeds/entries',
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
		$('#settleCurrency').erpCurrency();
		$("#settleId").erpResGrid({}, 6);
		$("#salesWay").erpResGrid({}, 7);
		$("#fetchAddr").erpResGrid({},10);
		$("#date").erpCurrDate(true);
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
				text: '合同(应收)',
				value:'合同(应收)'
			},{
				text:'销售发票',
				value: '销售发票'
			}],
			onSelect: function(record){
				$("#sourceBillNo").textbox("setValue","");
				//clear data
				$.erp.dgClean($dg);		
				if(record.value == '销售订单'){
					selUrl = "view/salesMgmt/salesOrder/salesOrderMain.jsp?selSta=true";
				}else if(record.value == '销售发票'){
					selUrl = "view/salesMgmt/salesInvoice/salesInvoiceMain.jsp?selSta=true";
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
				parent.$.modalDialog.type = "YSK";
				//parent.$.modalDialog.date = date.datebox('getValue');
				parent.$.modalDialog.selDlg = $dlg;
				parent.$.modalDialog.dg = $dg;
			}
		});
	}
	
	function gridRowTotal(index){
		$.erp.gridRowTotal($dg,index,['quantity','price'],'amount','*');
		$.erp.gridRowTotal($dg,index,['settleQty','price'],'settleAmount','*');
	}
	
	function updateAmount(){
		var rows = $dg.datagrid('getFooterRows');
		if(rows){
			$amount.numberbox('setValue', rows[0].amount);
			$settleAmount.numberbox('setValue', rows[0].settleAmount);
		}
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
		updateAmount();
	}
	
	function submit(entryRows){
		entry.saveRows();
		updateAmount();
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
				<th>单据号</th>
				<td>
					<input id="billNo" name="billNo" class="easyui-textbox" data-options="required:true"/>
				</td>
				
				<th>日期</th>
				<td>
					<input id="date" name="date" class="easyui-datebox" data-options="required:true"/>
				</td>
				
				<th>项目类别</th>
				<td>
					<select id="subject" name="subject" class="easyui-combobox" data-options="width:176,required:true,editable:false">
						<option value="客户" selected="selected">客户</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<th>核算项目</th>
				<td>
					<input id="customerId" name="customerId" class="easyui-textbox" data-options="required:true"/>
				</td>
			
				<th>结算方式</th>
				<td>
					<input id="settleId" name="settleId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>财务日期</th>
				<td>
					<input id="settleDate" name="settleDate" class="easyui-datebox" data-options="required:false"/>
				</td>
			</tr>
			<tr>	
				<th>项目开户行</th>
				<td>
					<input id="custBank" name="custBank" class="easyui-textbox" data-options="required:false"/>
				</td>
				<th>项目账号</th>
				<td>
					<input id="custBankNum" name="custBankNum" class="easyui-textbox" data-options="required:false"/>
				</td>
				<th>币别</th>
				<td>
					<input id="currencyId" name="currencyId" class="easyui-textbox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<!-- <th>现金类科目</th>
					<td>
						<input id="cashSubject" name="cashSubject" class="easyui-textbox" data-options="required:false"/>
			    </td> -->
				<th>收款银行</th>
				<td>
					<input id="bank" name="bank" class="easyui-textbox" data-options="required:false"/>
				</td>
				<th>账号</th>
				<td>
					<input id="bankNum" name="bankNum" class="easyui-textbox" data-options="required:false"/>
				</td>
				<th>汇率</th>
				<td>
					<input id="exchangeRate" name="exchangeRate" class="easyui-numberbox" data-options="required:true,precision:2"/>
				</td>
			</tr>
			<tr>
				<th>单据金额</th>
				<td>
					<input id="amount" name="amount" class="easyui-numberbox" data-options="required:true,editable:false,precision:2,value:0"/>
				</td>
				<th>货运金额</th>
				<td>
					<input id="freightAmount" name="freightAmount" class="easyui-numberbox" data-options="required:true,editable:false,precision:2,value:0"/>
				</td>
				<th>总金额</th>
				<td>
					<input id="totalAmount" name="totalAmount" class="easyui-numberbox" data-options="required:true,editable:false,precision:2,value:0"/>
				</td>
			</tr>
			<tr>
				<!-- <th>收款类型</th>
				<td>
					<input id="recType" name="recType" class="easyui-textbox" data-options="editable:false"/>
					<select id="recType" name="recType" class="easyui-combobox" data-options="width:176,required:true">
						<option selected="selected">转账</option>
						<option>销售回款</option>
					</select>
				</td> -->
				<th>实收金额</th>
				<td>
					<input id="settleAmount" name="settleAmount" class="easyui-numberbox" data-options="required:true,precision:2,value:0"/>
				</td>
				<th>银行费用</th>
				<td>
					<input id="bankCost" name="bankCost" class="easyui-numberbox" data-options="required:true,precision:2,value:0"/>
				</td>
				<th>实收币别</th>
				<td>
					<input id="settleCurrency" name="settleCurrency" class="easyui-textbox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>源单类型</th>
				<td>
					<input id="sourceType" name="sourceType" class="easyui-combobox" />
				</td>
				<th>源单号</th>
				<td>
					<input id="sourceBillNo" name="sourceBillNo" class="easyui-textbox" />
				</td>
				<th>摘要</th>
				<td>
					<input id="explanation" name="explanation" class="easyui-textbox" data-options="required:false"/>
				</td>
			</tr>
			<tr>
				<th>部门</th>
				<td>
					<input id=departmentId name="departmentId" class="easyui-combotree" data-options="required:true"/>
				</td>
				<th>业务员</th>
				<td>
					<input id="employeeId" name="employeeId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>审核人</th>
				<td>
					<input id="checker" name="checker" class="easyui-textbox" data-options="editable:false"/>
				</td>
			</tr>
			<tr>
				<th>审核日期</th>
				<td>
					<input id="checkDate" name="checkDate" class="easyui-textbox" data-options="editable:false"/>
				</td>
				<th>制单人</th>
				<td>
					<input id="creater" name="creater" class="easyui-combogrid" data-options="required:true"/>
				</td>
				<th>备注</th>
				<td>
					<input id="remark" name="remark" class="easyui-textbox"/>
				</td>
			</tr>
		</table>
		<div style="margin-top: 5px; margin-bottom:5px;">
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
			<table id="dg" title="项目内容"></table>
		</div>
		<div class="easyui-panel" data-options="title:'改单记录',collapsible:true,collapsed:true">
			<textarea id="changeReason" name="changeReason" readonly="readonly" style="margin:0;padding:0;border:0;width:100%;height:150px;"></textarea>
		</div>
	</form>	
</div>