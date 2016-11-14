<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
	var $grid;
		
	$(function(){
		$grid = $("#resdg").datagrid({
			url: 'auxiliaryResMessage/find.action',
			width: 'auto',
			height: 'auto',
			rownumbers: true,
			striped: true,
			border: false,
			singleSelect: true,
			columns:[[
			        {field:'code', title:'代码', width: parseInt($(this).width() * 0.1)},
			        {field:'name', title: '名称', width: parseInt($(this).width() * 0.1)}
			]],toolbar: '#restb'
		});
	});
	
	function addResMessageDlg(){
		//var node = $tree.tree('getSelected');
		var resId = $('#tempResId').val();
		if(resId){
			    parent.$resWindow = parent.$('<div/>').dialog({
				title: '添加',
				width: 280,
				height: 200,
				modal: true,
				href: 'view/auxiliaryRes/auxiliaryResMessageEditDlg.jsp',
				onLoad: function(){
					var f = parent.$resWindow.find("#resMessageForm");
					f.form('load',{
						resId: resId
					});
				},
				buttons:[{
					text: '保存',
					iconCls: 'icon-ok',
					handler: function(){
						parent.$resWindow.openner = $grid;
						var f = parent.$resWindow.find("#resMessageForm");
						f.submit();
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					handler: function(){
						parent.$resWindow.dialog('destroy');
						parent.$resWindow = undefined;
					}
				}]
				
			});
			
		}else{
			parent.$.messager.show({
				title: '提示',
				msg: '请选择资源类别！',
				timeout: 1000 * 2
			});
		}
	}
	
	function updateResMessageDlg(){
		var row = $grid.datagrid('getSelected');
		if(row){
			 parent.$resWindow = parent.$('<div/>').dialog({
				title: '修改',
				width: 280,
				height: 200,
				modal: true,
				href: 'view/auxiliaryRes/auxiliaryResMessageEditDlg.jsp',
				onLoad: function(){
					var f = parent.$resWindow.find("#resMessageForm");
					f.form('load', row);
				},
				buttons:[{
					text: '修改',
					iconCls: 'icon-edit',
					handler: function(){
						parent.$resWindow.openner = $grid;
						var f = parent.$resWindow.find("#resMessageForm");
						f.submit();
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					handler: function(){
						parent.$resWindow.dialog('destroy');
						parent.$resWindow = undefined;
					}
				}]
			});
		}else{
			parent.$.messager.show({
				title: '提示',
				msg: '请选择资源代码',
				timeout: 1000 * 2
			});
		}
	}
	
	function delResMessage(){
		var row = $grid.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm("提示","确定删除资源?",function(r){
			if(r){
				$.post("auxiliaryResMessage/delete.action",
						{id: row.messageId},function(rsp){
							if(rsp.status){
								var idx = $grid.datagrid('getRowIndex', row);
								$grid.datagrid('deleteRow', idx);
							}
							
							$.erp.submitSuccess(rsp.title, rsp.message);
							
						},"JSON").error(function(){
							$.erp.submitErr();
						});
				}
			});
		}else{
			$.erp.noSelectErr();
		}
	}

</script>

<input id="tempResId" type="hidden"/>

<div id="restb">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addResMessageDlg();">添加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateResMessageDlg();">编辑</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delResMessage();">删除</a>
</div>
				
<table id="resdg" title=""></table>