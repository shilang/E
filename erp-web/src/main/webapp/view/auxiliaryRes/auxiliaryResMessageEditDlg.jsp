<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#resMessageForm").form({
			url: 'auxiliaryResMessage/persist.action',
			onSubmit: function(){
				/* parent.$.messager.progress({
					title: '提示',
					msg: '数据处理中,请稍后...'
				}); */
				var isValid = $(this).form('validate');
				/* if(!isValid){
					parent.$.messager.progress('close');
				} */
				return isValid;
			},
			success: function(result){
				//parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if(result.status){
					parent.$resWindow.openner.datagrid('reload');
					parent.$resWindow.dialog('destroy');
					parent.$resWindow = undefined;
				}
				 parent.$.messager.show({
					title: result.title,
					msg: result.message,
					timeout: 1000 * 2
				});
			}
		});
	});
</script>

<div style="padding: 30px;">
	<form id="resMessageForm" method="post">
		<input id="messageId" name="messageId" type="hidden"/>
		<input id="resId" name="resId" type="hidden" />
		<input id="created" name="created" type="hidden" />
		<input id="creater" name="creater" type="hidden" />
		<input id="status" name="status" type="hidden" />
		<table>
			<tr>
				<th>代码</th>
				<td>
					<input id="code" name="code" class="easyui-validatebox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>名称</th>
				<td>
					<input id="name" name="name" class="easyui-validatebox" data-options="required:true"/>
				</td>
			</tr>
		</table>
	</form>
</div>