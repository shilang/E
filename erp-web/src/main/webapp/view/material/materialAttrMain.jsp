<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>物料属性</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	$(function(){
		$dg = $("#dg");
		$dg.treegrid({
			width: $(this).width(),
			height: $(this).height(),
			url: 'materialAttr/findById.action',
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: false,
			striped : true,
			border: false,
			idField: 'attrId',
			treeField: 'name',
			frozenColumns: [[
			     	{field:'name',title:'名称', width: parseInt($(this).width()*0.2),
			     		formatter: function(value){
			     			return '<span style="color:red">' + value
			     			+ '</span>';
			     		}	
			     	}            
			]],
			columns: [[
			        {field:'pname', title: '父名称',width: parseInt($(this).width()*0.2)},
			        {field:'remark', title: '备注',width: parseInt($(this).width()*0.2)}
			]],
			toolbar: '#tb'
		});
	});
	
	function addMaterialAttrDlg(){
		var row = $dg.treegrid('getSelected');
		parent.$.modalDialog({
			title: '添加属性',
			iconCls: 'icon-add',
			width: 600,
			height: 400,
			href: 'view/material/materialAttrEditDlg.jsp',
			onLoad: function(){
				if(row){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form("load", {pid:row.attrId,pname:row.name});
				}
			},
			buttons: [{
				text: '保存',
				iconCls: 'icon-save',
				handler: function(){
					parent.$.modalDialog.openner = $dg;
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
	
	function updateMaterialAttrDlg(){
		var row = $dg.treegrid("getSelected");
		if(row){
			parent.$.modalDialog({
				title: '修改属性',
				iconCls: 'icon-edit',
				width: 600,
				height: 400,
				href: 'view/material/materialAttrEditDlg.jsp',
				onLoad: function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form("load", row);
				},
				buttons: [{
					text: '修改',
					iconCls: 'icon-edit',
					handler: function(){
						parent.$.modalDialog.openner = $dg;
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
	
	function delMaterialAttr(){
		var row = $dg.treegrid("getSelected");
		if(row){
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg,function(r){
				if(r){
					$.post("materialAttr/delete.action",{attrId:row.attrId},function(rsp){
						if(rsp.status){
							$dg.treegrid('remove', row.attrId);
						}
						$.erp.submitSuccess(rsp.title,rsp.message);
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
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="addMaterialAttrDlg();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="updateMaterialAttrDlg();">修改</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="delMaterialAttr();">删除</a>
						</td>
					</tr>
				</table>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>