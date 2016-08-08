<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>员工管理</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var selSta = <%=request.getParameter("selSta")%>;
	var $dg;
	var $grid;
	$(function(){
		if(selSta){
			$("#addOper").hide();
			$("#updOper").hide();
			$("#delOper").hide();
			$("#showOper").hide();
			$("#exportOper").hide();
			$("#hint").hide();
		}
		
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url: 'employee/find.action',
			width: $(this).width(),
			height: $(this).height(),
			pagination: true,
			collapsible: true,
			rownumbers: true,
			striped: true,
			border: false,
			singleSelect: true,
			idField: 'employeeId',
			columns: [[
				{field: 'number', title: '代码', width: parseInt($(this).width()* 0.05)},
				{field: 'name', title: '名称', width: parseInt($(this).width()* 0.1)},
				{field: 'gender', title: '性别', width: parseInt($(this).width()* 0.03)},
				{field: 'birthday', title: '出生日期', width: parseInt($(this).width()* 0.1)},
				{field: 'degreeName', title: '文化程度', width: parseInt($(this).width()* 0.05)},
				{field: 'tel', title: '电话', width: parseInt($(this).width()* 0.07)},
				{field: 'dutyName', title: '职位', width: parseInt($(this).width()* 0.06)},
				{field: 'hireDate', title: '入职日期', width: parseInt($(this).width()* 0.1)},
				{field: 'leaveDate', title: '离职日期', width: parseInt($(this).width()* 0.1)},
				{field: 'address', title: '住址', width: parseInt($(this).width()* 0.1)},
				{field: 'email', title: '电子邮件', width: parseInt($(this).width()* 0.1)},
				{field: 'remark', title: '备注', width: parseInt($(this).width()* 0.1)}
			]],
			toolbar: '#tb',
			onDblClickRow: onDblClickRow
		});
		
		$("#searchbox").searchbox({ 
			 width: 250,
			 menu:"#mm", 
			 prompt :'模糊查询',
		    searcher:function(value,name){    
	            $dg.datagrid('reload',$.erp.searcher(name, value)); 
		    }
		});
	});
	
	function onDblClickRow(index, row){
		if(selSta){
			parent.$.modalDialog.handler.find("#name").textbox('setValue', row.name);
			parent.$.modalDialog.handler.find("#employeeId").val(row.employeeId);
			parent.$.modalDialog.selDlg.dialog("destroy");
		}else{
			return;
		}
	}
	
	function showDlg(title, iconCls, type, row, status){
		var viewPath = 'view/employee/employeeEditDlg.jsp';
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,'',row,
				$grid,'','',600, 450);
	}
	
	
	function addEmployeeDlg(){
		showDlg('添加职员','icon-add','add');
	}
	
	function updateEmployeeDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改职员','icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function delEmployee(){
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint,$.erp.deleteQueryMsg, function(r){
				if(r){
					$.post("employee/delete.action", {"employeeId": row.employeeId}, function(rsp){
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
	
	function hold(){
		$.erp.hold();
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region: 'center', border: false" >
			<div id="tb">
				<table>
					<tr>
						<td>
							<a id="addOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addEmployeeDlg();">添加</a>
							<a id="updOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateEmployeeDlg();">修改</a>
							<a id="delOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delEmployee();">删除</a> 
							<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-show" plain="true" onclick="hold();">查看</a>
							<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton" icnoCls="icon-excel" plain="true" onclick="hold();">导出Excel</a>
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
				<div name="test">测试</div>
			</div>
			
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>