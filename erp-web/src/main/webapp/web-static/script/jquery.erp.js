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
		hint: '提示',
		submitErrMsg: '提交错误！',
		submitSuccessMsg: '提交成功！',
		selectErrMsg: '请选择一行记录！',
		deleteQueryMsg: '确定删除选中的记录吗？',
		processing: '数据处理中,请稍后...',
		showTimeout: 1000 * 2,	
	});
	
	//show message
	$.extend($.erp,{
		submitErr: function(){
			parent.$.messager.show({
				title: $.erp.hint,
				msg: $.erp.submitErrMsg,
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
    	searcher: function(name, value){
    		var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
            return eval('('+str+')');
    	}
	});
	
})(jQuery);

