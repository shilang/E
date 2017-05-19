/*
 * jQuery erp plugin
 * 
 * Author: bollen
 * 
 * Date: 2015-05-14
 */
(function($){
	
	$.extend({erp:{}});
	
	//constants
	$.extend($.erp,{
		//base message
		hint: '提示',
		submitErrMsg: '提交错误！',
		submitSuccessMsg: '提交成功！',
		selectErrMsg: '请选择一行记录！',
		deleteQueryMsg: '确定删除选中的记录吗？',
		processing: '数据处理中,请稍后...',
		
		//business status value
		resultNone: 0,
		resultCheckPending: 1,
		resultCheckOk: 2,
		resultCheckChange: 3,
		//business status message
		resultNoneMsg: '',
		resultCheckPendingMsg: '待审核',
		resultCheckOkMsg: '已审核',
		resultCheckChangeMsg: '改待审核',
		
		//settle status value
		settleStatusNone: 0,
		settleStatusPart: 1,
		settleStatusFull: 2,
		//settle status message
		settleStatusNoneMsg: '',
		settleStatusPartMsg: '欠款',
		settleStatusFullMsg: '已结款',
		
		rowEditErrMsg: '编辑行数据未完成',
		//dialog duraction time
		showTimeout: 1000 * 2,	
	});
	
	//show message
	$.extend($.erp,{
		submitErr: function(vTitle, vMsg){
			parent.$.messager.show({
				title: vTitle==undefined ? $.erp.hint:vTitle,
				msg: vMsg==undefined?$.erp.submitErrMsg:vMsg,
				timeout: $.erp.showTimeout
			});
		},
		submitSuccess: function(vTitle, vMsg){
			parent.$.messager.show({
				title: vTitle==undefined ? $.erp.hint: vTitle,
				msg: vMsg==undefined ? $.erp.submitSuccessMsg : vMsg,
				timeout: $.erp.showTimeout
			});
		},
		noSelectErr: function(){
			parent.$.messager.show({
				title: $.erp.hint,
				msg: $.erp.selectErrMsg,
				timeout: $.erp.showTimeout
			});
		}
	});
	
	//utils
	$.extend($.erp,{
    	searcher: function(name, value, type){
    		var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\""+ (type?",\"searchType\":\""+type+"\"":"") +"}";
            return eval('('+str+')');
    	}
	});
	
	$.erp.isAdmin = function(){
		var user = $('#user').val();
		if(user==='admin'){
			return true;
		}
		return false;
	}
	
	/**
	 * $.erp.ajax(url,param,fun,sync)
	 */
	$.erp.ajax = function(url,param,fun,sync){
		return $.ajax({
			url: url,
			cache: false,
			async: sync?false:true,
			dataType: 'json',
			type: 'POST',
			data: param,
			success: fun
		});
	};
	
	$.erp.hold = function(){
		parent.$.messager.alert($.erp.hint,"保留功能！暂未实现.",'icon-ok');
	};
	
	$.erp.dgClean = function(grid){
		var rows = grid.datagrid('getRows');
		for(var i = 0; i < rows.length; i++){
			grid.datagrid('deleteRow', i);
		}
	};
	
	$.erp.getResultStatus = function(value){
		var status = {msg:'',color:'black'};
		switch(value){
		case $.erp.resultNone:
			status.msg = $.erp.resultNoneMsg;
			status.color = "black";
			break;
		case $.erp.resultCheckPending:
			status.msg = $.erp.resultCheckPendingMsg;
			status.color = "gray";
			break;
		case $.erp.resultCheckOk:
			status.msg = $.erp.resultCheckOkMsg;
			status.color = "green";
			break;
		case $.erp.resultCheckChange:
			status.msg = $.erp.resultCheckChangeMsg;
			status.color = "blue";
			break;
		default:
			break;
		}
		return '<span style="color:'+status.color+';">' + status.msg + '</span>';
	};
	
	$.erp.getSettleStatus = function(value){
		var status = {msg:'',color:'black'};
		switch(value){
		case $.erp.settleStatusNone:
			status.msg = $.erp.settleStatusNoneMsg;
			status.color = "black";
			break;
		case $.erp.settleStatusPart:
			status.msg = $.erp.settleStatusPartMsg;
			status.color = "red";
			break;
		case $.erp.settleStatusFull:
			status.msg = $.erp.settleStatusFullMsg;
			status.color = "green";
			break;
		default:
			break;
		}
		return '<span style="color:'+status.color+';">' + status.msg + '</span>';
	}
	
	$.erp.checkResultStatus = function(row){
		var status = false;
		
		if(!row){
			return false;
		}
		
		if($.erp.isAdmin()){
			return false;
		}
		
		var result = row.result;
		if(result == $.erp.resultCheckPending || result == $.erp.resultCheckOk 
				|| result == $.erp.resultCheckChange){
			status = true;
		}
		
		return status;
	};
	
	
	$.fn.erpResGrid = function(options, resId, editable,title){
		  var _url = '';
		  var $this = $(this);
		  if(resId){
			  _url = 'auxiliaryResAssign/findByTypeId.action';
		  }
		   var opts = $.extend({
				panelWidth: 280,
				url: _url,
				queryParams: {resId:resId},
				idField: 'messageId',
				textField: 'name',
				editable: false,
				columns: [[
					{field: 'code', title: '代码', width: 100},
					{field: 'name', title: '名称', width: 150}
				]]
		   }, options);
		   
		   if(editable){
			   $.extend(opts, {
					buttonText:'+',
					onClickButton: function(){
						var $resWindow = $('<div/>').dialog({
							title: (title?title:'')+'维护',
							iconCls: 'icon-edit',
							width: 500,
							height: 500,
							modal: true,
							href:'view/auxiliaryRes/auxiliaryResMessage.jsp',
							onLoad: function(){
								$resWindow.find('#tempResId').val(resId);
								setTimeout(function(){
									$resWindow.find('#resdg').datagrid('load',{id:resId});
								},100);
							},
							buttons:[{
								text: '关闭',
								iconCls: 'icon-cancel',
								width: 80,
								handler: function(){
									$resWindow.dialog('destroy');
								}
							}]
						});
					},
					onShowPanel: function(){
						setTimeout(function(){
							var g = $this.combogrid('grid');
							g.datagrid('load',{resId:resId});
						},100);
					}
				});
		   }
		   	
		return this.combogrid(opts);
	};
	
	$.fn.erpGrid = function(options){
		   var opts = $.extend({
			    width: 176,
				panelWidth: 280,
				url: '',
				idField: '',
				textField: 'name',
				editable: false,
				columns: [[
					{field: 'number', title: '代码', width: 100},
					{field: 'name', title: '名称', width: 150}
				]]
		   }, options);
		
		return this.combogrid(opts);
	};
	
	$.fn.erpSimpleTree = function(options){
		var opts = $.extend({
			url: '',
			width: 176,
			required: true,
			idFiled:'id',
			textFiled:'name',
			parentField:'pid'
		}, options);
		
		return this.combotree(opts);
	};
	
	$.fn.erpDept = function(){
		var dtd = $.Deferred();
		var $this = this;
		$this.erpSimpleTree({
			url: 'department/findForTree.action',
			onLoadSuccess: function(){
				dtd.resolve();
			}
		});
		return dtd.promise();
	};
	
	$.fn.erpEmployee = function(){
		return this.each(function(){		
			$(this).erpGrid({
				url: 'employee/find.action',
				idField: 'employeeId'
			});
		});
	};
	
	$.fn.erpCustomer = function(jq){
		return this.each(function(){
			$(this).erpGrid({
				url: 'customer/find.action',
				idField: 'customerId',
				editable: true,
				columns: [[
				           {field:'number', title:'代码',width:80},
				           {field:'name',title:'名称',width:80},
				           {field:'prefixCode',title:'前缀代码',width:80},
				           {field:'region',title:'国家',width:90}
				]],
				onSelect: function(index, row){
					if(jq){
						var date = new Date();
						var y = date.getFullYear();
						var m = date.getMonth() + 1;
						var d = date.getDate();
						y = y.toString().substring(2);
						m = m.toString().length < 2 ? '0'+m:m;
						d = d.toString().length < 2 ? '0'+d:d;
						var suffix = y + m + d;
						jq.textbox('setValue', row.prefixCode + suffix);
					}
				}
			});
		});
	}
	
	$.fn.erpCurrency = function(jq){
		return this.each(function(){
			$(this).erpGrid({
				url: 'currency/find.action',
				idField: 'currencyId',
				columns: [[
					         {field: 'number', title: '代码', width: 80},
					         {field: 'name', title: '名称', width: 80},
					         {field: 'exchangeRate', title: '汇率', width: 90}
				]],
				onSelect: function(index, row){
					if(jq){
						jq.textbox('setValue', row.exchangeRate);
					}
				}
			})
		});
	};
	
	$.fn.erpUsers = function(readonly){
		return this.each(function(){
			$(this).erpGrid({
				url: 'user/findUsers.action',
				idField: 'userId',
				readonly: readonly?true:false
			});
		});
	}
	
	$.fn.erpAccount = function(){
		var $this = this
		var state = $.data(this[0],'combogrid');
		return $.erp.ajax("getAccount.action",{},function(data){
			if(state){
				$this.combogrid('setValue', data.userId);
			}
		});
	};
	
	$.fn.erpNumber = function(method){
		var $this = this;
		$.erp.ajax("numberSet/" + method +".action", {}, function(rsp){
			if(rsp.status){
				$this.textbox('setValue', rsp.message);
				$this.textbox('readonly',true);
			}
		});
	};
	
	$.fn.erpCurrDate = function(readonly){
		return this.each(function(){
		    $(this).datebox({
				width: 176,
				required: true,
				readonly: readonly?true:false,
			}).datebox("setValue", new Date().toString());
		});
	}
	
	$.erp.materialCol = function(target){
		$.erp.materialCol.prototype.target = target;
	};
	$.erp.materialCol.prototype.target="";
	$.erp.materialCol.prototype.select = function(row){
		var $dg = $.erp.materialCol.prototype.target;
		
		var _row = $dg.datagrid('getSelected');
		var _idx = $dg.datagrid('getRowIndex', _row);
		
		var edtNumber = $dg.datagrid('getEditor',{
			index: _idx,
			field: 'number'
		});
		
		var edtItemId = $dg.datagrid('getEditor',{
			index: _idx,
			field: 'itemId'
		});
		
		var edtName = $dg.datagrid('getEditor',{
			index: _idx,
			field: 'itemName'
		});
		
		var edtModel = $dg.datagrid('getEditor',{
			index: _idx,
			field: 'itemModel'
		});
		
		var edtUnit = $dg.datagrid('getEditor',{
			index: _idx,
			field: 'itemUnit'
		});
		
		var edtAttr = $dg.datagrid('getEditor',{
			index: _idx,
			field: 'itemAttr'
		});
		
		var edtContent = $dg.datagrid('getEditor',{
			index: _idx,
			field: 'attrContent'
		});
		
		$(edtNumber.target).textbox('setValue', row.number);
		$(edtItemId.target).val(row.itemId);
		$(edtName.target).textbox('setText', row.name);
		$(edtModel.target).textbox('setText', row.model);
		$(edtUnit.target).textbox('setText', row.unitName);
		$(edtAttr.target).textbox('setValue', row.materialAttr);

		$(edtContent.target).combotree('loadData',$.erp.materialCol.prototype.getTreeData());
	};
	$.erp.materialCol.prototype.getItemAttr = function(){
		var $dg = $.erp.materialCol.prototype.target;
		var itemAttr = '';
		if($dg.success){
			var _row = $dg.datagrid('getSelected');
			if(_row){
				var _idx = $dg.datagrid('getRowIndex', _row);
				var edtAttr = $dg.datagrid('getEditor',{
					index: _idx,
					field: 'itemAttr'
				});
				itemAttr = $(edtAttr.target).textbox('getValue');
			}
		}
		return itemAttr;
	};
	$.erp.materialCol.prototype.getTreeData = function(){
		var treeData = [];
		var itemAttr = $.erp.materialCol.prototype.getItemAttr();
		if(itemAttr){
			var url = 'materialAttr/findByIdForTree.action';
			$.erp.ajax(url, {id: itemAttr}, function(result){
				treeData = result;
			},true);
		}
		return treeData;
	};
	
	
	$.extend($.fn.datagrid.defaults.editors,{
		combotree_material:{
			init: function(container, options){
				var editor = $('<select multiple />').appendTo(container);
				editor.combotree(options);
				return editor;
			},
			getValue: function(target){
				var t = $(target).combotree('tree');
				var n = t.tree('getChecked');
				var arr = new Array();
				if(n){
					for(var i = 0; i < n.length; i++){
						var o = new Object();
						o.id = n[i].id;
						o.text = n[i].text;
						arr.push(o);
					}
				}
				return arr;
			},
			setValue: function(target, value){
				$(target).combotree('loadData',$.erp.materialCol.prototype.getTreeData());
				if(value != undefined){
					if(typeof value == 'string'){
						value = eval(value);
					}
					var arrId = new Array();
					for(var i = 0; i < value.length; i++){
						arrId.push(value[i].id);
					}
					$(target).combotree('setValues', arrId);
				}
			},
			resize: function(target, width){
				$(target).combotree('resize', width);
			},
			destroy: function(target){
				$(target).combotree('destroy');
			}
		}
	});
	
	
	$.erp.materialCol.prototype.partCol = function(){
		
		var materialEditor = {
				type: 'textbox',
				options:{
					required: true,
					editable: false,
					buttonIcon: 'icon-search',
					onClickButton: function(){
						
						function closeWindow(){
							setTimeout(function(){
								msDlg.dialog('destroy');
							},100);
						}
						
						msDlg = $('<div/>').dialog({
							title: '查找产品',
							width: 650,
							height: 500,
							href: 'view/material/materialSearchDlg.jsp',
							modal: true,
							buttons:[{
								text: '选择',
								iconCls: 'icon-ok',
								width: 80,
								handler: function(){
									var dg = msDlg.find('#materialSearchDg');
									var row = dg.datagrid('getSelected');
									if(row){
										select(row);
										closeWindow();
									}else{
										$.erp.noSelectErr();
									}
								}
							},{
								text: '取消',
								iconCls: 'icon-cancel',
								width: 80,
								handler: function(){
									closeWindow();
								}
							}],
							onClose: function(){
								closeWindow();
							}
						});
					}
			}};
		
		return  [{field: 'number', title: '产品代码', width: 90, editor: materialEditor},
	        				{field: 'itemId', editor: 'text', hidden: true},
	        				{field: 'itemName', title: '产品名称', width: 100, editor: {type: 'textbox', options:{editable:false}}},
	        				{field: 'itemModel', title: '规格型号', width: 150, editor: {type: 'textbox', options:{editable:false}}},
	        				{field: 'itemUnit', title: '单位', width: 50, editor: {type: 'textbox', options:{editable: false}}},
	        				{field: 'itemAttr', title: '产品属性', hidden: true, width: 80, editor: {type: 'textbox', options:{editable: false}}},
	        				{field: 'attrContent', title: '辅助属性', width: 200, 
	        					editor:{
	        						type:'combotree_material',
	        						options:{
	        							panelWidth:200,
	        							checkbox: true,
	        							editable: false,
	        							multiple: true
	        						}
	        					},
	        					formatter: function(value, row, index){
	        						var text = '';
	        						if(value){
	        							if(typeof value == 'string'){
		        							value = eval(value);
		        						}
	        							for(var i = 0; i < value.length; i++){
	        								text += value[i].text + ',';
	        							}
	        						}
	        						return text;
	        					}
	        				}
	             ];
	};
	
	$.erp.materialCol.prototype.mergeCol = function(fieldArr){
		return $.merge(this.partCol(), fieldArr);
	};
	
	/**
	 * 只处理form数据
	 */
	$.erp.simpleEntry = function(){};
	
	$.erp.simpleEntry.prototype = {
		submit: function (form){
			parent.$.messager.progress({
				title: $.erp.hint,
				msg: $.erp.processing
			});

			var isValid = form.form('validate')
			if(!isValid){
				parent.$.messager.progress('close');
			}
			return isValid;
		},
		success: function (result, process){
			result = $.parseJSON(result);
			
			parent.$.messager.progress('close');
			$.erp.submitSuccess(result.title, result.message);
			
			if(result.status){
				if(process){
					process();
				}
			}
		}
	};
	
	
	/**
	 * 可以同时处理form数据和多个数据表格
	 */
	$.erp.entry = function(target,stat){
		this.target = target;
		this.stat = stat;
	};
	
	$.erp.entry.prototype = {
	    resultSet: [],
		editIndex: undefined,
		endEditing: function(){
			if(this.editIndex == undefined){return true}
			if(this.target.datagrid('validateRow', this.editIndex)){
				this.target.datagrid('endEdit', this.editIndex);
				this.editIndex = undefined;
				this.target.datagrid('statistics');
				if(this.stat){
					this.stat();
				}
				return true;
			}else{
				return false;
			}
		},
		
		onClickRow: function(index){
			if(this.editIndex != index){
				if(this.endEditing()){
					this.target.datagrid('selectRow', index)
					   .datagrid('beginEdit', index);
					this.editIndex = index;
				}else{
					this.target.datagrid('selectRow', this.editIndex);
				}
			}
		},
		
		appendRow: function(){
			if(this.endEditing()){
				this.target.datagrid('appendRow',{});
				this.editIndex = this.target.datagrid('getRows').length - 1;
				this.target.datagrid('selectRow', this.editIndex)
				   .datagrid('beginEdit', this.editIndex);
			}
		},
		
		removeRow: function(){
			if(this.editIndex == undefined){return}
			this.target.datagrid('cancelEdit', this.editIndex)
			   .datagrid('deleteRow', this.editIndex);
			this.editIndex = undefined;
			this.target.datagrid('statistics');
			if(this.stat){
				this.stat();
			}
		},
		
		acceptRows: function(){
			this.target.datagrid('acceptChanges');
		},
		
		rejectRows: function(){
			this.target.datagrid('rejectChanges');
			this.editIndex = undefined;
			this.target.datagrid('statistics');
			if(this.stat){
				this.stat();
			}
		},
		
		saveRows: function(){
			var effectRow = new Object();
			if(this.endEditing()){
				if(this.target.datagrid('getChanges').length){
					var inserted = this.target.datagrid('getChanges', 'inserted');
					var deleted = this.target.datagrid('getChanges', 'deleted');
					var updated = this.target.datagrid('getChanges', 'updated');
					if(inserted.length){
						effectRow.inserted = JSON.stringify(inserted);
					}
					if(deleted.length){
						effectRow.deleted = JSON.stringify(deleted);
					}
					if(updated.length){
						effectRow.updated = JSON.stringify(updated);
					}
				}
			}
			this.resultSet.push($.extend({}, {target:this.target},{effect:effectRow}));
		},
		
		chkRows: function(target){
			var rows = target.datagrid('getRows');
			if(rows && rows.length > 0){
				for(var i = 0; i < rows.length; i++){
					if(!target.datagrid('validateRow', i)){
						return false;
					}
				}
			}else{
				return false;
			}
			return true;
		},
		
		submit: function (entryRows, form, fun){
			parent.$.messager.progress({
				title: $.erp.hint,
				msg: $.erp.processing
			});
			
			var invalidRows = false;
			var checkRows = this.chkRows;
			$.each(this.resultSet,function(index,obj){
				if(!checkRows(obj.target)){
					invalidRows = true;
				}
			});
			
			if (this.resultSet.length == 1){
				var tempObj = this.resultSet[0].effect;
				if(tempObj.inserted){
					entryRows.inserted = tempObj.inserted;
				}
				if(tempObj.updated){
					entryRows.updated = tempObj.updated;
				}
				if(tempObj.deleted){
					entryRows.deleted = tempObj.deleted;
				}
			}else{
				entryRows = fun(this.resultSet);
			}
			
			$.erp.entry.prototype.resultSet = [];
			
			var isValid = $(form).form('validate') && !invalidRows;
			if(!isValid){
				parent.$.messager.progress('close');
			}
			return isValid;
		},
		
		success: function (result, process, status){
			result = $.parseJSON(result);
			if(status){
				  parent.$.messager.progress('close');
				  $.erp.submitSuccess(result.title, result.message);
			}
			if(result.status){
				process();
			}
		}		
	};
	
	$.erp.gridRowTotal = function (grid,index,valFields,totalField,symbol,fun){
		if(symbol != '*' && symbol != '+'){
			return;
		}
		
		var num = 0;
		var fields = [];
		for(var i in valFields){
			fields[num] = grid.datagrid('getEditor',{index:index,field:valFields[i]}).target;
			num++;
		}
		var total = grid.datagrid('getEditor', {index:index,field:totalField}).target;
		
		var totalFun = function(){
			var value;
			if(symbol == '*'){
				value = 1;
			}else if(symbol == '+'){
				value = 0;
			}
			for(var i in fields){
				var v = fields[i].numberbox('getValue');
				value = eval(value + symbol + (v?v:0));
			}
			total.numberbox('setValue', value);
			if(fun){
				fun(value);
			}
		};
		
		for(var i in fields){
			fields[i].next().children('input.textbox-text').blur(function(){
				totalFun();
			});
		}
	};
	
	$.erp.taskSubmit = function(procInstId, success){
		var url = "task/submitWithFormByProcInstId";
		var params = {procInstId: procInstId};
		$.erp.ajax(url, params, function(data){
			if(data.status){
				if(success){
					success();
				}
			}
			$.erp.submitSuccess(data.title, "提交-" + data.message);
		});
	};
	
	$.erp.excludeAttrForFormFill = function(row){
		if('interId' in row){delete row.interId}
		if('creater' in row){delete row.creater;}
		if('created' in row){delete row.created}
		if('status' in row){delete row.status}
		if('billNo' in row){delete row.billNo;} 
		if('date' in row){delete row.date;}
		if('sourceType' in row){delete row.sourceType;}
		if('sourceBillNo' in row){delete row.sourceBillNo;}
		if('checker' in row){delete row.checker;}
		if('checkDate' in row){delete row.checkDate;}
		if('managerId' in row){delete row.managerId;}
		if('departmentId' in row){delete row.departmentId;}
	};
	
	$.erp.showBusinessDlg = function(title,iconCls,viewPath,operType,numMethod,row,
			mainGrid,processDefKey,entityName,dlgWidth, dlgHeight){
		var btnText = '保存';
		var btnIcon = 'icon-save';
		var btnDisabled = false;
		var result = 0;
		var isChangeApply = false;
		
		if(!$.erp.isAdmin()){
			if(row && row.result){
				result = row.result;
				if(result == 1 || result == 3){
				   btnDisabled = true;
				}
				if(result == 2){
					isChangeApply = true;
					btnText = '申请修改';
					btnIcon = 'icon-audit';
				}
			}
		}
		
		var $dg = null;
		var dgOrgHeight = 0;
		var mainTbHeight = 0;
		
		var $parentWindow = parent.$.modalDialog({
			title: title,
			iconCls: iconCls,
			width: dlgWidth||800,
			height: dlgHeight||600,
			maximizable: true,
			href: viewPath,
			onLoad:function(){
				
				if(parent.init){
					parent.init();
				}
				
				var $form = $parentWindow.find('#form');
				
				var deptIdDefer = null;
				var $deptId = $parentWindow.find("#departmentId");
				if($deptId.length > 0){
					deptIdDefer = $deptId.erpDept();
				}
				
				var $mainTb = $parentWindow.find("table.simple");
				if($mainTb.length > 0){
					mainTbHeight = $mainTb.height();
				}
				
				$dg = $parentWindow.find("table[id^=dg]");
				if($dg.length <= 0){
					$dg = null;
				}else{
					dgOrgHeight = $dg.datagrid('options').height;
				}
					
				if(operType == 'add'){
					//fill auto number
					var $billNo = $parentWindow.find("#billNo");
					if(numMethod && $billNo.length > 0){
						$billNo.erpNumber(numMethod);
					}
					//fill base info (employee et.)
					var createrDefer = null;
					var $creater = $parentWindow.find('#creater');
					if($creater.length > 0){
						createrDefer = $creater.erpAccount();
					}
					
					if(createrDefer && deptIdDefer){
						$.when(createrDefer,deptIdDefer).done(function(){
							var url = 'user/getUserById.action';
							var v = $creater.combogrid('getValue');
							var params = {userId:v};
							$.when($.ajax({url:url,data:params})).done(function(data){
								$form.form('load', data);
							});
						});
					}
				}
				
				if(operType == 'update' || operType == 'show'){
					if(row){
						//load form data
						if(deptIdDefer){
							$.when(deptIdDefer).done(function(){
								$form.form('load', row);
							});
						}else{
							$form.form('load', row);
						}
						//load entry data
						if($dg){
							$dg.datagrid('reload',{interId:row.interId});
						}
					}
				}
			},
			onBeforeLoad: function(param){
				//parent.$.messager.progress(); 
			},
			onMaximize: function(){
				if($dg){
					var newHeight = $(window.top).height() - mainTbHeight - 100;
					$dg.datagrid('resize',{width:'auto',height:newHeight});
				}
			},
			onRestore: function(){
				if($dg){
					$dg.datagrid('resize',{width:'auto',height:dgOrgHeight});
				}
			},
			buttons:[{
				text: btnText,
				iconCls: btnIcon,
				width: 80,
				disabled: btnDisabled,
				handler: function(){
					if(isChangeApply){
						var $applyWindow = parent.$('<div/>').dialog({
							title: '请说明修改原因:',
							iconCls: 'icon-audit',
							width: 300,
							height: 250,
							modal: true,
							content: '<textarea id="reason" style="width:279px;height:168px;"></textarea>',
							buttons:[{
								text: '提交申请',
								iconCls: 'icon-audit',
								width: 80,
								handler: function(){
									var reason = $applyWindow.find('#reason').val();
									if(reason){
										var params = {processDefinitionKey: processDefKey};
									    var auditModel = {};
									    auditModel.processInstanceId = row.procInstId;
									    auditModel.taskBusinessType = 'change';
									    auditModel.path = viewPath;
									    auditModel.businessKey = row.interId;
									    auditModel.businessClass = 'com.cloud.erp.entities.table.' + entityName;
									    auditModel.number = row.billNo;
									    auditModel.creater = row.createrName;
									    auditModel.result = '';
									    auditModel.extra = reason;
									    var url = "task/submitChangeApply"
									    $.extend(params, auditModel);
									    $.erp.ajax(url, params, function(rsp){
									    	if(rsp.status){
									    		mainGrid.datagrid('reload');
									    	}
									    	$.erp.submitSuccess(rsp.title, '提交申请-' + rsp.message);	
									    });
									    //close subwindow
									    $applyWindow.dialog('destroy');
									    //close parent window
									    parent.$.modalDialog.handler.dialog('destroy');
										parent.$.modalDialog.handler = undefined;
									}
								}
							},{
								text: '取消',
								iconCls: 'icon-cancel',
								width: 80,
								handler: function(){
									$applyWindow.dialog('destroy');
								}
							}]
						});
					}else{
						parent.$.modalDialog.openner = mainGrid;
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				}
			},{
				text: '取消',
				iconCls: 'icon-cancel',
				width: 80,
				handler: function(){
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
			}]
		});
	
	};
	
	$.erp.commitBusinessList = function(grid){
		var row = grid.datagrid('getSelected');
		if(row){
			if($.erp.checkResultStatus(row)){
				return false;
			}
			parent.$.messager.confirm($.erp.hint,'确定提交单据 [ ' + row.billNo + ' ] ?',function(r){
				if(r){
					$.erp.taskSubmit(row.procInstId, function(){
						grid.datagrid('reload');
					});
				}else{
					return false;
				}
			});
		}else{
			$.erp.noSelectErr();
			return false;
		}
		return true;
	};
	
	$.erp.checkPending = function(grid){
		grid.datagrid('reload', $.erp.searcher("result", $.erp.resultCheckPending, 'int'));
	};
	
	$.erp.fileDownload = function(src, params){
		var getParams = '';
		var append = false;
		for(var field in params){
			getParams = getParams + (append?'&':'') + (append?'':'?') + field + "=" + params[field];
			append = true;
		}
		src = src + getParams;
		$('#_file_download_abc04r5gewg').remove();
		$('body').append('<div id="_file_download_abc04r5gewg" style="display:none;"><iframe src="'
				+src+'" ></iframe></div>');
	};
	
	$.erp.exportExcel = function(beanName,reportSchemaName,options){
		var src = 'file/exportExcel.action';
		var params = {bean:beanName, reportSchema:reportSchemaName};
		if(options && options.queryParams.searchName){
			$.extend(params,{searchName:options.queryParams.searchName,
				searchValue:options.queryParams.searchValue});
		}
		if(options && options.sortName){
			$.extend(params,{sort:options.sortName,order:options.sortOrder});
		}
		$.erp.fileDownload(src, params);
	};
	
	
})(jQuery);

