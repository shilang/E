<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#form").form({
			url: 'measureUnit/persist.action',
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
</script>
	
<div class="dlgcontent">
	<form id="form" method="post">
			<input id="measureUnitId" name="measureUnitId" type="hidden" />
			<input id="created" name="created" type="hidden" />
			<input id="creater" name="creater" type="hidden" />
			<input id="status" name="status" type="hidden" />
			<table class="simple">
				<tr>
					<th>代码</th>
					<td>
						<input id="number" name="number" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<th>名称</th>
					<td>
						<input id="name" name="name" class="easyui-textbox" data-options="required: true"/>
					</td>
				</tr>
				<tr>
					<th>计量组</th>
					<td>
						<input id="groupName" name="groupName" class="easyui-textbox" data-options="required: true"/>
					</td>
				</tr>
				<tr>
					<th>换算方式</th>
					<td>
						<input id="conversation" name="conversation" class="easyui-textbox" />
					</td>
				</tr>
				<tr>
					<th>换算率</th>
					<td>
						<input id="coefficient" name="coefficient" class="easyui-numberbox" data-options="required:true,precision:2"/>
					</td>
				</tr>
				<tr>
					<th>类别</th>
					<td>
						<input id="auxClass" name="auxClass" class="easyui-textbox"/>
					</td>
				</tr>
				
			</table>
	</form>	
</div>