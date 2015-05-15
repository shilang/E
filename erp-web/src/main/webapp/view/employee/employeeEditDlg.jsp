<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript"> 
	$(function(){
		$("#form").form({
			url: 'employee/employeeAction!persistenceEmployee.action',
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
				result = $.parseJSON(result);
				if(result.status){
					parent.$.modalDialog.openner.datagrid("reload");
					parent.$.modalDialog.handler.dialog("close");
				}
				$.erp.submitSuccess(result.title, result.message);
			}
		});
		
	
		$("#departmentName").combobox({
			width: 176,
			url: '',
			valueField: '',
			textField: '',
			onSelect: function(node){
				$("#departmentId").val(node.id);
			}
		});
		
		$("#degreeName").combobox({
			width: 176,
			url: '',
			valueField: '',
			textField: '',
			onSelect: function(node){
				$("#degree").val(node.id);
			}
		});
		
		$("#dutyName").combobox({
			width: 176,
			url: '',
			valueField: '',
			textField: '',
			onSelect: function(node){
				$("#duty").val(node.id);
			}
		});
	});
</script>

<div class="dlgcontent">
	<form id="form" method="post">
		<table>
			<tr>
				<th>代码</th>
				<td><input id="number" name="number" class="easyui-textbox" data-options="required:true"/></td>
				<th>名称</th>
				<td><input id="name" name="名称" class="easyui-textbox" data-options="required:true"/></td>
			</tr>
			<tr>
				<th>部门名称</th>
				<td><input id="departmentId" name="departmentId" type="hidden" />
					<input id="departmentName" name="departmentName" class="easyui-textbox" data-options="required:true"/></td>
				<th>性别</th>
				<td><select id="gender" name="gender" class="easyui-combobox" data-options="required:true">
						<option value="男">男</option>
						<option value="女">女</option>
				</select></td>
			</tr>
			<tr>
				<th>出生日期</th>
				<td><input id="birthday" name="birthday" class="easyui-textbox"/></td>
				<th>文化程度</th>
				<td><input id="degree" name="degree" type="hidden" /> <input
					id="degreeName" name="degreeName" class="easyui-textbox"/></td>
			</tr>
			<tr>
				<th>电话</th>
				<td><input id="tel" name="tel" class="easyui-textbox"/></td>
				<th>移动电话</th>
				<td><input id="mobile" name="mobile" class="easyui-textbox"/></td>
			</tr>
			<tr>
				<th>职务</th>
				<td><input id="duty" name="duty" type="hidden" /> <input
					id="dutyName" name="dutyName" class="easyui-textbox"/></td>
				<th>入职日期</th>
				<td><input id="hireDate" name="hireDate" class="easyui-textbox"/></td>
			</tr>
			<tr>
				<th>离职日期</th>
				<td><input id="leaveDate" name="leaveDate" class="easyui-textbox"/></td>
				<th>住址</th>
				<td><input id="address" name="address" class="easyui-textbox"/></td>
			</tr>
			<tr>
				<th>电子邮件</th>
				<td><input id="email" name="email" class="easyui-textbox"/></td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="3"><textarea id="remark" name="remark"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>