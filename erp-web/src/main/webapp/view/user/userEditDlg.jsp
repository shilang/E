<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var $form;
	var $name;
	var $oldPwd;
	var entry;
	$(function(){
		var modpwd = <%=request.getParameter("modpwd")%>;
	
		$form = $("#form");
		$form.form({
			url: modpwd?'user/updatePasswd.action':'user/persistence.action',
			onSubmit: submit,
			success: success
		});
		
		$name = $("#name");
		$name.textbox({
			width: 176,
			required: true,
			buttonText: '浏览',
			buttonIcon: 'icon-search',
			onClickButton: function(){
				var selUrl = "view/employee/employeeMain.jsp?selSta=true";
				var iframe = "<iframe src='" + selUrl + "' frameborder=\"0\" style=\"border:0;width:100%;height:99.4%;\"></iframe>";
				var $dlg =  $('<div/>').dialog({   
					title: '请选择记录',    
					width: 800,    
					height: 600,    
					closed: false,    
					cache: false,  
					content: iframe,
					modal: true
				});	
				parent.$.modalDialog.selDlg = $dlg;
			},
			onChange: function(newValue,oldValue){
				$("#employeeId").val("");
			}
		});
		
		$("#password").textbox({
			width: 176,
			value: '',
			required: true
		});
		
		$oldPwd = $("#oldPassword").textbox({
			width: 176,
			value: '',
			required:true,
			disabled:true 
		});
		
		if(modpwd){
			$name.textbox("readonly");
			$("#oldPwd").css("display","table-row");
			$oldPwd.textbox('enable');
		}
		
		entry = new $.erp.simpleEntry();
	});
	
	function submit(){
		return verifyPassword() && entry.submit($form);
	}
	
	function success(result){
		entry.success(result, process);
	}
	
	function process(){
		$form.form('reset');
		parent.$.modalDialog.openner.datagrid('reload');
	}
	
	function verifyPassword(){
		var password = $("#password").textbox('getValue');
		var truePassword = $("#truePassword").textbox('getValue');
		if(password && password == truePassword){
			return true;
		}else{
			parent.$.messager.alert("提示", "密码无效!");
			return false;
		}
	}
	
</script>

<div class="dlgcontent">
		<form id="form" method="post">
				<input name="userId" id="userId" type="hidden" />
				<input name="created" id="created" type="hidden" />
				<input name="creater" id="creater" type="hidden"/>
				<input name="status" id="status" type="hidden"/>
				<table class="simple">
					<tr>
						<th>用户名</th>
						<td>
							<input name="name" id="name" type="text"/>
							<input name="employeeId" id="employeeId" type="hidden"/>
						</td>
					</tr>
					<tr id="oldPwd" style="display:none;">
						<th>旧的密码</th>
						<td><input name="oldPassword" id="oldPassword" type="password"/></td>
					</tr>
					<tr>
						<th>新的密码</th>
						<td><input name="password" id="password" type="password" /></td>
					</tr>
					<tr>
						<th>确认密码</th>
						<td><input name="truePassword" id="truePassword" type="password" class="easyui-textbox" data-options="required:true"/></td>
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