<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $form;
	var entry;
	$(function(){
		$form = $("#form");
		$form.form({
			url: "role/persist.action",
			onSubmit : submit,
			success: success
		});
		
		entry = new $.erp.simpleEntry();
	});
	
	function submit(){
		return entry.submit($form);
	}
	
	function success(result){
		entry.success(result, process);
	}
	
	function process(){
		$form.form('reset');
		parent.$.modalDialog.openner.datagrid('reload');
	}
	
</script>


<div class="dlgcontent"> 
		<form id="form" method="post">
				<input name="roleId" id="roleId" type="hidden" />
				<input name="created" id="created" type="hidden" />
				<input name="creater" id="creater" type="hidden" />
				<input name="status" id="status" type="hidden" />
				<table class="simple">
					<tr>
						<th>角色名称</th>
						<td>
							<input name="name" id="name" class="easyui-textbox" data-options="required:true" />
						</td>
					</tr>
					<tr>	
						<th>排序</th>
						<td>
							<input name="sort" id="sort" class="easyui-textbox" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<th>描述</th>
						<td colspan="3">
							<textarea id="description" name="description" ></textarea>
						</td>
					</tr>
				</table>
		</form>
</div>