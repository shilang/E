<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>物料</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'material/find.action',
			width: $(this).width(),
			height: $(this).height(),
			pageSize: 20,
			pagination: true,
			collapsible: true,
			rownumbers: true,
			striped: true,
			singleSelect: true,
			border: false,
			idField: 'itemId',
			columns: [[
				{field: 'number', title: '代码', width: parseInt($(this).width() * 0.08), sortable:true},
				{field: 'name', title: '名称', width: parseInt($(this).width() * 0.1), sortable:true},
				{field: 'fullName', title: '全名', width: parseInt($(this).width() * 0.1), sortable:true},
				{field: 'model', title: '规格型号', width: parseInt($(this).width() * 0.1), sortable:true},
				{field: 'unitName', title: '计量单位', width: parseInt($(this).width() * 0.1)},
				{field: 'topName', title: '物料分类', width: parseInt($(this).width() * 0.1), sortable:true},
				{field: 'useStateName', title: '使用状态', width: parseInt($(this).width() * 0.1), sortable:true},
				{field: 'materialAttrName', title: '物料属性', width: parseInt($(this).width() * 0.2), sortable:true},
				{field: 'remark', title: '备注', width: parseInt($(this).width() * 0.2)}
			]],
			toolbar: '#tb'
		});
		
		$("#searchbox").searchbox({
			width: 250,
			menu: '#mm',
			prompt: '模糊查询',
			searcher: function(value, name){
				$dg.datagrid('reload', $.erp.searcher(name, value));
			}
		});
	});
	
	function showDlg(title, iconCls, type, row, status){
		var viewPath = 'view/material/materialEditDlg.jsp';
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,'',row,
				$grid,'','',600, 450);
	}
	
	function addMaterialDlg(){
		showDlg('添加物料','icon-add','add');
	}
	
	function updateMaterialDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改物料','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delMaterial(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("material/delete.action",{"itemId": row.itemId},function(rsp){
						if(rsp.status){
							var idx = $dg.datagrid('getRowIndex', row);
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
	<div class="easyui-layout" data-options="fit:true,border:false" >
		<div data-options="region:'center', border:false">
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="addMaterialDlg();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true" onclick="updateMaterialDlg();">修改</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="delMaterial();">删除</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel', plain:true" onclick="">导出Excel</a>
						</td>
						<td>
							<input id="searchbox" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search', plain:true" onclick="">高级查询</a>
						</td>
					</tr>
				</table>
			</div>
			<div id="mm">
				<div name="number">代码</div>
				<div name="name">名称</div>
				<div name="model">规格型号</div>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>