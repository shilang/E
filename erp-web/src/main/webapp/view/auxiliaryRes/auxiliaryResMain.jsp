<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>辅助资料管理</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $panel;
	var $tree;
	//var $grid;
	$(function(){
		$panel = $("#panel").panel({
			title: "资源类别",
			height: $(this).height() - 44,
			border:false  
		});
		
		$tree = $("#tt").tree({
			url: 'auxiliaryResType/find.action',
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
		
		/* $grid = $("#dg").datagrid({
			url: 'auxiliaryResMessage/find.action',
			width: $(this).width() - 310,
			height: $(this).height() - 44,
			rownumbers: true,
			striped: true,
			border: false,
			singleSelect: true,
			columns:[[
			        {field:'code', title:'代码', width: parseInt($(this).width() * 0.1)},
			        {field:'name', title: '名称', width: parseInt($(this).width() * 0.1)}
			]],toolbar: '#tb'
		}); */
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
					$.post("auxiliaryResType/delete.action",{
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
	
	function getResMessage(node){
		$('#tempResId').val(node.id);
		$grid.datagrid('load',{
			id: node.id
		});
	}
	
</script>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true,border:false">
		<div class="easyui-layout" data-options="fit:true, border:false">
			<div data-options="region:'west',split:true,border:false" style="width:300px;">
				<div id="panel" style="padding:10px;">
					<ul id="tt"></ul>
				</div>
				<div id="tree-mm" class="easyui-menu" style="width:120px;">
					<div onclick="addResTypeDlg();" data-options="iconCls:'icon-add'">添加类别</div>
					<div onclick="updateResTypeDlg();" data-options="iconCls:'icon-edit'">编辑类别</div>
					<div onclick="delResType();" data-options="iconCls:'icon-remove'">删除类别</div>
					<div></div>
				</div>
			</div>
			<div data-options="region:'center',border:false,fit:true,href:'view/auxiliaryRes/auxiliaryResMessage.jsp'">
			<!-- 	<div id="tb">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addResMessageDlg();">添加资源代码</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateResMessageDlg();">编辑资源代码</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delResMessage();">删除资源代码</a>
				</div>
				
				<table id="dg" title="资源代码"></table> -->
			</div>
		</div>
	</div>
</body>
</html>