<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>权限编辑</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $role;
	var $function;
	var $grid;
	$(function() {
		$role = $("#role");
		$grid = $role.datagrid({
				url : "role/find.action",
				width : 'auto',
				height : $(this).height(),
				collapsible: true,
				pagination : true,
				pageSize: 20,
				border : false,
				rownumbers : true,
				singleSelect : true,
				striped : true,
				columns : [ [ {
					field : 'name',
					title : '角色名称',
					width : parseInt($(this).width() * 0.1),
					align : 'center',
					editor : {
						type : 'validatebox',
						options : {
							required : true
						}
					}
				}, {
					field : 'description',
					title : '角色描述',
					width : parseInt($(this).width() * 0.1),
					align : 'center',
					editor : "text"
				}, {
					field : 'sort',
					title : '排序',
					width : parseInt($(this).width() * 0.1),
					align : 'center',
					editor : "numberbox"
				} ] ],
				toolbar : '#tbRole',
				onClickRow : getPermission
			});

		$function = $("#function");
		/*
		$function.treegrid({
					width : 'auto',
					height : $(this).height() - 44,
					url : "permission/permissionAssignmentAction!findAllFunctionList.action",
					rownumbers : true,
					animate : true,
					collapsible : true,
					fitColumns : true,
					border : false,
					striped : true,
					singleSelect : false,
					cascadeCheck : true,
					deepCascadeCheck : true,
					idField : 'id',
					treeField : 'name',
					parentField : 'pid',
					columns : [ [ {
						field : 'ck',
						checkbox : true
					}, {
						field : 'name',
						title : '程序名称',
						width : parseInt($(this).width() * 0.25)
					},
					{
						field : 'myid',
						title : '程式编码',
						width : parseInt($(this).width() * 0.1),
						align : 'center'
					}, {
						field : 'type',
						title : '程式类型',
						width : parseInt($(this).width() * 0.1),
						align : 'center',
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
					}, {
						field : 'description',
						title : '程式描述',
						width : parseInt($(this).width() * 0.2),
						align : 'left'
					} ] ],
					toolbar : '#tb',
					onClickRow : function(row) {
						//级联选择   
						$function.treegrid('cascadeCheck', {
							id : row.id, //节点ID   
							deepCascade : true
						//深度级联   
						});
					}
				});
		*/
		
		$function.tree({
			width : 'auto',
			height : $(this).height() - 44,
			url : "permission/find.action",
			animate: true,
			checkbox: true,
			cascadeCheck: true,
			lines: true,
			idFiled : 'id',
			textFiled : 'name',
			parentField : 'pid'
		});
		
		//搜索框
		$("#searchbox").searchbox({
			width: 250,
			menu : "#mm",
			prompt : '模糊查询',
			searcher : function(value, name) {
				var str = "{\"roleName\":\"" + name + "\",\"value\":\""+ value + "\"}";
				var obj = eval('(' + str + ')');
				$role.datagrid('reload', obj);
			}
		});
	});

	function collapseAll() {
		var node = $function.tree('getSelected');
		if (node) {
			$function.tree('collapseAll', node.target);
		} else {
			$function.tree('collapseAll');
		}
	}
	
	function expandAll() {
		var node = $function.tree('getSelected');
		if (node) {
			$function.tree('expandAll', node.target);
		} else {
			$function.tree('expandAll');
		}
	}
	
	function refresh() {
		$function.tree('reload');
	}
	
	
	function getPermission(rowIndex, rowData) {
		$.post("permission/getRolePermission.action",
						{roleId : rowData.roleId},
						function(rsp) {
							// uncheck 
							var nodes = $function.tree('getChecked');
							$.each(nodes, function(i, e){
								$function.tree('uncheck',e.target);
							});
							//check
							if (rsp.length != 0) {
								$.each(rsp,function(i, e) {
									var node = $function.tree('find', e.permissionId);
									if(node){
										if($function.tree('isLeaf', node.target)){
											$function.tree('check',node.target);
										}
									}
								});
							} else {
								parent.$.messager.show({
									title : "提示",
									msg : "该角色暂无权限!",
									timeout : 1000 * 2
								});
							}
						}, "JSON").error(function() {
					parent.$.messager.show({
						title : "提示",
						msg : "获取权限失败!",
						timeout : 1000 * 2
					});
				});
	}
	
	function savePermission() {
		var selections = $function.tree('getChecked',['checked','indeterminate']);
		var selectionRole = $role.datagrid('getSelected');
		var checkedIds = [];
		$.each(selections, function(i, e) {
			checkedIds.push(e.id);
		});
		if (selectionRole) {
			$.ajax({
						url : "permission/savePermission.action",
						data : "roleId=" + selectionRole.roleId
								+ "&checkedIds="
								+ (checkedIds.length == 0 ? "" : checkedIds),
						success : function(rsp) {
							parent.$.messager.show({
								title : rsp.title,
								msg : rsp.message,
								timeout : 1000 * 2
							});
						},
						error : function() {
							parent.$.messager.show({
								title : "提示",
								msg : "分配失败！",
								timeout : 1000 * 2
							});
						}

					});
		} else {
			parent.$.messager.show({
				title : "提示",
				msg : "请选择角色！",
				timeout : 1000 * 2
			});
		}
	}
	
	function delRole() {
		var row = $role.datagrid('getSelected');
		if (row) {
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){			
					$.post("role/delete.action",{roleId:row.roleId},
							function(rsp){
								if(rsp.status){
									var rowIndex = $role.datagrid('getRowIndex', row);
									$role.datagrid('deleteRow', rowIndex);
								}
								$.erp.submitSuccess(rsp.title, rsp.message);
					},"json").error(function(){
						
					});
				}
				
			});
		}else {
			$.erp.noSelectErr();
		}
	}
	
	function updateRoleDlg() {
		var row = $role.datagrid('getSelected');
		if (row) {
			parent.$.modalDialog({
				title : "编辑角色",
				iconCls: 'icon-edit',
				width : 600,
				height : 400,
				href : "view/permission/roleEditDlg.jsp",
				onLoad : function() {
					var f = parent.$.modalDialog.handler.find("#form");
					f.form("load", row);
				},
				buttons : [ {
					text : '编辑',
					iconCls : 'icon-ok',
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
		}else {
			$.erp.noSelectErr();	
		}
	}
	
	function addRoleDlg() {
		parent.$.modalDialog({
			title : "添加角色",
			iconCls: 'icon-add',
			width : 600,
			height : 400,
			href : "view/permission/roleEditDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
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
	
</script>
</head>
<body>
	  <div class="easyui-panel" data-options="fit:true,border:false" >
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'west',split:true,border:false"
				style="width: 65%;">
				<div id="tbRole" class="tb">
					<table>
						<tr>
							<td>
								<shiro:hasPermission name="roleAdd">
									<a href="javascript:void(0);" class="easyui-linkbutton"
										iconCls="icon-add" plain="true" onclick="addRoleDlg();">添加</a>
								</shiro:hasPermission> 
								<shiro:hasPermission name="roleMod">
									<a href="javascript:void(0);" class="easyui-linkbutton"
										iconCls="icon-edit" plain="true" onclick="updateRoleDlg();">修改</a>
								</shiro:hasPermission> 
								<shiro:hasPermission name="roleDel">
									<a href="javascript:void(0);" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="delRole();">删除</a>
								</shiro:hasPermission>
							</td>
							<td>
								<input id="searchbox" type="text" />
							</td>  
						</tr>
					</table>
				</div>
				
				<div id="mm">
					<div name="name">角色名称</div>
				</div>
				 
				<table id="role" title="角色"></table>
			</div>
			<div data-options="region:'center',border:false">
				<div id="tb">
					<table>
						<tr>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-undo" plain="true" onclick="expandAll();">展开</a> 
								<a	href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-redo" plain="true" onclick="collapseAll();">收缩</a>
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-reload" plain="true" onclick="refresh();">刷新</a>
								<a href="javascript:void(0);" class="easyui-linkbutton"
										iconCls="icon-config" plain="true" onclick="savePermission();">保存设置</a>
							</td>
						</tr>
					</table>
				</div>
				<table id="function" title="权限"></table>
			</div>
		</div>
	</div>
</body>
</html>