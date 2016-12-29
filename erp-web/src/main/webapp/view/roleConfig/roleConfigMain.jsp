<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>用户角色</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
var $role;
var $user;
$(function() {
	$user = $("#user");
	$user.datagrid({
		url : "user/findUsers.action",
		width : 'auto',
		height : $(this).height(),
		collapsible: true,
		pagination:true,
		pageSize: 20,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		border:false,
		columns : [ [ 
		              {field : 'name',title : '用户名',width : parseInt($(this).width()*0.1),editor : {type:'validatebox',options:{required:true}}},
		              {field : 'description',title : '描述',width : parseInt($(this).width()*0.2),align : 'left',editor : "text"}
		          ] ],
		toolbar:"#tbUser",
		onClickRow:getRoles
	});
	
	$role = $("#role");
	$role.datagrid({
			url : "role/findNotPage.action",
			width : 'auto',
			height : $(this).height()-45,
			collapsible: true,
			pagination:false,
			border:false,
			rownumbers:true,
			singleSelect:false,
			striped:true,
			idField: 'roleId',
			columns : [ [ {field:'ck',checkbox:true},
			              {field : 'name',title : '角色名称',width :  parseInt($(this).width()*0.1),align : 'center',editor : {type:'validatebox',options:{required:true}}},
			              {field : 'description',title : '角色描述',width :  parseInt($(this).width()*0.3),align : 'center',editor : "text"}
			              ] ],
			toolbar:"#tbRole"
	});
	
	//搜索框
	$("#searchbox").searchbox({ 
		 width: 250,
		 menu:"#mm", 
		 prompt :'模糊查询',
	    searcher:function(value,name){   
            $user.datagrid('reload',$.erp.searcher(name, value)); 
	    }
	   
	});
	
});

 function saveUserRoles(){
	 var selectRow=$user.datagrid("getSelected");
	 var selectRows=$role.datagrid("getSelections");
	 var isCheckedIds=[];
	 $.each(selectRows,function(i,e){
		 isCheckedIds.push(e.roleId);
	 });
	 if(selectRow){
		 $.post("user/saveUserRoles.action", {userId:selectRow.userId,isCheckedIds:isCheckedIds.toString()}, function(rsp){
			 $.erp.submitSuccess(rsp.title, rsp.message);
		 },"json").error(function(){
				$.erp.submitErr();
		 });
	 }else{
		 parent.$.messager.show({
				title :"提示",
				msg :"请选择角色！",
				timeout : 1000 * 2
			});
	 } 
 }
 
 function getRoles(rowIndex, rowData){
	 $.post("user/findUserRoleList.action", {userId:rowData.userId}, function(rsp) {
	     $role.datagrid("unselectAll");
		 if(rsp.length!=0){
			 $.each(rsp,function(i,e){
				 $role.datagrid("selectRecord",e.roleId);
			 });
		 }else{
			 parent.$.messager.show({
					title :"提示",
					msg : "该用户暂无角色！",
					timeout : 1000 * 2
			 });
		 }
		}, "JSON").error(function() {
			$.erp.submitErr();
		});
}
 
</script>
</head>
<body>
   <div class="easyui-panel"  data-options="fit:true,border:false">
	 <div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'west',split:true,border:false" style="width:500px;">
				<div id="tbUser" class="tb">
					<table>
						<tr>
							<td>
								<input id="searchbox" type="text"/>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="mm">
						<div name="name">用户名</div>
				</div>
				
				<table id="user" title="用户"></table>
		</div>
		<div data-options="region:'center',border:false">
				<div id="tbRole" class="tb">
					<table>
						<tr>
							<td>
								<shiro:hasPermission name="roleCfg">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveUserRoles();">保存</a>
								</shiro:hasPermission>
							</td>
						</tr>
					</table>
				</div>
				
				<table id="role" title="角色"></table>
		</div>
	  </div>
	</div>
</body>
</html>