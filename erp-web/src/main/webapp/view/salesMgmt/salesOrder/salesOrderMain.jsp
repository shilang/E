<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/euinc.jsp"></jsp:include>
<title>销售订单</title>
<script type="text/javascript">
	var selSta = <%=request.getParameter("selSta")%>;
	var $dg;
	var $grid;
	var salOrderShowCust = false;
	var salOrderShowPrice = false;
	
	//var $entry;
	var queryParams = {sort:'interId',order:'desc'};
	$(function(){
		
		if(selSta){
			$(".submain").hide();
			$.extend(queryParams, $.erp.searcher('result', $.erp.resultCheckOk, 'int'));
		}
		
		initOtherPerm();
		
		$dg = $("#dg");
		$grid = $dg.datagrid({
			url:'salesOrder/findAll.action',
			width: $(this).width(),
			height: $(this).height(),
			collapsible: true,
			pageSize: 25,
			pagination: true,
			//pagePosition: 'top',
			rownumbers: true,
			border: false,
			striped: true,
			singleSelect: true,
			queryParams: queryParams,
			idField: 'interId',
			columns:[[
						{field: 'result', title: '审核状态', width: parseInt($(this).width() * 0.08),
							formatter:function(value, row){
								return $.erp.getResultStatus(value);
						},sortable:true},
			        	{field:'orderStatus',title:'订单状态',width:parseInt($(this).width()*0.08),
							formatter:function(value, row){
								return getOrderStatusColor(value);
							},
							sortable:true
			        	},
			        	{field:'billNo',title:'单据编号',width:parseInt($(this).width()*0.12),sortable:true},
			        	{field:'date',title:'日期',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'customerName',title:'购货单位', hidden:!salOrderShowCust,width:parseInt($(this).width()*0.15),sortable:true},
			        /* 	{field:'salesScopeName',title:'销售范围',width:parseInt($(this).width()*0.1),sortable:true}, */
			        	{field:'salesWayName',title:'销售方式',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'fetchWayName',title:'交货方式',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'fetchAddrName',title:'交货地点',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'transitAheadTime',title:'交货日期',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'settlementName',title:'结算方式',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'settlementDate',title:'结算日期',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'currencyName',title:'币别',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),sortable:true},
			        	{field:'exchangeRate',title:'汇率',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),sortable:true},
			        	{field:'amount',title:'金额',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),sortable:true},
			        	{field:'freightAmount',title:'货运金额',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),sortable:true},
			        	{field:'tradeWayName',title:'贸易方式',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.1)},
			        	{field:'totalAmount',title:'总金额',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),sortable:true},
			        	{field:'settleAmount',title:'结算金额',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),sortable:true},
			        	{field:'bankCost',title:'银行费用',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),sortable:true},
			        	{field:'settleCurrencyName',title:'结算币别',hidden:!salOrderShowPrice,width:parseInt($(this).width()*0.06),sortable:true},
			        	{field:'settleStatus',title:'结算状态',width:parseInt($(this).width()*0.06),
			        		formatter:function(value,row){
			        			return $.erp.getSettleStatus(value);
			        		},
			        		sortable:true
			        	},
			        	{field:'managerName',title:'主管',width:parseInt($(this).width()*0.08),sortable:true},
			        	{field:'departmentName',title:'部门',width:parseInt($(this).width()*0.08),sortable:true},
			        	{field:'employeeName',title:'业务员',width:parseInt($(this).width()*0.08),sortable:true},
			        	{field:'createrName',title:'制单',width:parseInt($(this).width()*0.08),sortable:true},
			        	{field:'checkerName',title:'审核人',width:parseInt($(this).width()*0.08),sortable:true},
			        	{field:'checkDate',title:'审核日期',width:parseInt($(this).width()*0.16),sortable:true},
			        	{field:'sourceBillNo',title:'源单单号',width:parseInt($(this).width()*0.1),sortable:true},
			        	{field:'sourceType',title:'源单类型',width:parseInt($(this).width()*0.1)},
			        	{field:'explanation',title:'摘要',width:parseInt($(this).width()*0.4)}
			        ]],
			toolbar: '#tb',
			//onClickRow: onClickRow,
			onDblClickRow: onDblClickRow
		});
		
		/* $grid.datagrid('getPager').pagination({
			buttons:'#tb'
		}); */
		
		$("#searchbox").searchbox({
			width: 250,
			menu: '#mm',
			prompt: '模糊查询',
			searcher: function(value,name){
				$dg.datagrid('reload', $.erp.searcher(name, value));
			}
		});
		
	});
	
	function getOrderStatusColor(value){
		var status = {msg:'',color:'black'};
		if(value == '已备料'){
			status.msg = value;
			status.color = "maroon";
		}else if(value == '已排单'){
			status.msg = value;
			status.color = "green";
		}else if(value == '生产中'){
			status.msg = value;
			status.color = "black";		
		}else if(value == '待确认'){
			status.msg = value;
			status.color = "goldenrod";		
		}else if(value == '已备货(部分)'){
			status.msg = value;
			status.color = "blue";		
		}else if(value == '已备货'){
			status.msg = value;
			status.color = "blue";		
		}else if(value == '已出货(部分)'){
			status.msg = value;
			status.color = "red";		
		}else if(value == '已出货'){
			status.msg = value;
			status.color = "red";
		}
		return '<span style="color:'+status.color+';">' + status.msg + '</span>';
	}
	
	function onDblClickRow(index, row){
		if(selSta){
			var interId = row.interId;
			var billNo = row.billNo;
			parent.$.modalDialog.handler.find("#sourceBillNo").textbox("setValue", billNo);
			var type = parent.$.modalDialog.type;
			var url = "";
			if(type == "FHTZ"){
				url = "salesOutStockNotice/findEntriesByType.action";
			}else if(type == "XSCK"){
				url = "salesOutStock/findEntriesByType.action";
			}else if(type == "XSFP"){
				url = "salesInvoice/shareEntries";
			}else if(type == "YSK"){
				url = "proceeds/shareEntries";
				//if('settleAmount' in row){delete row.settleAmount}
				//if('bankCost' in row){delete row.bankCost;}
			}else{
				return;
			}
			//load form data
			var f = parent.$.modalDialog.handler.find('#form');
			$.erp.excludeAttrForFormFill(row);
			f.form('load', row);
			
			//load entry data
			$.erp.ajax(url,
					{entryType:'order',interId:interId},
					function(data){
						$.each(data.rows,function(idx, value){
							value.fetchDate = parent.$.modalDialog.date;
							value.sourceBillNo = billNo;
							parent.$.modalDialog.dg.datagrid('appendRow',value);
						});
					},true);
			parent.$.modalDialog.selDlg.dialog("destroy");
		}else{
			updateSalesOrderDlg();
		}
	}
	
	function initOtherPerm(){
		if($('#salOrderShowCust').length > 0){
			salOrderShowCust = true;
		}
		if($('#salOrderShowPrice').length > 0){
			salOrderShowPrice = true;
		}
	}
	
	function addSalesOrderDlg(){
		showDlg('添加订单','icon-add','add');
	}
	
	function updateSalesOrderDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('修改订单', 'icon-edit','update',row);
		}else{
			$.erp.noSelectErr();
		}
	}

	function showSalesOrderDlg(){
		var row = $dg.datagrid('getSelected');
		if(row){
			showDlg('查看订单','icon-show','show',row);
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function showDlg(title, iconCls,type,row){
		var viewPath = 'view/salesMgmt/salesOrder/salesOrderEditDlg.jsp';
		var numMethod = "getSalesOrderNumber";
		var processDefKey = "sales-order-process-apply";
		var entityName = "SalesOrder";
		
		$.erp.showBusinessDlg(title,iconCls,viewPath,type,numMethod,row,
				$grid,processDefKey,entityName);
	}
	
	function delSalesOrder(){
		var row = $dg.datagrid('getSelected');
		
		if(!row){
			$.erp.noSelectErr();
			return;
		}
		
		if($.erp.checkResultStatus(row)){
			return;
		}
		
		parent.$.messager.confirm($.erp.hint, $.erp.deleteQueryMsg, function(r){
			if(r){
				$.post("salesOrder/delete.action",{interId:row.interId},function(rsp){
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
	}
	
	function orderReview(){
		var row = $dg.datagrid('getSelected');
		if(row){
			
			function closeWindow(){
				parent.$.modalDialog.handler.dialog('destroy');
				parent.$.modalDialog.handler = undefined;
			}
			
			var $reviewDg = null;
			var mainTbHeight = 0;
			var reviewDlgHeight = 0;
			var dgOrgHeight = 0;
			
			parent.$.modalDialog({
				title: '订单评审',
				width: 800,
				height: 600,
				maximizable: true,
				href: 'view/salesMgmt/salesOrder/salesOrderReview.jsp',
				onLoad: function(){
					var $parentWindow = parent.$.modalDialog.handler;
					
					var $mainTb = $parentWindow.find('table.simple');
					if($mainTb.length > 0){
						mainTbHeight = $mainTb.height();
					}
					
					var $reviewDlg = $parentWindow.find('#reviewDiv');
					if($reviewDlg.length > 0){
						reviewDlgHeight = $reviewDlg.height();
					}
					
					$reviewDg = $parentWindow.find('#reviewdg');
					if($reviewDg.length > 0){
					}else{
						$reviewDg = null;
					}
					
					if($reviewDg){
						dgOrgHeight = $reviewDg.datagrid('options').height;
					}
					
					//load form data
					var $form = $parentWindow.find('#form');
					$form.form('load', row);
					
					//load entry data
					setTimeout(function(){
						$reviewDg.datagrid('reload', {interId: row.interId});
					},100);
					
					// test getReviewNodeName ajax request.
					setTimeout(function(){
						var reviewFun = parent.review;
						if(reviewFun){
							reviewFun("${sessionScope.shiroUser.account}",row.procInstId);
						}
					}, 100);
				},
				onMaximize: function(){
					if($reviewDg){
						var newHeight = $(window.top).height() - mainTbHeight - reviewDlgHeight - 160;
						$reviewDg.datagrid('resize',{width:'auto',height:newHeight});
					}
				},
				onRestore: function(){
					if($reviewDg){
						$reviewDg.datagrid('resize',{width:'auto',height:dgOrgHeight});
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
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function modifyOrderStatus(){
		var status = $('#orderStatus').combobox('getValue');
		if(status == 'empty'){
			return;
		}
		var row = $dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm($.erp.hint,'单据['+row.billNo+']状态改为['+status+']?',function(r){
				if(r){
					$.erp.ajax('salesOrder/updateOrderStatus.action',{interId:row.interId,orderStatus:status},function(rsp){
						if(rsp.status){
							$dg.datagrid('reload');
							$dg.datagrid('selectRow', $dg.datagrid('getRowIndex', row));
						}
						$.erp.submitSuccess(rsp.title, rsp.message);
					});
				}
			});
		}else{
			$.erp.noSelectErr();
		}
	}
	
	function checkPending(){
		$.erp.checkPending($dg);
	}	
	
	function commitSalesOrder(){
		return $.erp.commitBusinessList($dg);
	}
	
	function exportExcel(){	
		var beanName = 'salesOrderServiceImpl';
		var reportSchemaName = 'salesOrderReport.xml';
		$.erp.exportExcel(beanName,reportSchemaName,$dg.datagrid('options'));
	}
	
	function hold(){
		$.erp.hold();
	}
	
</script>
</head>
<body>
	<%-- <input id="user" type="hidden" value="${sessionScope.shiroUser.account}" /> --%>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" >
			<div id="tb">
				<table>
					<tr>
						<td>
							<shiro:hasPermission name="salOrderAdd">
								<a id="addOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-add',plain:true" onclick="addSalesOrderDlg();">添加</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderMod">
								<a id="updOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-edit',plain:true" onclick="updateSalesOrderDlg();">修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderDel">
								<a id="delOper" href="javascript:void(0);" class="easyui-linkbutton submain" data-options="iconCls:'icon-remove',plain:true" onclick="delSalesOrder();">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderShow">
								<a id="showOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-show" plain="true" onclick="showSalesOrderDlg();">查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderCommit">
								<a id="commitOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-datago" plain="true" onclick="commitSalesOrder();">提交</a>
							</shiro:hasPermission>
							<div style="float: right;margin-left:5px;" class="datagrid-btn-separator submain" ></div>
						</td>
						<td>
							<shiro:hasPermission name="salOrderReview">
								<a id="ReviewOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-cstbase" plain="true" onclick="orderReview();">订单评审</a>
							 	<div style="float: right;margin-left:5px;" class="datagrid-btn-separator submain" ></div>
							</shiro:hasPermission>
						</td>
						<td>	
							 <div id="orderStatusOper" class="submain" style="display:inline-block;">
								 <select id="orderStatus" name="orderStatus" class="easyui-combobox" >
								 	<option value="empty">请选择</option>
								 	<shiro:hasPermission name="salOrderStatusProduction">
										<option value="已备料">已备料</option>
										<option value="已排单">已排单</option>
										<option value="生产中">生产中</option>
										<option value="待确认">待确认</option>
									</shiro:hasPermission>
									<shiro:hasPermission name="salOrderStatusOut">
										<option value="已备货(部分)">已备货(部分)</option>
										<option value="已备货">已备货</option>
										<option value="已出货(部分)">已出货(部分)</option>
										<option value="已出货">已出货</option>
									</shiro:hasPermission>
									<shiro:hasPermission name="salOrderStatusCancel">
										<option value="取消订单">取消订单</option>
									</shiro:hasPermission>
								 </select>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="modifyOrderStatus();">修改状态</a>
								<div style="float: right;" class="datagrid-btn-separator" ></div>
							</div>
						</td>
						<td>
							<shiro:hasPermission name="salOrderExport">
								<a id="exportOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-excel" plain="true" onclick="exportExcel();">导出Excel</a>
								<div style="float: right; margin-left:5px;" class="datagrid-btn-separator submain" ></div>
							</shiro:hasPermission>
						</td>
						<td>
							<input id="searchbox">
						</td>
						<%-- <td>
							<shiro:hasPermission name="salOrderCheck">
								<a id="pendingOper" href="javascript:void(0);" class="easyui-linkbutton submain" iconCls="icon-search" plain="true" onclick="checkPending();">待审核</a>
							</shiro:hasPermission>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="">高级查询</a>
						</td> --%>
						<td>
							<shiro:hasPermission name="salOrderShowCust">
								<label id="salOrderShowCust"></label>
							</shiro:hasPermission>
							<shiro:hasPermission name="salOrderShowPrice">
								<label id="salOrderShowPrice"></label>
							</shiro:hasPermission>
						</td>
					</tr>
				</table>
			</div>
			<div id="mm">
				<div name="billNo">单据编号</div>
				<div name="sourceBillNo">源单单号</div>
			</div>
			<table id="dg" title=""></table>
		</div>
	</div>
</body>
</html>