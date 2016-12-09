<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>待办</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
	var $dg;
	var _width = $(this).width();
	var _tstatus = '提交';
	var _fystatus = '基本费用';
	var _qstatus = '签收';
	var _sstatus = '审核';
	var _psstatus = '评审';
	$(function(){
		$dg = $('#dg');
		$dg.datagrid({
			url: 'task/list',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pageSize: 20,
			pagination: true,
			rownumbers: true,
			fitColumns: false,
			striped: true,
			border: false,
			singleSelect: true,
			idField: 'id',
			columns: [[
				{field:'id', title:'任务ID',width:_width*0.08},           
				{field:'taskDefinitionKey', title:'任务Key',width:_width*0.1,hidden:true},           
				{field:'name', title:'任务名称',width:_width*0.15},           
				{field:'number', title:'单据编号',width:_width*0.15,
					formatter:function(value,row,index){
						if(row&&row.auditModel){
							return row.auditModel.number;
						}
					}	
				},           
				{field:'processDefinitionId', title:'流程定义ID',width:_width*0.15,hidden:true},           
				{field:'processInstanceId', title:'流程实例ID',width:_width*0.05,hidden:true},           
				{field:'priority', title:'优先级',width:_width*0.05,hidden:true},           
				{field:'owner', title:'任务创建人',width:_width*0.12,
					formatter:function(value,row,index){
						if(row&&row.auditModel){
							return row.auditModel.creater;
						}
					}	
				},
				{field:'createTime', title:'创建日期',width:_width*0.15},           
				{field:'dueDate', title:'任务逾期',width:_width*0.1,hidden:true},           
				{field:'description', title:'任务描述',width:_width*0.15},           
				{field:'assignee',title:'操作',width:_width*0.084,
					formatter:function(value,row,index){
						if(row&&row.auditModel){
							var businessType = row.auditModel.taskBusinessType;
							var status = '';
							if(businessType == 'commit'){
								status = _tstatus;
							}else if(businessType == 'amend'){
							    status = _fystatus;
							}else if(businessType == 'check' || businessType == 'change'){
								if(row.assignee){
									status = _sstatus;
								}else{
									status = _qstatus;
								}
							}else if(businessType == 'review'){
								status = _psstatus;
							}
							return '<a class="operbtn" href="javascript:void(0);" >' + status + '</a>';
						}else{
							return '<span>错误</span>';
						}
					}	
				}
			]],
			onLoadSuccess: function(data){
				var $operbtn = $('.operbtn');
				$operbtn.each(function(index){
					var status = this.innerText;
					$(this).linkbutton({
						width: 70,
						plain:false,
						onClick: function(){
							processTask(status, data.rows[index]);
						}
					});
				});
			},
			toolbar: '#tb'
		});
	});
	
	function processTask(status, row){
		var taskId = row.id;
		var url = '';
		if(status == _qstatus){
			url = 'task/signed';
			submitTask(url,{taskId:taskId})
		}else if(status == _sstatus){
			checkTask(row.id, row.name, row.renderedTaskForm, row.auditModel);
		}else if(status == _tstatus){
			parent.$.messager.confirm($.erp.hint, "确定提交任务吗?", function(r){
				if(r){
					url = 'task/submitWithForm';
					submitTask(url, {taskId:taskId});
				}
			});
		}else if(status == _fystatus){
			amendTask(taskId, row.auditModel);
		}else if(status == _psstatus){
			reviewTask(row.auditModel);
		}
	}
	
	function reviewTask(auditModel){
		var row = undefined;
		var url = 'task/getEntity';
		var params = {businessClass:auditModel.businessClass, businessKey:auditModel.businessKey};
		$.when($.ajax({url:url,data:params})).done(function(data){		
			row = data;
		}).done(function(){
			function closeWindow(){
				parent.$.modalDialog.handler.dialog('destroy');
				parent.$.modalDialog.handler = undefined;
			}
			parent.$.modalDialog({
				title: '订单评审',
				width: 800,
				height: 600,
				href: auditModel.path,
				onLoad: function(){
					var $parentWindow = parent.$.modalDialog.handler;
					if(row){
						//load form data
						var $form = $parentWindow.find('#form');
						$form.form('load', row);
						
						//load entry data
						setTimeout(function(){
							var $entry = $parentWindow.find('#reviewdg');
							$entry.datagrid('reload', {interId: row.interId});
						},100);
						
						// test getReviewNodeName ajax request.
						setTimeout(function(){
							var reviewFun = parent.review;
							if(reviewFun){
								reviewFun("${sessionScope.shiroUser.account}",row.procInstId);
							}
						}, 100);
					}
				},
				buttons: [{
					text: '保存',
					iconCls: 'icon-save',
					width: 80,
					handler: function(){
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
						setTimeout(function(){
							closeWindow();
						},100);
						setTimeout(function(){
							$dg.datagrid('reload');
						},500);
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					width: 80,
					handler: function(){
						closeWindow();
					}
				}]
			});
			
		});
	}
	
	function amendTask(taskId, auditModel){
		var formData = null;
		var url = 'transportInfo/findByPid';
		var params = {pid:auditModel.businessKey};
		$.when($.erp.ajax(url,params)).done(function(data){
			formData = data.rows[0];
		}).done(function(){
			
			function closeDlg(){
				parent.$.modalDialog.handler.dialog('destroy');
				parent.$.modalDialog.handler = undefined;
			}
			
			parent.$.modalDialog({
				title: '基本费用',
				width: 800,
				height: 600,
				href: auditModel.path,
				buttons: [{
					text: '查看详细',
					iconCls: 'icon-show',
					width: 80,
					handler: function(){
						auditModel.path = 'view/salesMgmt/salesOutStockNotice/salesOutStockNoticeEditDlg.jsp';
					    parent.pAuditModel = auditModel;
						var detailDlg =	parent.$('<div />').dialog({
							title: '详细内容',
							width: 800,
							height: 600,
							content: '<iframe id="detail" src="view/msgMgmt/taskDetail.jsp" frameborder="0" style="border:0;width:100%;height:99.6%;"></iframe>',
							border: false,
							buttons: [{
								text: '关闭',
								iconCls: 'icon-cancel',
								width: 80,
								handler: function(){
									parent.pAuditModel = undefined;
									detailDlg.dialog('destroy');
								}
							}]
						}); 
					}  	  
				},{
					text: '提交',
					iconCls: 'icon-save',
					width: 80,
					handler: function(){
						parent.$.messager.confirm($.erp.hint, "确定提交任务吗?", function(r){
							if(r){
								var f = parent.$.modalDialog.handler.find('#form');
								f.find('#taskId').val(taskId);
								f.submit();
								setTimeout(function(){
									closeDlg();
									$dg.datagrid('reload');
								},100);
							}
						});
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					width: 80,
					handler: function(){
						closeDlg();
					}
				}],
				onLoad: function(){
					if(parent.init){
						parent.init();
					}
					var $interId = parent.$.modalDialog.handler.find('#interId');
					if($interId){
						$interId.val(auditModel.businessKey);  
					}
					if(formData && formData.id){
						var $f = parent.$.modalDialog.handler.find('#form');
						$f.form('load', formData);
					}
				}
			});
		});
	}
	
	function checkTask(taskId, taskName, taskForm, auditModel){
		var vWidth = 800;
		var vHeight = 600;
		if(!auditModel.path){
			 vWidth = 400;
			 vHeight = 200;
		}
	    parent.$.modalDialog({
			title: taskName,
			width: vWidth,
			height: vHeight,
			href: 'view/msgMgmt/taskPanel.jsp',
			//cache: false,
			modal: true,
			buttons: '#chkcnt',
			onLoad: function(){
				parent.updateAuditContent(taskId, taskForm, auditModel, $dg);
			}
		});
	}
	
	function submitTask(url, params){
		$.erp.ajax(url,params,function(rsp){
			if(rsp.status){
				$dg.datagrid('reload');
			}
			$.erp.submitSuccess(rsp.title, rsp.message);
		});
	}
	
	function reload(t){
		$dg.datagrid('load',{type:t});
	}
	
	function unsignedTasks(){
		reload('unsigned');
	}
	
	function toDoTasks(){
		reload('toDo');
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
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-color',plain:true" onclick="unsignedTasks();">未签收</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cstbase',plain:true" onclick="toDoTasks();">待处理</a>
						</td>
					</tr>
				</table>
			</div>
			<table id="dg" title=""></table>
		</div>	
	</div>
</body>
</html>