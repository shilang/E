<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		
		$("#form").form({
			url: 'material/persist.action',
			onSubmit: function(){
				parent.$.messager.progress({
					title: $.erp.hint,
					msg: $.erp.processing
				});
				var isValid = $(this).form('validate');
				if(!isValid){
					parent.$.messager.progress("close");
				}
				return isValid;
			},
			success: function(result){
				parent.$.messager.progress("close");
				result = $.parseJSON(result);
				if(result.status){
					parent.$.modalDialog.handler.dialog('close');
					parent.$.modalDialog.openner.datagrid('reload');
				}
				$.erp.submitSuccess(result.title, result.message);
			}
		});
	});
	
	function init(){
		$("#topId").erpResGrid({}, 4);
		$("#useState").erpResGrid({}, 5);
		$("#unit").erpGrid({
			url: 'measureUnit/find.action',
			idField: 'measureUnitId'
		});
		$("#materialAttr").combobox({
			valueField:'attrId',
			textField:'name',
			url:'materialAttr/findById.action'
		});
	}
</script>
	
<div class="dlgcontent">
	<form id="form" method="post">
		<input id="itemId" name="itemId" type="hidden" />
		<input id="created" name="created" type="hidden" />
		<input id="creater" name="creater" type="hidden" />
		<input id="status" name="status" type="hidden" />
			<table class="simple">
				<tr>
					<th>代码</th>
					<td>
						<input id="number" name="number" class="easyui-textbox" data-options="required:true"/>
					</td>
					
					<th>名称</th>
					<td>
						<input id="name" name="name" class="easyui-textbox" data-options="required: true"/>
					</td>
				</tr>
				<tr>
					<th>全名</th>
					<td>
						<input id="fullName" name="fullName" class="easyui-textbox" data-options="required: true"/>
					</td>
				
					<th>规格型号</th>
					<td>
						<input id="model" name="model" class="easyui-textbox" data-options="required: true"/>
					</td>
				</tr>
				<tr>	
					<th>物料分类</th>
					<td>
						<input id="topId" name="topId" class="easyui-textbox" />
					</td>
				
					<th>计量单位</th>
					<td>
						<input id="unit" name="unit" class="easyui-textbox" data-options="required: true"/>
					</td>
				</tr>
				<tr>
					<th>使用状态</th>
					<td>
						<input id="useState" name="useState" class="easyui-textbox" />
					</td>
					<th>物料属性</th>
					<td>
						<input id="materialAttr" name="materialAttr" class="easyui-textbox" />
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3">
						<textarea id="remark" name="remark"></textarea>
					</td>
				</tr>
				
			</table>
	</form>	
</div>