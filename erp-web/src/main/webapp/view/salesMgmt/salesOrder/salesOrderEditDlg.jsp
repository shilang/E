<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<script type="text/javascript">
	var $dg;
	var $form;
	var $amount;
	var $freightAmount;
	var entry;
	var date;
	var transitAheadTime;
	var fileMenu;
	
	var salOrderShowPrice = false;
	
	 $(function(){
		 
		 initOtherPerm();
		 
		 transitAheadTime = $('#transitAheadTime');
		 
		 $amount = $('#amount');
		 $freightAmount = $('#freightAmount');
		 
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
		               {field:'price',title:'单价',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'amount',title:'金额',hidden:!salOrderShowPrice,sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
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
		$("#settlementDate").erpCurrDate();
		$("#currencyId").erpCurrency($("#exchangeRate"));
		/* $("#salesScope").erpResGrid({}, 9); */
		$("#settlementId").erpResGrid({}, 6);
		$("#salesWay").erpResGrid({}, 7);
		transitAheadTime.erpCurrDate();
		$("#fetchWay").erpResGrid({}, 8);
		$("#fetchAddr").erpResGrid({},10, true,"交货地点");
		date = $("#date").erpCurrDate();
		$('#tradeWay').erpResGrid({},15);
		$("#managerId").erpEmployee();
		$("#checker").erpUsers(true);
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
		
		$freightAmount.numberbox({
			onChange: function(newValue,oldValue){
				updateTotalAmount();
			}
		});
		
		var fileBoxMenuButton = $('#attachOper').menubutton({
			menu:'#attachMM'
		});
		
		fileMenu = $(fileBoxMenuButton.menubutton('options').menu);
		
		fileMenu.menu({
			onClick:function(item){
				var id = item.id;
				var vFileName = $('#fileNm').val();
				var vFileSaveAs = $('#billNo').textbox('getValue');
				if(!vFileSaveAs || !vFileName) return;
				if(id == 'upload'){
					var vFileExt = getFileExt(vFileName);
					initFileParams(vFileSaveAs,vFileExt);
					ajaxFileUpload('filebox', vFileSaveAs);
				}else if(id == 'delete'){
					
				}else if(id == 'download'){
					//ajaxFileDownload(vFileName,vFileSaveAs);
					var vSrc = "file/download.action?fileNm="+vFileName+"&fileSaveAs="+vFileSaveAs;
					$('#fileDownload').attr('src',vSrc);
				}
			}
		});
	}
	
	function initFileParams(saveAs, ext){
		$('#fileSaveAs').val(saveAs);
		$('#fileExt').val(ext);
	}
	
	function ajaxFileUpload(vFileEleId, vFileSaveAs) {
		
		$.messager.progress({
			title: '提示',
			msg: '正在上传...'
		});
		
	    $.ajaxFileUpload({
	            url: 'file/upload.action', //用于文件上传的服务器端请求地址
	            data: {fileSaveAs:vFileSaveAs},
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: vFileEleId, //文件上传域的ID
	            dataType: 'json', //返回值类型 一般设置为json
	            success: function (data, status){   //服务器成功响应处理函数
	            	$.messager.progress('close');
	            	$.erp.submitSuccess(data.title,data.message);
	            },
	            error: function (data, status, e){ //服务器响应失败处理函数
	                $.erp.submitErr('错误',e);
	            }
	        }
	    )
	    return false;
	}
	
	function ajaxFileDownload(vFilename, vFileSaveAs){
		var vUrl = 'file/download.action';
		var vParams = {fileNm:vFilename,fileSaveAs:vFileSaveAs};
		$.ajax({
			url: vUrl,
			data: vParams
		});
		return false;
	}
	
	function updateAmount(){
		var rows = $dg.datagrid('getFooterRows');
		if(rows){
			$amount.numberbox('setValue', rows[0].amount);
		}
		updateTotalAmount();
	}
	
	function updateTotalAmount(){
		var fAmount = $freightAmount.numberbox('getValue');
		if(!fAmount){
			fAmount = 0;
		}
		var totalAmount = parseInt(fAmount) + parseInt($amount.numberbox('getValue'));
		$('#totalAmount').numberbox('setValue', totalAmount);
	}
	
	function initOtherPerm(){
		if($('#salOrderShowPrice').length > 0){
			salOrderShowPrice = true;
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
	
	function endEditing(){
		entry.endEditing();
		updateAmount();
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
	
	function endEditBtn(visible){
		var $endEditOper = $('#endEditOper');
		if(visible){
			$endEditOper.show();
		}else{
			$endEditOper.hide();
		}
	}
	
	function setFileName(obj){
		$('#fileNm').textbox('setValue',$(obj).val());
		var itemEl = $('#upload')[0];
		fileMenu.menu('enableItem',itemEl);
	}
	
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="interId" name="interId" type="hidden"/>
		<input id="created" name="created" type="hidden"/>
		<input id="status" name="status" type="hidden"/>
		<input id="fileSaveAs" name="fileSaveAs" type="hidden" />
		<input id="fileExt" name="fileExt" type="hidden" />
		<table class="simple">
			<tr>
				<th>编号</th>
				<td>
					<input id="billNo" name="billNo" class="easyui-textbox" data-options="required:true,editable:false"/>
				</td>
				<shiro:hasPermission name="salOrderShowCust">
					<th>购货单位</th>
					<td>
						<input id="customerId" name="customerId" class="easyui-textbox" data-options="required:true"/>
					</td>
				</shiro:hasPermission>
				<th>销售方式</th>
				<td>
					<input id="salesWay" name="salesWay" class="easyui-textbox" data-options="required:true"/>
				</td>	
			</tr>
			<tr>
				<!-- <th>销售范围</th>
				<td>
					<input id="salesScope" name="salesScope" class="easyui-textbox" data-options="required:true"/>
				</td> -->
				<shiro:hasPermission name="salOrderShowPrice">
					<th>结算日期</th>
					<td>
						<input id="settlementDate" name="settlementDate" class="easyui-datebox" data-options="required:true"/>
					</td>
					<th>结算方式</th>
					<td>
						<input id="settlementId" name="settlementId" class="easyui-textbox" data-options="required:true"/>
					</td>
					<th>币别</th>
					<td>
						<input id="currencyId" name="currencyId" class="easyui-textbox" data-options="required:true"/>
					</td>
				</shiro:hasPermission>
			</tr>
			<tr>
				<th>交货日期</th> <!-- 由运输提前期更改 -->
				<td>
					<input id="transitAheadTime" name="transitAheadTime" class="easyui-datebox" data-options="required:true"/>
				</td>
				<th>交货方式</th>
				<td>
					<input id="fetchWay" name="fetchWay" class="easyui-textbox" data-options="required:true"/>
				</td>
				<shiro:hasPermission name="salOrderShowPrice">
					<th>汇率</th>
					<td>
						<input id="exchangeRate" name="exchangeRate" class="easyui-numberbox" data-options="required:true,precision:2"/>
					</td>
				</shiro:hasPermission>
			</tr>
			<tr>
				<th>交货地点</th>
				<td>
					<input id="fetchAddr" name="fetchAddr" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>摘要</th>
				<td>
					<input id="explanation" name="explanation" class="easyui-textbox"/>
				</td>
				<th>日期</th>
				<td>
					<input id="date" name="date" class="easyui-datebox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<shiro:hasPermission name="salOrderShowPrice">
					<th>金额</th>
					<td>
						<input id="amount" name="amount" class="easyui-numberbox" data-options="required:true,editable:false,precision:2,value:0"/>
					</td>
					<th>源单类型</th>
					<td>
						<input id="sourceType" name="sourceType" class="easyui-combobox"/>
					</td>
					<th>选单号</th>
					<td>
						<input id="sourceBillNo" name="sourceBillNo" class="easyui-textbox"/>
					</td>
				</shiro:hasPermission>
			</tr>
			<tr>
				<shiro:hasPermission name="salOrderShowPrice">
					<th>货运金额</th>
					<td>
						<input id="freightAmount" name="freightAmount" class="easyui-numberbox" data-options="required:true,precision:2,value:0"/>
					</td>
					<th>贸易方式</th>
					<td>
						<input id="tradeWay" name="tradeWay" class="easyui-textbox" data-options="required:true"/>
					</td>
					<th>总金额</th>
					<td>
						<input id="totalAmount" name="totalAmount" class="easyui-numberbox" data-options="required:true,editable:false,precision:2,value:0"/>
					</td>
				</shiro:hasPermission>
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
			<tr>
				<th>附件</th>
				<td colspan="4">
					<div class="fileboxContainer">
						<input id="filebox" name="filebox" class="filebox" type="file" onchange="setFileName(this)" />
					</div>
					<div style="display:none;">
						<iframe id="fileDownload" src="" ></iframe>
					</div>
					<input id="fileNm" name="fileNm" class="easyui-textbox" style="width:300px;" data-options="editable:false" />
					<a href="javascript:void(0)" id="attachOper">操作</a>
					<div id="attachMM">
						<div id="upload" data-options="iconCls:'icon-upload',disabled:true">上传</div>    
						<!-- <div id="delete" data-options="iconCls:'icon-remove'">删除</div> -->
						<div class="menu-sep"></div>    
						<div id="download" data-options="iconCls:'icon-download',disabled:false">下载</div>    
					</div>
				</td>
			</tr>
		</table>
		<div class="" style="margin-top: 12px;">
				<div id="tb">
					<table>
						<tr>
							<td>
								<shiro:hasPermission name="salOrderShowPrice">
									<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendRow();">添加</a>
									<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeRow();">删除</a>
									<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="rejectRows();">撤销</a>
									<a id="endEditOper" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="endEditing();">结束编辑</a>
								</shiro:hasPermission>
							</td>
						</tr>
					</table>
				</div>
				<table id="dg" title="项目内容"></table>
		</div>
	</form>
		<shiro:hasPermission name="salOrderShowPrice">
			<label id="salOrderShowPrice"></label>
		</shiro:hasPermission>
</div>