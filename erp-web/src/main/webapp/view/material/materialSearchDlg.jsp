<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	var materialSearchDg;

	$(function(){
		materialSearchDg = $('#materialSearchDg');
		
		materialSearchDg.datagrid({
			url: 'material/find.action',
			width: 636,
			height: 426,
			collapsible: true,
			pageSize: 20,
			pagination: true,
			rownumbers: true,
			border: false,
			striped: true,
			singleSelect: true,
			idField: 'itemId',
			columns:[[
						{field:'number',title:'代码',width:80},        
						{field:'name',title:'名称',width:150},        
						{field:'model',title:'规格型号',width:250},
						{field:'fullName',title:'全名',width:250}
			        ]],
			toolbar: '#materialSearchTb',
			onDblClickRow: onDblClickRow
		});
		
		$('#materialSearcher').searchbox({
			width: 250,
			menu: '#materialSearchMenu',
			prompt: '模糊查询',
			searcher: function(value, name){
				materialSearchDg.datagrid('reload',$.erp.searcher(name, value));
			}
		});
	});
	
	function closeWindow(){
		setTimeout(function(){
			msDlg.dialog('destroy');
		},100);
	}
	
	function select(row){
		$.erp.materialCol.prototype.select(row);
	}
	
	function onDblClickRow(index, row){
		select(row);
		closeWindow();
	}

</script>
<div>
	<div id="materialSearchTb">
		<table>
			<tr>
				<td>
					<input id="materialSearcher" />
				</td>
			</tr>
		</table>
	</div>
	<div id="materialSearchMenu">
		<div name="model">规格型号</div>
		<div name="number">代码</div>
		<div name="name">名称</div>
		<div name="fullName">全名</div>
	</div>
	<table id="materialSearchDg"></table>
</div>