<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>辅助资料管理</title>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript">
	var $tree;
	var $grid;
	$(function(){
		$tree = $("#tt").tree({
			url: 'auxiliaryResType/auxiliaryResTypeAction!findAuxiliaryResTypes.action',
			animate: true,
			onContextMenu: function(e, node){
				e.preventDefault();
				$("#tt").tree('select', node.target);
				$('#tree-mm').menu('show',{
					left: e.pageX,
					top: e.pageY
				});
			},
			onClick: getResMessage
		});
		
		$grid = $("#dg").datagrid({
			url: 'auxiliaryResMessage/auxiliaryResMessageAction!findAuxiliaryResMessages.action',
			width: 'auto',
			height: $(this).height(),
			rownumbers: true,
			striped: true,
			border: false,
			singleSelect: true,
			columns:[[
			        {field:'code', title:'代码', width: parseInt($(this).width() * 0.1)},
			        {field:'name', title: '名称', width: parseInt($(this).width() * 0.1)}
			]],toolbar: '#tb'
		});
	});
	
	function addResTypeDlg(){
		var node = $tree.tree('getSelected');
		if(node){
			parent.$.modalDialog({
				title: '添加资源类别',
				width: 280,
				height: 180,
				href: 'view/auxiliaryRes/auxiliaryResTypeEditDlg.jsp',
				onLoad: function(){
					if(node){
						var f = parent.$.modalDialog.handler.find("#form");
						f.form('load',{
							"pid": node.id
						});
					}
				},
				buttons:[{
					text: '保存',
					iconCls: 'icon-ok',
					handler: function(){
						parent.$.modalDialog.openner = $tree;
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					handler: function(){
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				}]
			});
			
		}
	}
	
	function updateResTypeDlg(){
		var node = $tree.tree('getSelected');
		if(node){
			parent.$.modalDialog({
				title: '编辑资源类别',
				width: 280,
				height: 180,
				href: 'view/auxiliaryRes/auxiliaryResTypeEditDlg.jsp',
				onLoad: function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form('load', node.attributes);
				},
				buttons:[{
					text: '修改',
					iconCls: 'icon-edit',
					handler: function(){
						parent.$.modalDialog.openner = $tree;
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					handler: function(){
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				}]
			});
		}else{
			parent.$.messager.show({
				title: '提示',
				msg: '请选择一行记录！',
				timeout: 1000 * 2
			});
		}
	}
	
	function delResType(){
		var node = $tree.tree('getSelected');
		if(node){
			parent.$.messager.confirm("提示","确定要删除记录吗？",function(r){
				if(r){
					$.post("auxiliaryResType/auxiliaryResTypeAction!delAuxiliaryResType.action",{
						id: node.id
					}, function(rsp){
						if(rsp.status){
							$tree.tree('remove', node.target);
						}
						parent.$.messager.show({
							title: rsp.title,
							msg: rsp.message,
							timeout: 1000 * 2
						});
					},"JSON").error(function(){
						parent.$.messager.show({
							title: '提示',
							msg: '提交错误了！',
							timeout: 1000 * 2
						});
					});
				}
			});
			
		}
	}
	
	function addResMessageDlg(){
		var node = $tree.tree('getSelected');
		if(node){
			parent.$.modalDialog({
				title: '添加资源代码',
				width: 280,
				height: 200,
				href: 'view/auxiliaryRes/auxiliaryResMessageEditDlg.jsp',
				onLoad: function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form('load',{
						resId: node.id
					});
				},
				buttons:[{
					text: '保存',
					iconCls: 'icon-ok',
					handler: function(){
						parent.$.modalDialog.openner = $grid;
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					handler: function(){
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
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
			parent.$.modalDialog({
				title: '修改资源代码',
				width: 280,
				height: 200,
				href: 'view/auxiliaryRes/auxiliaryResMessageEditDlg.jsp',
				onLoad: function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form('load', row);
				},
				buttons:[{
					text: '修改',
					iconCls: 'icon-edit',
					handler: function(){
						parent.$.modalDialog.openner = $grid;
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					handler: function(){
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
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
			parent.$.modalDialog.confirm("提示","确定删除资源代码?",function(r){
			if(r){
				$.post("auxiliaryRes/auxiliaryResMessage!delAuxiliaryResMessage.action",
						{id: row.messageId},function(rsp){
							if(rsp.status){
								var idx = $grid.datagrid('getRowIndex', row);
								$grid.datagird('deleteRow', idx);
							}
							parent.$.messager.show({
								title: rsp.title,
								msg: rsp.message,
								timeout: 1000 * 2
							});
						},"JSON").error(function(){
							parent.$.modalDialog({
								title: '提示',
								msg: '提交错误！',
								timeout: 1000 * 2
							});
						});
				}
			});
		}else{
			parent.$.messager.show({
				title: '提示',
				msg: '请选择资源代码',
				timeout: 1000 * 2
			});
		}
	}
	
	function getResMessage(node){
		
		$grid.datagrid('load',{
			id: node.id
		});
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" title="" 
		style="height:82px;overflow:hidden;padding:5px;">
			<div class="well well-samll">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>辅助资料</strong></span>进行编辑！
				</p>
			</div>
		</div>
		<div data-options="region:'west',split:true,border:true" style="width:300px;">
			<div class="easyui-panel" data-options="border:false" title="资源类别" style="padding:10px;">
				<ul id="tt"></ul>
			</div>
			<div id="tree-mm" class="easyui-menu" style="width:120px;">
				<div onclick="addResTypeDlg();" data-options="iconCls:'icon-add'">添加类别</div>
				<div onclick="updateResTypeDlg();" data-options="iconCls:'icon-edit'">编辑类别</div>
				<div onclick="delResType();" data-options="iconCls:'icon-remove'">删除类别</div>
				<div></div>
			</div>
		</div>
		<div data-options="region:'center',border:true,fit:true">
			<div id="tb">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addResMessageDlg();">添加资源代码</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateResMessageDlg();">编辑资源代码</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delResMessage();">删除资源代码</a>
			</div>
			
			<table id="dg" title="资源代码"></table>
		</div>
	</div>
	
</body>
</html>