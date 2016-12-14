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
			url: 'salesOutStock/persistence.action',
			onSubmit: submit,
			success: success
		});
		
		$dg = $("#dg");
		$dg.success = false;

		var entryArr = [[]];
		var currArr = [
		              {field:'quantity',title:'应发数量',sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{editable:false}}},
		              {field:'actualQuantity',title:'实发数量',sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{required:true}}},
		              {field:'price',title:'单价',hidden:true, width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{required:true,precision:2}}},
		              {field:'amount',title:'金额',hidden:true,sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{required:true,precision:2}}},
		              {field:'stockId',title:'发货仓库',width:parseInt($(this).width()*0.1),editor:{type:"textbox",options:{editable:false}}},
		              {field:'stockPlaceId',title:'仓位',width:parseInt($(this).width()*0.1),editor:{type:"textbox",options:{editable:false}}},
		              {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.12),editor:{type:"textbox",options:{editable:false}}},
		              {field:'remark',title:'备注',width:parseInt($(this).width()*0.4),editor:'textbox'}
		              ];
		entryArr[0] = new $.erp.materialCol($dg).mergeCol(currArr);
		$dg.datagrid({
			url: 'salesOutStock/findEntriesById.action',
			width: 'auto',
			height: 260,
			collapsible: true,
			rownumbers: true,
			striped: true,
			border: true,
			singleSelect: true,
			nowrap: false,
			/* showFooter: true, */
			idField: 'entryId',
			columns: entryArr,
			toolbar: '#tb',
			onClickRow: onClickRow
		});
		$dg.success = true;
		
		entry = new $.erp.entry($dg);
	});
	
	function init(){
		$("#marketingWay").erpResGrid({}, 11);
		$("#salesWay").erpResGrid({}, 7);
		$("#customerId").erpCustomer();
		$("#fetchAddr").erpResGrid({}, 10);
		date = $("#date").erpCurrDate(true);
		$("#settlementDate").erpCurrDate();
		$("#sender").erpEmployee();
		$("#defender").erpEmployee();
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
				value: '发货通知'
			},{
				text: '销售订单',
				value: '销售订单'
			}],
			onSelect: function(record){
				$("#sourceBillNo").textbox("setValue","");
				$.erp.dgClean($dg);
				if(record.value == '发货通知'){
					selUrl = "view/salesMgmt/salesOutStockNotice/salesOutStockNoticeMain.jsp?selSta=true";
				}else if(record.value == '销售订单'){
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
				parent.$.modalDialog.type = "XSCK";
				parent.$.modalDialog.date = date.datebox('getValue');
				parent.$.modalDialog.selDlg = $dlg;
				parent.$.modalDialog.dg = $dg;
			}
		});
	}
	
	function gridRowTotal(index){
		$.erp.gridRowTotal($dg,index,['actualQuantity','price'],'amount','*');
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
				<th>日期</th>
				<td>
					<input id="date" name="date" class="easyui-datebox" data-options="required:true"/>
				</td>
				<th>业务类型</th>
				<td>
					<input id="marketingWay" name="marketingWay" class="easyui-textbox" data-options="required:true"/>
				</td>
			
			</tr>
			<tr>
				<!-- <th>购货单位</th>
				<td>
					<input id="customerId" name="customerId" class="easyui-textbox" data-options="required:true"/>
				</td> -->
				
				<th>销售方式</th>
				<td>
					<input id="salesWay" name="salesWay" class="easyui-textbox" data-options="required:true"/>
				</td>
				
				<th>收款日期</th>
				<td>
					<input id="settlementDate" name="settlementDate" class="easyui-datebox" data-options="required:true" />
				</td>
				
				<th>摘要</th>
				<td>
					<input id="explanation" name="explanation" class="easyui-textbox" />
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
			</tr>	
			<tr>
				<th>交货地点</th>
				<td>
					<input id="fetchAddr" name="fetchAddr" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>发货仓库</th>
				<td>
					<input id="stockId" name="stockId" class="easyui-textbox" data-options="editable:false"/>
				</td>
				<th>发货</th>
				<td>
					<input id="sender" name="sender" class="easyui-textbox" data-options="required:false"/>
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
				<th>业务员</th>
				<td>
					<input id="employeeId" name="employeeId" class="easyui-textbox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>记账</th>
				<td>
					<input id="" name="" class="easyui-textbox" />
				</td>
				<th>保管</th>
				<td>
					<input id="defender" name="defender" class="easyui-textbox" data-options="required:false"/>
				</td>
				<th>审核</th>
				<td>
					<input id="checker" name="checker" class="easyui-textbox" data-options="editable:false"/>
				</td>
			</tr>
			<tr>
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
</div>