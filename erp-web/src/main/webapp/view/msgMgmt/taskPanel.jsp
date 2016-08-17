<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
<!--
	.auditPanel{
		margin-bottom:20px; 
		padding:0 20px;
	}
-->
</style>

<script type="text/javascript">
	var $mainGrid;

	function submitAudit(){
		var url = 'task/submitWithForm';
		var $auditForm = $('#auditForm');
		var param = $auditForm.serialize();
		var remark = $auditForm.find('#remark').val();
		//don't verify audit remark
		/* if(!remark){
			return false;
		} */
		$.erp.ajax(url, param, function(rsp){
			if(rsp.status){
				if($mainGrid){
					$mainGrid.datagrid('reload');
				}
			}
		});
		closeAuditDlg();
	}
	
	function closeAuditDlg(){
		parent.$.modalDialog.handler.dialog('destroy');
		parent.$.modalDialog.handler = undefined;
	}
	
	function updateAuditContent(taskId, taskForm, auditModel, mainGrid){
		if(taskId){
			$('#taskId').val(taskId);
		}
/* 		$('#taskBusinessType').val(auditModel.taskBusinessType);
		$('#path').val(auditModel.path); */
		if(mainGrid){
			$mainGrid = mainGrid;
		}
		//request row data
		var row = undefined;
		var url = 'task/getEntity';
		var param = {businessClass:auditModel.businessClass,businessKey:auditModel.businessKey};
		$.when($.ajax({url:url,data:param})).done(function(data){
			row = data;
		}).done(function(){
			var vHref = auditModel.path;	
			$('#contentPanel').panel({
				width:'auto',
				height: 'auto',
				href : vHref?vHref:'',
				border: false,
				onLoad: function(){
			 		if(vHref && row){
			 			var $parentWindow = parent.$.modalDialog.handler;
			 			//initial control
			 			if(init){
							init();
						}
						
						function fillData(){
							//load form data
							var f = $parentWindow.find("#form");
							if(f){
								f.form('load',row);
							}
						}
						
						var $deptId = $parentWindow.find("#departmentId");
						if($deptId){
							var deptIdDefer = $deptId.erpDept();
							$.when(deptIdDefer).done(function(){
								fillData();
							});
						}else{
							fillData();
						}
						
						//load entry data
						var entry = $parentWindow.find('table[id^=dg]');
						if(entry){
							entry.datagrid('reload',{interId:row.interId});
						}
					}
				}
			});
			
			//build content for task form
			if(taskForm){
				var $auditPanel = $('#auditPanel');
				$auditPanel.addClass('auditPanel');
				$auditPanel.html(taskForm);
				if(initForm){
					initForm(row);			
				}
			}
		});
	}
</script>

<div id="contentPanel"></div>
<div id="auditPanel"></div>
<div id="chkcnt" style="padding: 10px 0 10px 20px; background: #F5F5F5;">
	<form id="auditForm">
		<input id="taskId" name="taskId" type="hidden">
<!-- 		<input id="taskBusinessType" name="taskBusinessType" type="hidden">
		<input id="path" name="path" type="hidden"> -->
		<textarea id="remark" name="remark" rows="1" cols="30" style="margin-bottom: -6px;"></textarea>
		<select id="result" name="result" class="easyui-combobox" data-options="panelHeight:50,editable:false" style="width:80px;">
			<option value="approve" selected="selected">同意</option>
			<option value="reject">拒绝</option>
		</select>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="width:80,iconCls:'icon-save'" onclick="submitAudit();">提交审核</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="width:80,iconCls:'icon-cancel'" onclick="closeAuditDlg();">关闭</a>
	</form>
</div>
