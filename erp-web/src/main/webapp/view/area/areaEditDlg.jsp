<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript"> 
	$(function(){
		$("#form").form({
			url: 'area/persist.action',
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
					parent.$.modalDialog.openner.datagrid("reload");
					parent.$.modalDialog.handler.dialog("close");
				}
				$.erp.submitSuccess(result.title, result.message);
			}
		});
	});
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="areaId" name="areaId" type="hidden" />
		<input id="created" name="created" type="hidden" />
		<input id="creater" name="creater" type="hidden" />
		<input id="status" name="status" type="hidden" />
		<table class="simple">
			<tr>
				<th>代码</th>
				<td><input id="number" name="number" class="easyui-textbox" data-options="required:true"/></td>
				
				<th>名称</th>
				<td><input id="name" name="name" class="easyui-textbox" data-options="required:true"/></td>
			</tr>
			<tr>
				<th>英文名称</th>
				<td><input id="nameEn" name="nameEn" class="easyui-textbox" data-options="required:true"/>
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