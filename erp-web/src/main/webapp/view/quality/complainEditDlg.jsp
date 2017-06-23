<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style type="text/css">
	
	.dlgcontent #form table tr th{
		font-weight:300; 
		font-size:12px;
	}
	
	.dlgcontent #form fieldset{
		border-width: 1px; 
		border-color: #C8C8C8; 
	}
	
	.dlgcontent #form fieldset legend{
		font-weight: bold;
	}

	.dlgcontent #form textarea{
		width: 640px;
		height: 50px;
	}
	
	.dlgcontent #form div span{
		margin-left: 4px;
	}
	
	.dlgcontent #form #fixDiv .easyui-textbox{
		width: 625px;
	}

</style>
    
<script type="text/javascript">

	function billNoPart(editable){
		if(editable){
			$('#billNo').removeAttr('readonly');
		}
	}
	
	function contentPart(editable){
		if(editable){
			$('#content').removeAttr('readonly');
			$("input[name='suggestion']").removeAttr('disabled');
			$('#attachment').removeAttr('readonly');
		}
	} 
	
	function reasonPart(editable){
		if(editable){
			$('#reason').removeAttr('readonly');
			$('#SCBchk').removeAttr('disabled');
			$('#PZBchk').removeAttr('disabled');
			$('#CGBchk').removeAttr('disabled');
			$('#GCBchk').removeAttr('disabled');
			$('#GMBchk').removeAttr('disabled');
			$('#WJBchk').removeAttr('disabled');
		}
	}
	
	function fixPart(editable){
		if(editable){
			$('#SCB').removeAttr('readonly');
			$('#PZB').removeAttr('readonly');
			$('#CGB').removeAttr('readonly');
			$('#GCB').removeAttr('readonly');
			$('#GMB').removeAttr('readonly');
			$('#WJB').removeAttr('readonly');
		}
	}
	
	function handlePart(editable){
		if(editable){
			$('#solveWay').removeAttr('readonly');
			$('#solveWayMark').removeAttr('readonly');
		}
	}
	
	function resultPart(editable){
		if(editable){
			$('#result').removeAttr('readonly');
		}
	}
	
	function initSegment(level,id){
		setProcessInstanceId(id);
		if(level == ''){
			
		}
	}
	
	function complainProcess(userId, processInstanceId){
		var url = 'quality/getSegment.action';
		var params = {userId:userId,processInstanceId:processInstanceId};
		$.ajax({url:url,data:params}).done(function(){
			initSegment(data.message,processInstanceId);
		});
	}
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="interId" name="interId" type="hidden" />
		<input id="suggestion" name="suggestion" type="hidden" />
		<!-- 订单信息 -->
		<div id="infoDiv">
			<table>
				<tr>
					<th>订单编号:</th>
					<td>
						<input id="billNo" name="billNo" readonly="readonly" class="easyui-combobox" data-options="required:true" />
					</td>
					<th>创建日期:</th>
					<td>
						<input id="date" name="date" class="easyui-textbox" data-options="editable:false" />
					</td>
					<th>订单状态:</th>
					<td>
						<input id="orderStatus" name="orderStatus" class="easyui-textbox" data-options="editable:false" />
					</td>
				</tr>
			</table>
		</div>
		<!-- 投诉内容 -->
		<div id="contentDiv">
			<fieldset>
				<legend>投诉内容</legend>
				<div>
					<span>投诉内容:</span>
					<textarea id="content" name="content" readonly="readonly" ></textarea>
				</div>
				<div>
				 <table>
					<tr>
						<th>客户意见:</th>
						<td><label><input name="suggestion" type="radio" disabled="disabled" value="" />退换 </label></td>
						<td><label><input name="suggestion" type="radio" disabled="disabled" value="" />返修 </label></td>
						<td><label><input name="suggestion" type="radio" disabled="disabled" value="" />补发配件 </label></td>
						<td><label><input name="suggestion" type="radio" disabled="disabled" value="" />退货 </label></td>
						<td><label><input name="suggestion" type="radio" disabled="disabled" value="" />其它 </label></td>
					</tr>
				  </table>
				</div>
				
				<div>
					<span>附件位置:</span><input id="attachment" name="attachment" type="text" readonly="readonly" class="easyui-textbox" data-options="" style="width:645px;">
				</div>
			</fieldset>
			
		</div>
		<!-- 不良品原因分析 -->
		<div id="reasonDiv">
		<fieldset>
			<legend>不良品原因分析</legend>
			<span>原因分析:</span>
			<textarea id="reason" name="reason" readonly="readonly" ></textarea>
			<table>
				<tr>
					<th>纠正与预防部门:</th>
					<td><label><input id="SCBchk" name="SCBchk" type="checkbox" disabled="disabled" />生产部 </label></td>
					<td><label><input id="PZBchk" name="PZBchk" type="checkbox" disabled="disabled" />品质部 </label></td>
					<td><label><input id="CGBchk" name="CGBchk" type="checkbox" disabled="disabled" />采购部 </label></td>
					<td><label><input id="GCBchk" name="GCBchk" type="checkbox" disabled="disabled" />工程部 </label></td>
					<td><label><input id="GMBchk" name="GMBchk" type="checkbox" disabled="disabled" />工模部 </label></td>
					<td><label><input id="WJBchk" name="WJBchk" type="checkbox" disabled="disabled" />五金部 </label></td>
				</tr>
			</table>
		</fieldset>
		</div>
		<!-- 纠正与预防措施 -->
		<div id="fixDiv">
		<fieldset>
			<legend>纠正与预防措施</legend>
			<table>
				<tr>
					<th>生产部措施:</th>
					<td><input id="SCB" name="SCB" readonly="readonly" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>品质部措施:</th>
					<td><input id="PZB" name="PZB" readonly="readonly" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>采购部措施:</th>
					<td><input id="CGB" name="CGB" readonly="readonly" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>工程部措施:</th>
					<td><input id="GCB" name="GCB" readonly="readonly" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>工模部措施:</th>
					<td><input id="GMB" name="GMB" readonly="readonly" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<th>五金部措施</th>
					<td><input id="WJB" name="WJB" readonly="readonly" class="easyui-textbox" /></td>
				</tr>
			</table>
		</fieldset>
			
		</div>
		<!-- 处理方案 -->
		<div id="handleDiv">
		<fieldset>
			<legend>处理方案</legend>
			<table>
				<tr>
					<th>处理结果:</th>
					<td><input id="solveWay" name="solveWay" readonly="readonly" class="easyui-combobox"/></td>
					<th>备注:</th>
					<td><input id="solveWayMark" name="solveWayMark" readonly="readonly" class="easyui-textbox" style="width: 427px;"/></td>
				</tr>
			</table>
		</fieldset>
			
		</div>
		<!-- 处理结果 -->
		<div id="resultDiv">
		<fieldset>
			<legend>处理结果</legend>
			<table>
				<tr>
					<th>客户满意度:</th>
					<td><input id="result" name="result" readonly="readonly" class="easyui-combobox" /></td>
				</tr>
			</table>
		</fieldset>
			
		</div>
	</form>	
</div>