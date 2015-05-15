<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>国别地区管理</title>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $('#dg');
		$grid = $dg.datagrid({
				url: 'area/areaAction!findAreas.action',
				width: $(this).width() - 10,
				height: $(this).height() - 82,
				collapsible: true,
				pagination: true,
				rownumbers: true,
				striped: true,
				singleSelect: true,
				border: true,
				idField: 'areaId',
				columns: [[
					{field: 'number', title: '代码'},
					{field: 'name', title: '名称'},
					{field: 'nameEn', title: '英文名称'},
				]],
				toolbar: '#tb'

		});
		
		$('#searchbox').searchbox({
				width: 250,
				menu: '#mm',
				prompt: '模糊查询',
				searcher: function(value, name){
					$dg.datagrid('reload', $.erp.searcher(name, value));
				}
		});
	});
	
	function addAreaDlg(){
		parent.$.modalDialog({
			title: '添加国别地区',
			iconCls: 'icon-add',
			width: 600,
			height: 400,
			href: 'view/area/areaEditDlg.jsp',
			buttons: [{
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
					parent.$.modalDialog.handler.dialog("destroy");
					parent.$.modalDialog.handler = undefined;
				}
			}]
		});		
	}
	
	function updateAreaDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title: '修改国别地区',
				iconCls: 'icon-edit',
				width: 600,
				height: 400,
				href: 'view/area/areaEditDlg.jsp',
				onLoad: function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form('load', row);
				},
				buttons: [{
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
						parent.$.modalDialag.handler.dialog("destroy");
						parent.$.modalDialog.handler = undefined;
					}
				}]
			});
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delArea(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("area/areaAction!delArea.action",{"areaId": row.areaId},function(rsp){
						if(rsp.status){
							var idx = $dg.datagrid('getRowIndex');
							$dg.datagrid('deleteRow', idx);
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
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center', border:false" style="padding: 5px;">
			<div class="well well-small" style="margin-bottom: 5px;">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>国家</strong></span>进行编辑!
				</p>
			</div>
			
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addAreaDlg();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateAreaDlg();">修改</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delArea();">删除</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick=""> 导出Excel</a>
						</td>
						<td>
							<input id="searchbox" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级查询</a>	
						</td>
					</tr>
				</table>	
			</div>
			
			<div id="mm">
				<div name="name">国家名称</div>	
			</div>
			
			<table id="dg" title="国别地区管理"></table>
		</div>
	</div>
</body>
</html>
