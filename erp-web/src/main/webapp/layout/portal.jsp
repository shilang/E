<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<jsp:include page="/euinc.jsp"></jsp:include>
<script type="text/javascript">
/* 	var $chkPending;
	var content;
	$(function(){
		content= '<div style="padding: 20px;">';
		$.erp.ajax('salesMgmt/chkPending.action',{searchName:'result',searchValue:1,searchType:'int'},function(data){
			if(data.AQ.length){
				showMsg('销售报价单',data.AQ.length,data.AQ);
			}
			if(data.XSHT.length){
				showMsg('销售合同单',data.XSHT.length,data.XSHT);
			}
			if(data.SEORD.length){
				showMsg('销售订单',data.SEORD.length,data.SEORD);
			}
			if(data.SEOUT.length){
				showMsg('发货通知单',data.SEOUT.length,data.SEOUT);
			}
			if(data.SEIN.length){
				showMsg('退货通知单',data.SEIN.length,data.SEIN);
			}
			if(data.XOUT.length){
				showMsg('销售出库单',data.XOUT.length,data.XOUT);
			}
			
			if(data.ZSEFP.length){
				showMsg('销售发票',data.ZSEFP.length,data.ZSEFP);
			}
			if(data.XSKD.length){
				showMsg('收款',data.XSKD.length,data.XSKD);
			}
		},true);
		content += '</div>';
		
		$chkPending = $("#chkPending");
		$chkPending.panel({
			title: '待审核记录',
			width:300,
			height: $(this).height() - 40,
			content: content,
			closable:true,                
			collapsible:true
		});
	}); */
	
/* 	function showMsg(title,cnt,arr){
		content +='<div><h3>'+title+'&nbsp;( ' + cnt + ' )</h3><ul>';
		$.each(arr,function(i,e){
			content += '<li><span style="margin-right:10px;">' + e.billNo + '</span><span style="margin-right:10px;">' + e.employeeName + '</span></li>';
		});
		content += '</ul></div>';
	} */
	
</script>

</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center', border:false" style="padding: 20px;">
			<div id="cc" class="easyui-calendar" style="width:250px;height:250px;"></div>
		</div>
	</div>
</body>
</html>