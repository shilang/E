<%@ page language="java" pageEncoding="utf-8"%>
<script type="text/javascript">
	var $form;
	var entry;
	$(function() {
		
		funs($("#pid"));

		$("#iconCls").combobox({
			width:176,
			editable: false,
			data:$.iconData,
			formatter: function(v){
				return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
			}
		});
		
		$form = $("#form");
		$form.form({
			url :"function/persist.action",
			onSubmit : submit,
			success : success
		});
		
		entry = new $.erp.simpleEntry();
	});
	
	function submit(){
		return entry.submit($form);
	}
	
	function success(result){
		entry.success(result, process);
	}
	
	function process(){
		parent.$.modalDialog.openner.treegrid('reload');
	}
	
	function funs(jq){
		var $this = jq;
		$this.hide();
		var $tree = $('<input />');
		$tree.insertAfter($this);
		$tree.combotree({
			url: 'function/find.action',
			width: 176,
			idFiled: 'id',
			textFiled: 'name',
			parentField: 'pid',
			onLoadSuccess: function(node, data){
				var val = $this.val();
				if(val){
					if(val == 'null'){
						$tree.combotree('disable');
						$this.val("");
					}else{
						$tree.combotree('setValue', val);
					}
				}
		    },
		    onSelect: function(node){
		    	$this.val(node.id);
		    	$("#pname").val(node.text);
		    }
		});
	}
	
</script>

<div class="dlgcontent">
		<form id="form" method="post">
				<input name="permissionId" id="permissionId"  type="hidden"/>
				<input name="created" id="created"  type="hidden"/>
				<input name="creater" id="creater"  type="hidden"/>
				<input name="status" id="status"  type="hidden"/>
				<input name="state" id="state"  type="hidden"/>
				 <table>
					 <tr>
					    <th>程序名称</th>
						<td><input name="name" id="name" class="easyui-textbox" type="text" data-options="required:true"/></td>
						<th>父程序名</th>
						<td>
							<input name="pid"  id="pid" type="text"/>
							<input name="pname" id="pname"  type="hidden"/>
						</td>
					 </tr>
					 <tr>
					    <th>排序编码</th>
						<td><input name="sort" id="sort" type="text" class="easyui-textbox" required="required"/></td>
						<th>程序图标</th>
						<td><input id="iconCls" name="iconCls" type="text"/></td>
					 </tr>
					  <tr>
					    <th>程序路径</th>
						<td><input id="url" name="url" type="text" class="easyui-textbox" required="required"/></td>
						<th>程序编码</th>
						<td><input id="myid" name="myid" type="text" class="easyui-textbox" required="required"/></td>
					 </tr>
					 <tr>
						<th>程序类型</th>
						<td>
							<select id="type" class="easyui-combobox" name="type" data-options="required:true, editable:false">
								<option value="F">菜单</option>
								<option value="O">操作</option>
							</select>
						</td>
						<th>是否启用</th>
						<td>
							<select id="isused" class="easyui-combobox" name="isused" data-options="required:true, editable:false">
								<option value="Y">是</option>
								<option value="N">否</option>
							</select>
						</td>
					</tr>
					 <tr>
						<th>描述</th>
						<td colspan="3"><textarea id="description" name="description"></textarea></td>
					</tr>
				 </table>
		</form>
</div>
