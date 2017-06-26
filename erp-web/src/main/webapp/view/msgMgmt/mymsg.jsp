<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>我的消息</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var _width = $(this).width();
	var queryParams = {sort:'interId',order:'desc'};
	$(function(){
		
		$.extend(queryParams,$.erp.searcher('owner','${sessionScope.shiroUser.account}'));
		
		$dg = $('#dg');
		$dg.datagrid({
			url:'msg/list',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pageSize: 25,
			pagination: true,
			//pagePosition: 'top',
			rownumbers: true,
			fitColumns: true,
			striped: true,
			border: false,
			singleSelect: false,
			checkOnSelect: false,
			queryParams: queryParams,
			idField: 'interId',
			columns: [[
			    {field:'read',width:20,
			    	formatter:function(value,row,index){
			    		if(value == 0){
			    			return '<img width="20px" height="20px" border="0" src="web-static/images/new24.png" />';
			    		}else{
			    			return '';
			    		}
			    	}
			    },
				{field:'chk',width:10,checkbox:true},           
				{field:'interId',title:'消息ID',width:25},           
				{field:'type',title:'类型',width:_width*0.05,
					formatter:function(value,row,index){
						if(value == 'process'){
							return '流程消息';
						}
					}},           
				{field:'name',title:'标题',width:_width*0.05},      
				{field:'content',title:'内容',width:_width*0.2}, 
				{field:'sender',title:'发送者',width:_width*0.05}, 
				{field:'created',title:'创建时间',width:_width*0.1}
			]],
			toolbar: '#tb'			
		});
	});
	
	function getSelectedIds(){
		var rows = $dg.datagrid('getChecked');
		var ids = '';
		var fg = false;
		for(var i = 0; i < rows.length; i++){
			ids = ids + (fg?',':'') + rows[i].interId;
			fg = true;
		}
		return ids;
	}
	
	function updateReadStatus(read){
		var ids = getSelectedIds();
		$.ajax({url:'msg/updateReadStatus',data:{ids:ids,read:read}})
		.done(function(data){
			$dg.datagrid('clearChecked');
			$dg.datagrid('reload');
		});
	}
	
	function flagUnread(){
		updateReadStatus(0);
	}
	
	function flagRead(){
		updateReadStatus(1);
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false">
			<div id="tb">
				<table>
					<tr>
						<td>
							<!-- <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-all-select',plain:true" onclick="">全选</a> -->
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-mail-mark-unread',plain:true" onclick="flagUnread();">标记未读</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-mail-mark-read',plain:true" onclick="flagRead();">标记已读</a>
						</td>
					</tr>
				</table>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>