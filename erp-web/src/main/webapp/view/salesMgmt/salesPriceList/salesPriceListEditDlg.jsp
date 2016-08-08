<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $dg;
	var $form;
	var entry;
	$(function(){
		
			$form = $("#form");
			$form.form({
				url: 'salesPriceList/persistence.action',
				onSubmit: submit,
				success: success
			});
			
			$dg = $("#dg");
			$dg.success = false;
			
			var entryArr = [[]];
			
			var currArr = [{field: 'quantity', title: '数量',sum: true, width: parseInt($(this).width() * 0.06), 
						  editor: {
							  type: 'textbox', 
							  options:{
								  required: true
								  }}},
							{field: 'price', title: '单价', width: parseInt($(this).width() * 0.1), editor: {type: 'numberbox', options:{required: true, precision: 2}}},
							{field: 'amount', title: '金额', sum:true, width: parseInt($(this).width() * 0.1), editor: {type: 'numberbox', options:{required: true, precision: 2}}},
							{field: 'remark', title: '备注', width: parseInt($(this).width() * 0.1), editor: 'textbox'}
						  ];
			entryArr[0] = new $.erp.materialCol($dg).mergeCol(currArr);
			
		    $dg.datagrid({
			url: 'salesPriceList/findEntriesById.action',
			width: 'auto',
			height: 350,
			rownumbers: true,
			collapsible: true,
			border: true,
			striped: true,
			singleSelect: true,
			showFooter: true,
			idField: 'entryId',
			columns: entryArr,
			onClickRow: onClickRow,
			toolbar: '#tb'
		});
		
		$dg.success = true;
		    
		entry = new $.erp.entry($dg);
	});
	
	function init(){
		$("#date").erpCurrDate();
		$("#customerId").erpCustomer();
		$("#currencyId").erpCurrency($("#exchangeRate"));
		$("#employeeId").erpEmployee();
		$("#managerId").erpEmployee();
		$("#checker").erpEmployee();
		$("#creater").erpUsers(true);
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
					<input id="billNo" name="billNo" class="easyui-textbox" data-options="required:true, editable:false"/>
				</td>
				
				<th>货币</th>
				<td>
					<input id="currencyId" name="currencyId" class="easyui-textbox" data-options="required:true, editable:false" />
				</td>
		
				<th>汇率</th>
				<td>
					<input id="exchangeRate" name="exchangeRate" type="text" class="easyui-numberbox" data-options="required:true,precision:2" />
				</td>
				
			</tr>
			<tr>
				<th>购货单位</th>
				<td>
					<input id="customerId" name="customerId" class="easyui-textbox" data-options="required:true"/>
				</td>
			
				<th>日期</th>
				<td>
					<input id="date" name="date" class="easyui-datebox" data-options="required:true"/>
				</td>
				
				<th>业务员</th>
				<td>
					<input id="employeeId" name="employeeId" class="easyui-textbox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>部门</th>
				<td>
					<input id="departmentId" name="departmentId" class="easyui-combotree" data-options="required:true"/>
				</td>
			
				<th>主管</th>
				<td>
					<input id="managerId" name="managerId" class="easyui-textbox" data-options="required:true"/>
				</td>
				
				<th>制单</th>
				<td>
					<input id="creater" name="creater" class="easyui-combogrid" data-options="requried:true"/>
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
			</tr>
		</table>
		
		<div class="" style="margin-top: 12px;">
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
	</form>
</div>