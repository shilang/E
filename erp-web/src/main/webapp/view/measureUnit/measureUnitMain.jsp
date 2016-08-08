<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>计量单位</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'measureUnit/find.action',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			rownumbers: true,
			striped: true,
			singleSelect: true,
			border: false,
			idField: 'measureUnitId',
			columns: [[
				{field: 'number', title: '代码', width: parseInt($(this).width() * 0.05)},
				{field: 'name', title: '名称', width: parseInt($(this).width() * 0.1)},
				{field: 'groupName', title: '计量组', width: parseInt($(this).width() * 0.1)},
				{field: 'conversation', title: '换算方式', width: parseInt($(this).width() * 0.1)},
				{field: 'coefficient', title: '换算率', width: parseInt($(this).width() * 0.1)},
				{field: 'auxClass', title: '类别', width: parseInt($(this).width() * 0.1)}
			]],
			toolbar: '#tb' 
		});
		
	});
	
	function showDlg(title, iconCls, type, row, status){
		var viewPath = 'view/measureUnit/measureUnitEditDlg.jsp';
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,'',row,
				$grid,'','',600, 450);
	}
	
	function addMeasureUnitDlg(){
		showDlg('添加计量单位','icon-add','add');
	}
	
	function updateMeasureUnitDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改计量单位','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delMeasureUnit(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("measureUnit/delete.action",{"measureUnitId": row.measureUnitId},function(rsp){
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
		<div data-options="region:'center', border:false" >
			<div id="tb">
				<table>
					<tr>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addMeasureUnitDlg();">添加</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateMeasureUnitDlg();">修改</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delMeasureUnit();">删除</a>
						</td>
					</tr>
				</table>
			</div>
		
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>