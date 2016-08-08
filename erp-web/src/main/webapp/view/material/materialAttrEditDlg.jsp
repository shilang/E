<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $form;
	var entry;
	$(function(){
		
		$form = $("#form");
		$form.form({
			url: 'materialAttr/persist.action',
			onSubmit: submit,
			success: success
		});
		
		entry = new $.erp.simpleEntry();
	});
	
	function submit(){
		return entry.submit($form);
	}
	
	function success(result){
		entry.success(result, function(){
			parent.$.modalDialog.openner.treegrid('reload');
			parent.$.modalDialog.handler.dialog('close');
		});
	}
	
</script>
<div class="dlgcontent">
	<form id="form" method="post">
		<input id="attrId" name="attrId" type="hidden"/>
		<input id="pid" name="pid" type="hidden"/>
		<input id="created" name="created" type="hidden"/>
		<input id="creater" name="creater" type="hidden"/>
		<input id="status" name="status" type="hidden"/>
		<table class="simple">
			<tr>
				<th>名称</th>
				<td>
					<input id="name" name="name" class="easyui-textbox" data-options="required:true"/>
				</td>
				<th>父名称</th>
				<td>
					<input id="pname" name="pname" class="easyui-textbox" data-options="editable:false"/>
				</td>
			</tr>
			<tr>
				<th>描述</th>
				<td colspan="3">
					<textarea id="remark" name="remark"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>