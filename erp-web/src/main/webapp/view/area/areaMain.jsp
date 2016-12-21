<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>国别地区管理</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var $grid;
	$(function(){
		$dg = $('#dg');
		$grid = $dg.datagrid({
				url: 'area/findAreas.action',
				width: $(this).width(),
				height: $(this).height(),
				collapsible: true,
				pageSize: 20,
				pagination: true,
				rownumbers: true,
				striped: true,
				singleSelect: true,
				border: false,
				idField: 'areaId',
				columns: [[
					{field: 'number', title: '代码', width: parseInt($(this).width() * 0.05)},
					{field: 'name', title: '名称', width: parseInt($(this).width() * 0.1)},
					{field: 'nameEn', title: '英文名称', width: parseInt($(this).width() * 0.1)},
					{field: 'remark', title: '备注', width: parseInt($(this).width() * 0.1)}
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
	
	function showDlg(title, iconCls, type, row, status){
		var viewPath = 'view/area/areaEditDlg.jsp';
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,'',row,
				$grid,'','',600, 400);
	}
	
	function addAreaDlg(){
		showDlg('添加国别地区','icon-add','add');
	}
	
	function updateAreaDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改国别地区','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delArea(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("area/delArea.action",{"areaId": row.areaId},function(rsp){
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
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center', border:false" >
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
			
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>
