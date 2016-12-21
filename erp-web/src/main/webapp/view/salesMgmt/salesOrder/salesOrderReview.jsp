<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
    
<style type="text/css">
	div #reviewDiv{
		margin-top: 5px;
	}
	
	div #reviewDiv ul{
		margin:0;
		padding:0;
		list-style: none;
	}
	
	div #reviewDiv ul li{
		margin-bottom:5px;
	}
	
	div #reviewDiv ul li span{
		font-size: 12px;
		font-weight: bold;
		margin-right: 5px;
	}
	
	div #reviewDiv ul li textarea{
		width: 700px;
		height: 30px;
	}
	
	div #reviewDiv ul li textarea#review{
		height: 60px;
	}
	
	div #reviewDiv span{
		
	}
</style>
    
<script type="text/javascript">
	var $form;
	var $reviewdg;
	var entry;
	
	var salOrderShowPrice = false;
	
	$(function(){
		
		initOtherPerm();
		
		$form = $('#form');
		$form.form({
			url: 'salesOrder/updateOrderReview.action',
			onSubmit: submit,
			success: success
		});
		
		$reviewdg = $('#reviewdg');
		$reviewdg.success = false;
		
		var entryArr = [[]];
		var currArr = [{field:'quantity',title:'数量',sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{required:true}}},
		               {field:'price',title:'单价',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'amount',title:'金额',hidden:!salOrderShowPrice,sum:true,width:parseInt($(this).width()*0.06),editor:{type:'numberbox',options:{precision:2,required:true}}},
		               {field:'date',title:'交货日期',width:parseInt($(this).width()*0.1),
		            	   editor:{type:'datebox',options:{
		            		   required:true
		            		   }}},
		               {field:'adviceDate',title:'建议交货日期',width:parseInt($(this).width()*0.1),editor:{type:'datebox',options:{required:true}}},
		               {field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.12),editor:{type:'textbox',options:{editable:false}}},
		               {field:'remark',title:'备注',width:parseInt($(this).width()*0.4),editor:'textbox'}
		              ];
		entryArr[0] = new $.erp.materialCol($reviewdg).mergeCol(currArr);
		$reviewdg.datagrid({
			url: 'salesOrder/findEntriesById.action',
			width: 'auto',
			height: 260,
			rownumbers: true,
			collapsible: true,
			striped: true,
			border: true,
			singleSelect: true,
			nowrap: false,
			idField: 'entryId',
			columns: entryArr,
		});
		$reviewdg.success = true;
		
		entry = new $.erp.simpleEntry();
	});
	
	function submit(){
		return entry.submit($form);
	}
	
	function success(result){
		entry.success(result);
	}
	
	function initOtherPerm(){
		if($('#salOrderShowPrice').length > 0){
			salOrderShowPrice = true;
		}
	}
	
	function setSegment(name){
		$('#segment').val(name);
	}
	
	function setTaskDefKey(key){
		$('#taskDefKey').val(key);
	}
	
	function setProcessInstanceId(id){
		$('#processInstanceId').val(id);
	}
	
	function setBtnEditable(status){
		$('#btnEditable').val(status);
	}
	
	function reviewInit(level, id){
		setProcessInstanceId(id);
		if(level == 'GongChenReview'){
			setTaskDefKey('GongChenReview');
			setSegment('review');
			$('#review').removeAttr('readonly');
		}else if(level == 'CangKuReview'){
			setTaskDefKey('CangKuReview');
			setSegment('ckreview');
			$('#ckreview').removeAttr('readonly');
		}else if(level == 'CaiGouReview'){
			setTaskDefKey('CaiGouReview');
			setSegment('cgreview');
			$('#cgreview').removeAttr('readonly');
		}
	}
	
	function review(userId, processInstanceId){
		var url = 'salesOrder/getReviewSegment.action';
		var params = {userId:userId,processInstanceId:processInstanceId};
		$.ajax({url:url,data:params}).done(function(data){
			reviewInit(data.message, processInstanceId);
		});
	}
	
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="interId" name="interId" type="hidden" />
		<input id="segment" name="segment" type="hidden" />
		<input id="processInstanceId" name="processInstanceId" type="hidden" />
		<input id="taskDefKey" name="taskDefKey" type="hidden" />

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
				<th>修改时间</th>
				<td>
					<input id="lastmod" name="lastmod" class="easyui-textbox" data-options="editable:false"/>
				</td>
			</tr>
		</table>
		<div style="margin-bottom:5px;">
			<table id="reviewdg" title="项目内容"></table>
		</div>
		<div class="easyui-panel" data-options="title:'改单记录',collapsible:true,collapsed:true">
			<textarea id="changeReason" name="changeReason" readonly="readonly" style="margin:0;padding:0;border:0;width:100%;height:150px;"></textarea>
		</div>
		<div id="reviewDiv">
			<ul>
				<li>
					<div>
						<span>工程评审</span>
						<span>
							<input id="reviewDate" name="reviewDate" class="easyui-textbox" data-options="editable:false"/>
						</span>
					</div>
					<textarea id="review" name="review" readonly></textarea>
				</li>
				<li>
					<div>
						<span>仓库评审</span>
						<span>
							<input id="ckReviewDate" name="ckReviewDate" class="easyui-textbox" data-options="editable:false"/>
						</span>
					</div>
					<textarea id="ckreview" name="ckreview" readonly></textarea>
				</li>
				<li>
					<div>
						<span>采购评审</span>
						<span>
							<input id="cgreviewDate" name="cgreviewDate" class="easyui-textbox" data-options="editable:false"/>
						</span>
					</div>
					<textarea id="cgreview" name="cgreview" readonly></textarea>
				</li>
			</ul>
		</div>
	</form>
	<shiro:hasPermission name="salOrderShowPrice">
			<label id="salOrderShowPrice"></label>
	</shiro:hasPermission>
</div>