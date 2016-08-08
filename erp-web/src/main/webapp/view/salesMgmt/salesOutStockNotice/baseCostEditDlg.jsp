<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	var $costGroup;
	var $total;
	var $form;
	var entry;
	$(function(){
		$('#predictSail').erpCurrDate();
		$('#predictReach').erpCurrDate();
		$('#cutoff').erpCurrDate();
		
		$form = $('#form');
		$form.form({
			url: 'transportInfo/persist',
			onSubmit: submit,
			success: success
		});
		
		entry = new $.erp.simpleEntry();
	});
	
	function submit(){
		return entry.submit($form);
	}
	
	function success(result){
		entry.success(result);
	}
	
	function init(){
		$costGroup = $('#costGroup').find('.easyui-numberbox');
		$total = $('#total');
		$costGroup.each(function(i,e){
			$(e).numberbox('textbox').bind('blur',function(){
				var total = costTotal($costGroup);
				$total.numberbox('setValue',total);
			});
			
		});
	
	}
	function costTotal(costGroup){
		var sum = 0.0;
		costGroup.each(function(i,e){
			var currVal = $(e).numberbox('getValue');
			if(!currVal){
				currVal = 0.0;
			}
			var tmp = Number(currVal);
			sum = Number((sum + tmp).toFixed(2));
		});
		return sum;
	}
</script>

<style>
	fieldset{
		border: 1px solid #D4D4D4;
	}
</style>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="id" name="id" type="hidden"/>
		<input id="interId" name="interId" type="hidden"/>
		<input id="creater" name="creater" type="hidden"/>
		<input id="created" name="created" type="hidden"/>
		<input id="status" name="status" type="hidden"/>
		<input id="taskId" name="taskId" type="hidden" />
		<fieldset>
			<legend>
				基本信息
			</legend>
			<table class="simple">
				<tr>
				<th>货代名称</th>
				<td>
					<input id="freight" name="freight" class="easyui-textbox" />
				</td>
				<th>预计开船</th>
				<td>
					<input id="predictSail" name="predictSail" type="text" />
				</td>
				<th>预计到港</th>
				<td>
					<input id="predictReach" name="predictReach" type="text" />
				</td>
			</tr>
			<tr>
				<th>重量</th>
				<td>
					<input id="weight" name="weight" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
				<th>体积</th>
				<td>
					<input id="bulk" name="bulk" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
				<th>箱数</th>
				<td>
					<input id="quantity" name="quantity" class="easyui-numberbox" data-options="min:0" />
				</td>
			</tr>
			<tr>
				<th>装箱方式</th>
				<td>
					<input id="way" name="way" class="easyui-textbox" />
				</td>
				<th>截关日期</th>
				<td>
					<input id="cutoff" name="cutoff" type="text" />
				</td>

			</tr>
		</table>
	</fieldset>
	
	<fieldset style="margin-top: 10px;">
		<legend>
			相关费用
		</legend>
		
		<table id="costGroup" class="simple">
			<tr>
				<th>报关费</th>
				<td>
					<input id="customsCost" name="customsCost" class="easyui-numberbox" data-options="min:0,precision:2"/>
				</td>
				<th>商检费</th>
				<td>
					<input id="commodityInspCost" name="commodityInspCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
				<th>送货费</th>
				<td>
					<input id="deliverGoodsCost" name="deliverGoodsCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
			</tr>
			<tr>
				<th>入仓费</th>
				<td>
					<input id="storageCost" name="storageCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
				<th>文件费</th>
				<td>
					<input id="fileCost" name="fileCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
				<th>港建费</th>
				<td>
					<input id="buildCost" name="buildCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
			</tr>
			<tr>
				<th>海运费</th>
				<td>
					<input id="transportCost" name="transportCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
				<th>THC/ORC</th>
				<td>
					<input id="thcOrcCost" name="thcOrcCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>
				<th>港口安全费</th>
				<td>
					<input id="securityCost" name="securityCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td> 
			</tr>
			<tr>
				<th>操作费</th>
				<td>
					<input id="operationCost" name="operationCost" class="easyui-numberbox" data-options="min:0,precision:2" />
				</td>

				<th>其它费</th>
				<td>
					<input id="otherCost" name="otherCost" class="easyui-numberbox" data-options="min:0,precision:2"/>
				</td> 
			</tr>
		</table>
	</fieldset>
	<div style="margin-top: 15px;">
		<label>备注:<textarea id="remark" name="remark" style="width: 300px; height: 40px;"></textarea></label><br />
	</div>
	<div style="margin-top: 15px;">
		<label>合计:<input id="total" name="total" class="easyui-numberbox" data-options="editable:false,min:0,precision:2"/></label>
	</div>
	</form>	
</div>

