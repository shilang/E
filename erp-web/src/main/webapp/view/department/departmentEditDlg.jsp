<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $form;
	var entry;
	$(function(){
		$("#manager").erpEmployee();
		$form = $("#form")
		$form.form({
			url: 'department/persist.action',
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
		<input id="departmentId" name="departmentId" type="hidden"/>
		<input id="pid" name="pid" type="hidden" />
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
					<input id="name" name="name" class="easyui-textbox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>全名</th>
				<td>
					<input id="fullName" name="fullName" class="easyui-textbox"/>
				</td>
				<th>英文名</th>
				<td>
					<input id="ename" name="ename" class="easyui-textbox" />
				</td>
			</tr>
			<tr>
				<th>电话</th>
				<td>
					<input id="tel" name="tel" class="easyui-textbox"/>
				</td>
				<th>传真</th>
				<td>
					<input id="fax" name="fax" class="easyui-textbox"/>
				</td>
			</tr>
			<tr>
				<th>主管</th>
				<td>
					<input id="manager" name="manager" class="easyui-textbox"/>
				</td>
			</tr>
			<tr>
				<th>描述</th>
				<td colspan="3">
					<textarea id="description" name="description"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>