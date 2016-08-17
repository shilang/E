<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $dg;
	var $form;
	var entry;
	var date;
	var transitAheadTime;
	
	 $(function(){
		 transitAheadTime = $('#transitAheadTime');
		 
		$form = $("#form");
		$form.form({
			url: 'salesOrder/persistence.action',
			onSubmit: submit,
			success: success
		});
		
		$dg = $("#dg");
		$dg.success = false;
		
		var entryArr = [[]];
		var currArr = [{field:'quantity',title:'数量',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{required:true}}},
		               {field:'price',title:'单价',width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'amount',title:'金额',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'date',title:'交货日期',width:parseInt($(this).width()*0.1),
		            	   editor:{type:'datebox',options:{
		            		   required:true,
		            		   onSelect: function(date){
		            			   setMaxDate(date);
		            		   }
		            		   }}},
		               {field:'remark',title:'备注',width:parseInt($(this).width()*0.1),editor:'textbox'},
		               {field:'adviceDate',title:'建议交货日期',width:parseInt($(this).width()*0.1),editor:{type:'datebox',options:{required:true}}},
		               {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.1),editor:{type:'textbox',options:{editable:false}}}
		              ];
		entryArr[0] = new $.erp.materialCol($dg).mergeCol(currArr);
		$dg.datagrid({
			url: 'salesOrder/findEntriesById.action',
			width: 'auto',
			height: 260,
			rownumbers: true,
			collapsible: true,
			striped: true,
			border: true,
			singleSelect: true,
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
		$("#settlementDate").erpCurrDate();
		$("#currencyId").erpCurrency($("#exchangeRate"));
		$("#salesScope").erpResGrid({}, 9);
		$("#settlementId").erpResGrid({}, 6);
		$("#salesWay").erpResGrid({}, 7);
		transitAheadTime.erpCurrDate();
		$("#fetchWay").erpResGrid({}, 8);
		$("#fetchAddr").erpResGrid({},10);
		date = $("#date").erpCurrDate();
		$("#managerId").erpEmployee();
		$("#checker").erpEmployee();
		$("#employeeId").erpEmployee();
		$('#creater').erpUsers(true);
		
		var selUrl = "";
		$("#sourceType").combobox({
			valueField:'value',
			textField:'text',
			editable: false,
			data: [{
				text:'',
				value:''
			},{
				text:'销售报价单',
				value: '销售报价单'
			},{
				text: '合同(应收)',
				value:'合同(应收)'
			}],
			onSelect: function(record){
				$("#sourceBillNo").textbox("setValue","");
				//clear data
				$.erp.dgClean($dg);		
				
				if(record.value == '销售报价单'){
					selUrl = "view/salesMgmt/salesPriceList/salesPriceListMain.jsp?selSta=true";
				}else if(record.value == '合同(应收)'){
					selUrl = "view/salesMgmt/salesContract/salesContractMain.jsp?selSta=true";
				}else{
					selUrl = "";
				}
			}
		});
		
		$("#sourceBillNo").textbox({
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
				parent.$.modalDialog.type = "XSDD";
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
	
	function setMaxDate(currDate){
	 	var max = currDate;
		var rows = $dg.datagrid('getRows');
		for(var i = 0; i < rows.length; i++){
			var tmpDate = rows[i].date;
			if(tmpDate){
				tmpDate = new Date(tmpDate);
				if(tmpDate > max){
					max = tmpDate;
				}
			}
		}
		var dateStr = max.getFullYear() + '-' + (max.getMonth() + 1) + '-' + max.getDate();
		transitAheadTime.datebox('setValue', dateStr);
	}
	
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="interId" name="interId" type="hidden"/>
		<input id="created" name="created" type="hidden"/>
		<input id="status" name="status" type="hidden"/>
		<table class="simple">
			<tr>
				<th>购货单位</th>
				<td>
					<input id="customerId" name="customerId" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>结算日期</th>
				<td>
					<input id="settlementDate" name="settlementDate" class="easyui-datebox" data-options="required:true"/>
				</td>
				<th>编号</th>
				<td>
					<input id="billNo" name="billNo" class="easyui-textbox" data-options="required:true,editable:false"/>
				</td>
			</tr>
			<tr>
				<th>销售范围</th>
				<td>
					<input id="salesScope" name="salesScope" class="easyui-textbox" data-options="required:true"/>
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
				<th>销售方式</th>
				<td>
					<input id="salesWay" name="salesWay" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>交货日期</th> <!-- 由运输提前期更改 -->
				<td>
					<input id="transitAheadTime" name="transitAheadTime" class="easyui-datebox" data-options="required:true"/>
				</td>
				<th>汇率</th>
				<td>
					<input id="exchangeRate" name="exchangeRate" class="easyui-numberbox" data-options="required:true,precision:2"/>
				</td>
			</tr>
			<tr>
				<th>交货方式</th>
				<td>
					<input id="fetchWay" name="fetchWay" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>交货地点</th>
				<td>
					<input id="fetchAddr" name="fetchAddr" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>摘要</th>
				<td>
					<input id="explanation" name="explanation" class="easyui-textbox"/>
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
					<input id="departmentId" name="departmentId" class="easyui-combotree" data-options="required:true"/>
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
					<input id="creater" name="creater" class="easyui-combogrid" data-options="required:true" />
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