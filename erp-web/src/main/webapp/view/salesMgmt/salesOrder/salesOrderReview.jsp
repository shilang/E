<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $form;
	var $dg;
	var entry;
	$(function(){
		$form = $('#form');
		$form.form({
			url: 'salesOrder/updateOrderReview.action',
			onSubmit: submit,
			success: success
		});
		
		$dg = $('#dg');
		$dg.success = false;
		
		var entryArr = [[]];
		var currArr = [{field:'quantity',title:'数量',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{required:true}}},
		               {field:'price',title:'单价',width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'amount',title:'金额',sum:true,width:parseInt($(this).width()*0.1),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'date',title:'交货日期',width:parseInt($(this).width()*0.1),
		            	   editor:{type:'datebox',options:{
		            		   required:true
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
			idField: 'entryId',
			columns: entryArr,
		});
		$dg.success = true;
		
		entry = new $.erp.simpleEntry();
	});
	
	function submit(){
		return entry.submit($form);
	}
	
	function success(result){
		entry.success(result);
	}
	
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="interId" name="interId" type="hidden" />
		<table class="simple">
			<tr>
				<th>单据编号</th>
				<td>
					<input id="billNo" name="billNo" class="easyui-textbox" data-options="editable:false"/>
				</td>
				<th>业务员</th>
				<td>
					<input id="employeeName" name="employeeName" class="easyui-textbox" data-options="editable:false"/>
				</td>
			</tr>
		</table>
		<div>
			<table id="dg" title="项目内容"></table>
		</div>
		<div style="margin-top: 10px;">
			<div>评审内容</div>
			<textarea id="review" name="review"></textarea>
		</div>
	</form>
</div>