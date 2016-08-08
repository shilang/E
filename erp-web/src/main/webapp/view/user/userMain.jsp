<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
var $dg;
var $temp;
var $grid;
$(function() {
	$dg = $("#dg");
	$grid=$dg.datagrid({
		url : "user/findUsers.action",
		width : $(this).width(),
		height :  $(this).height(),
		collapsible: true,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:true,
		striped:true,
		idField: 'userId',
		columns : [[ 
		              {field : 'name',title : '用户名',width : parseInt($(this).width()*0.1)},
		              {field : 'forbidden',title : '用户状态',width : parseInt($(this).width()*0.1)},
		              {field : 'description',title : '描述',width : parseInt($(this).width()*0.3)}
		           ]],
		           toolbar:'#tb'
	});
	//搜索框
	$("#searchbox").searchbox({
		 width: 250,
		 menu:"#mm", 
		 prompt :'模糊查询',
	     searcher:function(value,name){
            $dg.datagrid('reload', $.erp.searcher(name, value)); 
	    }
	   
	});
});

function addUserDlg(){
	parent.$.modalDialog({
		title: '添加用户',
		iconCls: 'icon-add',
		width: 600,
		height: 400,
		href: 'view/user/userEditDlg.jsp',
		buttons:[{
			text: '保存',
			iconCls: 'icon-save',
			handler: function(){
				parent.$.modalDialog.openner = $grid;
				var f = parent.$.modalDialog.handler.find("#form");
				f.submit();
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				parent.$.modalDialog.handler.dialog("destroy");
				parent.$.modalDialog.handler = undefined;
			}
		}]
	});
}

function updateUserDlg(){
	var row = $dg.datagrid('getSelected');
	if(row){
		parent.$.modalDialog({
			title: '修改用户',
			iconCls: 'icon-edit',
			width: 600,
			height: 400,
			href: 'view/user/userEditDlg.jsp',
			onLoad: function(){
				var f = parent.$.modalDialog.handler.find("#form");
				f.form('load', row);
			},
			buttons:[{
				text: '修改',
				iconCls: 'icon-save',
				handler: function(){
					parent.$.modalDialog.openner = $grid;
					var f = parent.$.modalDialog.handler.find("#form");
					f.submit();
				}
			},{
				text: '取消',
				iconCls: 'icon-cancel',
				handler: function(){
					parent.$.modalDialog.handler.dialog("destroy");
					parent.$.modalDialog.handler = undefined;
				}
			}]
		});
	}else{
		$.erp.noSelectErr();
	}
}

function delUser(){
	var row = $dg.datagrid('getSelected');
	if(row){
		parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
			if(r){
				$.post("user/delUser.action", {userId:row.userId},function(rsp){
					if(rsp.status){
						var idx = $dg.datagrid('getRowIndex', row);
						$dg.datagrid('deleteRow', idx);
					}
					$.erp.submitSuccess(rsp.title, rsp.message);
				},"json").error(function(){
					$.erp.submitErr();
				});
			}
		});
	}else{
		$.erp.noSelectErr();
	}
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" >
			<div id="tb">
				<table>
					<tr>
						<td style="padding-left:2px">
							<shiro:hasPermission name="userAdd">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUserDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="userMod">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateUserDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="userDel">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delUser();">删除</a>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox" type="text"/>
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="userSearch();">高级查询</a>
						</td>
					</tr>
				</table>
			</div>
			<div id="mm">
					<div name="name">用户名</div>
			</div>
	  		<table id="dg" title=""></table>
  		</div>
	</div>
</body>
</html>