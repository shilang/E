<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#form").form({
			url: 'department/departmentAction!persistenceDepartment.action',
			onSubmit: function(){
				parent.$.messager.progress({
					title: '提示',
					msg: '数据处理中,请稍后...'
				});
				var isValid = $(this).form('validate');
				if(!isValid){
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success: function(result){
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if(result.status){
					parent.$.modalDialog.openner.treegrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				}
				parent.$.messager.show({
					title: result.title,
					msg: result.message,
					timeout: 1000 * 2
				});
			}
		});
		
		$("#managerName").combobox({
			url: '',
			valueField: '',
			textField: '',
			onSelect: function(node){
				$("#manager").val(node.id);
			}
		});
	});
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<input id="departmentId" name="departmentId" type="hidden"/>
		<input id="pid" name="pid" type="hidden" />
		<input id="created" name="created" type="hidden" />
		<input id="creater" name="creater" type="hidden" />
		<input id="status" name="status" type="hidden" />
		<table>
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
					<input id="managerName" name="managerName" class="easyui-textbox"/>
					<input id="manager" name="manager" type="hidden"/>
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