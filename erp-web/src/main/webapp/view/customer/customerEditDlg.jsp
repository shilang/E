<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#form").form({
			url: 'customer/persist.action',
			onSubmit: function(){
				parent.$.messager.progress({
					title: $.erp.hint,
					msg: $.erp.processing
				});
				var isValid = $(this).form('validate');
				if(!isValid){
					parent.$.messager.progress("close");
				}
				return isValid;
			},
			success: function(result){
				parent.$.messager.progress("close");
				result = $.parseJSON(result);
				if(result.status){
					parent.$.modalDialog.handler.dialog('close');
					parent.$.modalDialog.openner.datagrid('reload');
				}
				$.erp.submitSuccess(result.title, result.message);
			}
		});
	});
	
	function init(){
		$("#typeId").erpResGrid({}, 12);
		$("#saleMode").erpResGrid({}, 7);
		$("#region").erpGrid({
			url: 'area/findAreas.action',
			idField:'areaId'
		});
	}
</script>
	
<div class="dlgcontent">
	<form id="form" method="post">
			<input id="customerId" name="customerId" type="hidden" />
			<input id="created" name="created" type="hidden" />
			<input id="creater" name="creater" type="hidden" />
			<input id="status" name="status" type="hidden" />
			<table class="simple">
				<tr>
					<th>代码</th>
					<td>
						<input id="number" name="number" class="easyui-textbox" data-options="required:true"/>
					</td>
					<th>名称</th>
					<td>
						<input id="name" name="name" class="easyui-textbox" data-options="required: true"/>
					</td>
				</tr>
				<tr>
					<th>全名</th>
					<td>
						<input id="fullName" name="fullName" class="easyui-textbox"/>
					</td>
					<th>前缀代码</th>
					<td>
						<input id="prefixCode" name="prefixCode" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<th>国别地区</th>
					<td>
						<input id="region" name="region" class="easyui-textbox" data-options="required:true"/>
					</td>
					<th>地址</th>
					<td>
						<input id="address" name="address" class="easyui-textbox" />
					</td>
				</tr>
				<tr>
					<th>电话</th>
					<td>
						<input id="phone" name="phone" class="easyui-textbox" />
					</td>
					<th>传真</th>
					<td>
						<input id="fax" name="fax" class="easyui-textbox" />
					</td>
				</tr>
				<tr>
					<th>邮箱</th>
					<td>
						<input id="email" name="email" class="easyui-textbox" data-options="required: true"/>
					</td>
					<th>主页</th>
					<td>
						<input id="homePage" name="homePage" class="easyui-textbox" />
					</td>
				</tr>
				<tr>
					<th>客户分类</th>
					<td>
						<input id="typeId" name="typeId" class="easyui-textbox" />
					</td>
					<th>销售方式</th>
					<td>
						<input id="saleMode" name="saleMode" class="easyui-textbox" />
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3">
						<textarea id="remark" name="remark"></textarea>
					</td>
				</tr>
			</table>
	</form>	
</div>