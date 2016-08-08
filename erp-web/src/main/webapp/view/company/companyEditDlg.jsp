<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$("#form").form({
			url :"companyInfo/persist.action",
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.status) {
					parent.reload;
					parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}else{
					parent.$.messager.show({
						title :  result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}
			}
		});
	});
</script>

<div class="dlgcontent">
	<form id="form" method="post">
			<input name="companyId" id="companyId"  type="hidden"/>
			<input name="created" id="created"  type="hidden"/>
			<input name="creater" id="creater"  type="hidden"/>
			<input name="status" id="status"  type="hidden"/>
			 <table class="simple">
				 <tr>
				    <th>公司名称</th>
					<td><input name="name" id="name" class="easyui-textbox" data-options="required:true"/></td>
					<th>公司电话</th>
					<td><input name="tel" id="tel" type="text"  class="easyui-textbox" data-options="required:true"/></td>
				 </tr>
				 <tr>
				    <th>传真</th>
					<td><input name="fax" id="fax" type="text" class="easyui-textbox" data-options="required:true"/></td>
					<th>地址</th>
					<td><input id="address" name="address" type="text" class="easyui-textbox" data-options="required:true"/></td>
				 </tr>
				  <tr>
				    <th>邮箱</th>
					<td><input id="email" name="email" type="text" class="easyui-textbox" /></td>
					<th>联系人</th>
					<td><input id="contact" name="contact" type="text" class="easyui-textbox"/></td>
				 </tr>
				 <tr>
					<th>描述</th>
					<td colspan="3"><textarea  name="description"></textarea></td>
				</tr>
			 </table>
	</form>
</div>

