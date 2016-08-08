<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<script type="text/javascript">
	var $dgEntry;
	var $dgReceivable;
	var $form;
	var entry;
	var rev;
	$(function(){
		
		$form = $("#form");
		$form.form({
			url:'salesContract/persistence.action',
			onSubmit: submit,
			success: success
		});
		
		$dgEntry = $('#dg');
		$dgEntry.success = false;
		$dgReceivable = $("#dgreceivable");
		$form = $("#form");
		
		var entryArr = [[]];
		
		var currArr = [
			   			{field:'quantity', title: '数量',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'textbox',options:{required:true}}},
						{field:'price',title:'单价',width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
						{field:'amount',title:'金额',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
						{field:'explanation',title:'备注',width:parseInt($(this).width()*0.15),editor:'textbox'}
					  ];
		
		entryArr[0] = new $.erp.materialCol($dgEntry).mergeCol(currArr);
		
		$dgEntry.datagrid({
			url: 'salesContract/findEntriesById.action',
			width: 'auto',
			height: 350,
			collapsible: true,
			border: true,
			striped: true,
			singleSelect: true,
			showFooter: true,
			idField: 'detailId',
			columns: entryArr,
			toolbar: '#entry',
			onClickRow: onClickRow_entry
		});
		$dgEntry.success = true;
		
		$dgReceivable.datagrid({
			url: 'salesContract/findSchemesById.action',
			width: 420,
			height: 248,
			collapsible: true,
			border: true,
			striped: true,
			singleSelect: true,
			showFooter: true,
			idField: 'id',
			columns: [[
				{field:'receiveDate',title:'应收日期',width:120,editor:{type:'datebox',options:{required:true}}},
				{field:'amount',title:'应收金额',sum:true,width:150,editor:{type:'numberbox',options:{precision:2,required:true}}}
			]],
			toolbar: '#receivable',
			onClickRow: onClickRow_rev
		});
		
		entry = new $.erp.entry($dgEntry);
		entry.stat = statTotal;
		rev = new $.erp.entry($dgReceivable);
		
	});
	
	function init(){
		$("#currencyId").erpCurrency($("#exchangeRate"));
		$("#itemClassId").erpCustomer();
		$("#date").erpCurrDate();
		$("#employeeId").erpEmployee();
		$("#checker").erpEmployee();
		$("#creater").erpUsers(true);
	}
	
	function statTotal(){
		var footerRows = $dgEntry.datagrid('getFooterRows');
		if(footerRows){
			$("#totalAmount").textbox('setValue',footerRows[0].amount);
		}
	}
	
	function gridRowTotal(index){
		$.erp.gridRowTotal($dgEntry,index,['quantity','price'],'amount','*');
	}
	
	function appendRow_entry(){
		entry.appendRow();
		var index = $dgEntry.datagrid('getRows').length - 1;
		gridRowTotal(index);
	}
	
	function appendRow_rev(){
		rev.appendRow();
	}
	
	function removeRow_entry(){
		entry.removeRow();
	}
	
	function removeRow_rev(){
		rev.removeRow();
	}
	
	function rejectRows_entry(){
		entry.rejectRows();
	}
	
	function rejectRows_rev(){
		rev.rejectRows();
	}
	
	function onClickRow_entry(index){
		entry.onClickRow(index);
		gridRowTotal(index);
	}
	
	function onClickRow_rev(index){
		rev.onClickRow(index);
	}
	
	function submit(entryRows){
		entry.saveRows();
		rev.saveRows();
		return entry.submit(entryRows, $form, function(resultSet){
			var tempObj = resultSet[0].effect;
			if(tempObj.inserted){
				entryRows.inserted = tempObj.inserted;
			}
			if(tempObj.updated){
				entryRows.updated = tempObj.updated;
			}
			if(tempObj.deleted){
				entryRows.deleted = tempObj.deleted;
			}
			
			tempObj = resultSet[1].effect;
			if(tempObj.inserted){
				entryRows.schemeInserted = tempObj.inserted;
			}
			if(tempObj.updated){
				entryRows.schemeUpdated = tempObj.updated;
			}
			if(tempObj.deleted){
				entryRows.schemeDeleted = tempObj.deleted;
			}
		});
	}
	
	function success(result){
		rev.success(result, process_rev);
		entry.success(result, process_entry, true);
	}
	
	function process_entry(){
		entry.acceptRows();
		parent.$.modalDialog.openner.datagrid('reload');
		parent.$.modalDialog.handler.dialog('close');
	}
	
	function process_rev(){
		rev.acceptRows();
	}
	
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',border:false" style="width: 530px;">
		<div class="dlgcontent">
			<form id="form" method="post">
				<input id="interId" name= "interId" type="hidden" />
				<input id="created" name= "created" type="hidden" />
				<input id="status" name= "status" type="hidden" />
				<table class="simple">
					<tr>
						<th>合同日期</th>
						<td>
							<input id="date" name="date" class="easyui-datebox" data-options="required:true"/>
						</td>
						<th>货币</th>
						<td>
							<input id="currencyId" name="currencyId" class="easyui-textbox" data-options="required:true"/>
						</td>
					</tr>
					<tr>	
						<th>合同名称</th>
						<td>
							<input id="contractName" name="contractName" class="easyui-textbox" data-options="required:true"/>
						</td>
						<th>汇率</th>
						<td>
							<input id="exchangeRate" name="exchangeRate" class="easyui-numberbox" data-options="required:true,precision:2"/>
						</td>
					</tr>
					<tr>
						<th>合同号</th>
						<td>
							<input id="billNo" name="billNo" class="easyui-textbox" data-options="required:true,editable:false"/>
						</td>
						<th>项目类别</th>
						<td>
							<select id="classTypeId" name="classTypeId" class="easyui-combobox" data-options="width:176,required:true,editable:false,disabled:true">
								<option value="客户" selected="selected">客户</option>
								<option value="供应商">供应商</option>
							</select>
							<input id="classType" name="classType" type="hidden" value="客户"/>
						</td>
					</tr>
					<tr>
						<th>摘要</th>
						<td>
							<input id="explanation" name="explanation" class="easyui-textbox"/>
						</td>
						<th>项目目标</th>
						<td>
							<input id="itemClassId" name="itemClassId" class="easyui-textbox" data-options="required:true"/>
						</td>
					</tr>
					<tr>
						<th>部门</th>
						<td>
							<input id="departmentId" name="departmentId" class="easyui-combotree" data-options="required:true"/>
						</td>
						<th>总金额</th>
						<td>
							<input id="totalAmount" name="totalAmount" class="easyui-textbox"/>
						</td>
					</tr>
					<tr>
						<th>业务员</th>
						<td>
							<input id="employeeId" name="employeeId" class="easyui-textbox" data-options="required:true"/>
						</td>
						
						<th>审核日期</th>
						<td>
							<input id="checkDate" name="checkDate" class="easyui-textbox" data-options="editable:false"/>
						</td>
					</tr>
					<tr>	
						<th>审核</th>
						<td>
							<input id="checker" name="checker" class="easyui-textbox" data-options="editable:false"/>
						</td>
						
						<th>制单人</th>
						<td>
							<input id="creater" name="creater" class="easyui-combogrid" data-options="required:true"/>
						</td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="4">
							<textarea id="remark" name="remark" style="width:410px;height:30px;"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	
	</div>
	<div data-options="region:'center',border:false" style="padding-top:25px;">
		<div id="receivable" class="tb">
			<table>
				<tr>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendRow_rev();">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeRow_rev();">删除</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="rejectRows_rev();">撤销</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="dgreceivable" title="应收"></table>
	</div>
	<div data-options="region:'south',border:false" style="height:225px;padding:0 20px;">
		<div id="entry" class="tb">
			<table>
				<tr>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendRow_entry();">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeRow_entry();">删除</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="rejectRows_entry();">撤销</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="dg" title="项目内容"></table>
	</div>
</div>