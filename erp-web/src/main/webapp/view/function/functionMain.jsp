<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>程序管理</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	var typedata = [ {"type" : "F","typeName" : "菜单"}, 
	                 {"type" : "O","typeName" : "操作"} ];
	$(function() {
		$dg = $("#dg");
		$grid = $dg.treegrid({
					width : $(this).width(),
					height : $(this).height(),
					url : "function/findById.action",
					rownumbers : true,
					animate : true,
					collapsible : true,
					fitColumns : true,
					striped : true,
					border : false,
					//singleSelect:false,
					idField : 'permissionId',
					treeField : 'name',
					frozenColumns : [ [ {
						title : '程序名称',field : 'name',
						width : parseInt($(this).width() * 0.2),
						formatter : function(value) {
							return '<span style="color:red">' + value
									+ '</span>';
						}
					} ] ],
					columns : [ [ 
							{
								field : 'description',
								title : '程序描述',
								width : parseInt($(this).width() * 0.1),
								align : 'left'
							},
							{
								field : 'pname',
								title : '父程序名称',
								width : parseInt($(this).width() * 0.1),
								align : 'left'
							},
							{
								field : 'sort',
								title : '排序编码',
								width : parseInt($(this).width() * 0.1)
							},
							{
								field : 'iconCls',
								title : '程序图标',
								align : 'center',
								width : parseInt($(this).width() * 0.1),
								formatter : function(value, row) {
									return "<span class='"+row.iconCls+"' style='display:inline-block;vertical-align:middle;width:16px;height:16px;'></span>";
								}

							}, {
								field : 'url',
								title : '程序路径',
								width : parseInt($(this).width() * 0.1),
								align : 'left'
							}, {
								field : 'myid',
								title : '程序编码',
								width : parseInt($(this).width() * 0.1),
								align : 'left'
							}, {
								field : 'type',
								title : '程序类型',
								width : parseInt($(this).width() * 0.1),
								align : 'left',
								formatter : function(value, row) {
									if ("F" == row.type)
										return "<font color=green>菜单<font>";
									else
										return "<font color=red>操作<font>";
								}
							}, {
								field : 'isused',
								title : '是否启用',
								width : parseInt($(this).width() * 0.1),
								align : 'center',
								formatter : function(value, row) {
									if ("Y" == row.isused)
										return "<font color=green>是<font>";
									else
										return "<font color=red>否<font>";
								}
							} ] ],
					toolbar : '#tb'
				});
	});
	
	function delFunction() {
		var node = $dg.treegrid('getSelected');
		if (node) {
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r) {
				if (r) {
					$.post("function/delete.action", {
						id : node.permissionId
					}, function(rsp) {
						if (rsp.status) {
							$dg.treegrid('remove', node.permissionId);
						}
						$.erp.submitSuccess(rsp.title, rsp.message);
					}, "JSON").error(function() {
						$.erp.submitErr();
					});
				}
			});
		} else {
			$.erp.noSelectErr();
		}
	}
	
	function updateFunctionDlg() {
		var row = $dg.treegrid('getSelected');
		if (row) {
			parent.$.modalDialog({
				title : "修改程序",
				iconCls: 'icon-edit',
				width : 600,
				height : 400,
				href : "view/function/functionEditDlg.jsp",
				onLoad : function() {
					if(!row.pid){
						row.pid = "null";
					}
					var f = parent.$.modalDialog.handler.find("#form");
					f.form("load", row);
				},
				buttons : [ {
					text : '修改',
					iconCls : 'icon-save',
					handler : function() {
						parent.$.modalDialog.openner = $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				} ]
			});
		} else {
			$.erp.noSelectErr();
		}
	}
	
	function addFunctionDlg() {
		var row = $dg.treegrid('getSelected');
		if (row) {
			if (row.type == "O") {
				parent.$.messager.show({
					title : "提示",
					msg : "操作暂无下层!",
					timeout : 1000 * 2
				});
			} else {
				
				function closeDlg(){
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
				
				parent.$.modalDialog({
					title : "添加程序",
					iconCls: 'icon-add',
					width : 600,
					height : 400,
					href : "view/function/functionEditDlg.jsp",
					onLoad : function() {
						if (row) {
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", {
								"pid" : row.permissionId
							});
						}
					},
					buttons : [ {
						text : '保存',
						iconCls : 'icon-save',
						handler : function() {
							parent.$.modalDialog.openner = $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find("#form");
							f.submit();
							//$grid.treegrid('reload');
							//closeDlg();
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							closeDlg();
						}
					} ]
				});
			}
		} else {
			parent.$.modalDialog({
				title : "添加程序",
				iconCls: 'icon-add',
				width : 600,
				height : 400,
				href : "view/function/functionEditDlg.jsp",
				buttons : [ {
					text : '保存',
					iconCls : 'icon-save',
					handler : function() {
						parent.$.modalDialog.openner = $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				} ]
			});
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
						<td>
							<shiro:hasPermission name="funAdd">
								<!--  <a href="javascript:void(0);" class="easyui-splitbutton" data-options="menu:'#mm1',iconCls:'icon-add'">添加</a>
								<div id="mm1" style="width:150px;">
									<div data-options="iconCls:'icon-undo'" onclick="addStandPlaceNode();">增加并列项</div>
									<div data-options="iconCls:'icon-redo'" onclick="addSubitemNode();">增加子项</div>
								</div>-->
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-add" plain="true" onclick="addFunctionDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="funMod">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-edit" plain="true" onclick="updateFunctionDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="funDel">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-remove" plain="true" onclick="delFunction();">删除</a>
							</shiro:hasPermission>
							<!-- <shiro:hasPermission name="funEndEdit">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="endEdit();">结束编辑</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="funSave">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveNodes();">保存</a>
							</shiro:hasPermission> -->
						</td>
					</tr>
				</table>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>