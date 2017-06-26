<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
	#menuItem a {
		height: 30px;
	}
	
	#menuItem a.l-btn span.l-btn-left {
		margin: 0 0 0 26px;
	}
	
	#menuItem a.l-btn span span.l-btn-text {
		text-align: left;
		line-height: 30px;
		width: 126px;
		padding: 0 0 0 15px;
	}
	
	#menuItem a.l-btn span span.l-btn-icon {
		background-position: left center;
		left: 15px;
	}
	
	#menuItem span:focus {
		outline: none;
	}
</style>

<script src="web-static/script/sockjs.js"></script>
<script src="web-static/script/stomp.js"></script>

<script type="text/javascript" charset="utf-8">
	var stompClient = null;
	var centerTabs;
	var tabsMenu;
	$(function(){
		tabsMenu = $('tabsMenu').menu({
			onClick: function(item){
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('type');
				
				if(type == 'refresh'){
					refreshTab(curTabTitle);
					return;
				}
				
				if(type == 'close'){
					var t = centerTabs.tabs('getTab', curTabTitle);
					if(t.panel('options').closable){
						centerTabs.tabs('close', curTabTitle);
					}
					return;
				}
				
				var allTabs = centerTabs.tabs('tabs');
				var closeTabsTitle = [];
				
				$.each(allTabs, function(){
					var opt = $(this).panel('options');
					if(opt.closable && opt.title != curTabTitle && type == 'closeOther'){
						closeTabsTitle.push(opt.title);
					}else if(opt.closable && type == 'closeAll'){
						closeTabsTitle.push(opt.title);
					}
				});
				
				for(var i = 0; i , closeTabsTitle.length; i++){
					centerTabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});
		
		centerTabs = $('#centerTabs').tabs({
			tools : [{
				width: 50,
				iconCls : 'icon-reload',
				handler : function() {
					var href = $('#centerTabs').tabs('getSelected').panel('options').href;
					if (href) {/*说明tab是以href方式引入的目标页面*/
						var index = $('#centerTabs').tabs('getTabIndex', $('#centerTabs').tabs('getSelected'));
						$('#centerTabs').tabs('getTab', index).panel('refresh');
					} else {   /*说明tab是以content方式引入的目标页面*/
						var panel = $('#centerTabs').tabs('getSelected').panel('panel');
						var frame = panel.find('iframe');
						try {
							if (frame.length > 0) {
								for ( var i = 0; i < frame.length; i++) {
									frame[i].contentWindow.document.write('');
									frame[i].contentWindow.close();
									frame[i].src = frame[i].src;
								}
								if ($.browser.msie) {
									CollectGarbage();
								}
							}
						} catch (e) {
						}
					}
				}
			}, {
				width: 50,
				iconCls : 'icon-cancel',
				handler : function() {
					var index = $('#centerTabs').tabs('getTabIndex', $('#centerTabs').tabs('getSelected'));
					var tab = $('#centerTabs').tabs('getTab', index);
					if (tab.panel('options').closable) {
						$('#centerTabs').tabs('close', index);
					} else {
						//$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭', 'error');
					}
     			}
    		},{
    			width: 50,
				iconCls : 'icon-home',
				handler : function(){
					/*
					$('#theme').menu({
						onClick: function(item){
							var cookieColor1 = jqueryUtil.cookie.get('cookieColor');
							if(cookieColor1 != item.id){
								jqueryUtil.cookie.set("cookieColor", item.id, 30);
								jqueryUtil.chgSkin(item.id, cookieColor1);
							}
						}
					});
					
					$('#theme').menu('show', {
						left : '91%',
						top : 97
					}) */
					
					if(centerTabs.tabs('exists', '首页')){
						centerTabs.tabs('select', '首页');
					}
					
				}
			}],
			fit : true,
			border : false,
			onContextMenu : function(e, title){
				e.preventDeault();
				tabsMenu.menu('show',{
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			}
		});
	});
	
	var d = $("#dataTree").tree({
		animate: true,
		checkbox: false,
		onlyLeafCheck: true,
		url: 'data/tree_data2.json',
		onClick: function(node){
			addTab(node); //alert node text property when clicked
		}
	});
	
	function addTab(node){
		var nodes = node.split("||");
		if(centerTabs.tabs('exists', nodes[0])){
			centerTabs.tabs('select', nodes[0]);
		}else{
			/*if (node.attributes.url && node.attributes.url.length > 0) {
			if (node.attributes.url.indexOf('!druid.action') == -1) {//数据源监控页面不需要开启等待提示
				$.messager.progress({
					text : '页面加载中....',
					interval : 100
				});
				window.setTimeout(function() {
					try {
						$.messager.progress('close');
					} catch (e) {
					}
				}, 5000);
			}
			centerTabs.tabs('add', {
				title : node.text,
				closable : true,
				iconCls : node.iconCls,
				content : '<iframe src="' + node.attributes.url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				tools : [ {
					iconCls : 'icon-mini-refresh',
					handler : function() {
						refreshTab(node.text);
					}
				} ]
			});
		} else {}*/
		centerTabs.tabs('add',{
			title : nodes[0],
			closable: true,
			//border: false,
			iconCls: nodes[1],
			//href: 'error/error.jsp',
			content: "<iframe src=" + nodes[2] + " frameborder=\"0\" style=\"border:0;width:100%;height:99.4%;\"></iframe>",
			tools: [{
				iconCls: 'icon-mini-refresh',
				handler: function(){
					refreshTab(nodes[0]);
				}
			}]
		});
		}
	}
	
	function refreshTab(title){
		var tab = centerTabs.tabs('getTab', title);
		centerTabs.tabs('update',{
			tab: tab,
			options: tab.panel('options')
		});
	}
	
	function logout() {
		$.messager.confirm("提示", "确定退出吗？", function(r) {
			if (r) {
				$.ajax({url:'logout.action'});
				setTimeout(function(){
					disconnect();
					parent.location.reload();
				},100);
			}
		});
	}

	function modifyPasswddDlg() {
		
		function closeWindow(){
			parent.$.modalDialog.handler.dialog("destroy");
			parent.$.modalDialog.handler = undefined;
		}
		
		parent.$.modalDialog({
			title : '修改密码',
			iconCls : 'icon-edit',
			width : 600,
			height : 400,
			href : 'view/user/userEditDlg.jsp?modpwd=true',
			onLoad : function() {
				var f = parent.$.modalDialog.handler.find("#form");
				f.form('load', {
					name : "${sessionScope.shiroUser.account}"
				});
			},
			buttons : [ {
				text : '修改',
				iconCls : 'icon-save',
				width: 80,
				handler : function() {
					var f = parent.$.modalDialog.handler.find("#form");
					f.submit();
					/* setTimeout(function(){
						closeWindow();
					},100); */
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				width: 80,
				handler : function() {
					closeWindow();
				}
			} ]
		});
	}
	
	function connect(){
		var socket = new SockJS('/stomp');
		stompClient = Stomp.over(socket);
		stompClient.connect({},function(frame){
			stompClient.subscribe('/topic/${sessionScope.shiroUser.account}',function(message){
				var msgobj = $.parseJSON(message.body);	
				var msgbody = '名称: ' + msgobj.name 
				    msgbody	= msgbody + '<br/>内容: ' + msgobj.content 
					msgbody = msgbody + '<br/>发送者: ' + msgobj.sender;
				
				var title = '';
				if(msgobj.type == 'process'){
					title = '流程消息';
				}
				
				$.messager.show({
					title: title,
					msg: msgbody,
					width: 300,
					height: 150,
					timeout: 0
				});
			});
		});
	}
	
	function disconnect(){
		if(stompClient != null){
			stompClient.disconnect();
		}
	}
	
	connect();
	
</script>

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false"
		style="height: 40px; padding: 3px; background: url(web-static/images/bg-header.gif) repeat-x; 
		margin-bottom: 0px; overflow: hidden;">
		
		<div id="mainMenuItem" style="padding: 5px 0 0 30px; font-size: 12px;"></div>

		<div id="userMenu"
			style="position: absolute; right: 20px; bottom: 5px;">
			<div style="display: inline-block; margin-right: 10px;">
				<a href="javascript:void(0);" class="easyui-menubutton"
					style="color: #FFF;" menu="#layout_north_kzmbMenu"
					iconCls="icon-user"><strong>${sessionScope.shiroUser.account}</strong></a>
			</div>
			
			<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
				<div onclick="modifyPasswddDlg();">修改密码</div>
				<div class="menu-sep"></div>
				<div onclick="logout();">退出系统</div>
			</div>
		</div>
	</div>
	
	<div data-options="region:'west',split:false,title:''"
		style="width: 200px; background: #F2F2F2;">
		<div id="logo" style="margin: 12px 20px;">
			<img alt="欧比特科技有限公司" src="web-static/images/orbita-2.png">
		</div>
		<div id="menuItem"></div>
	</div>
	
	<div data-options="region:'center',plain:true,title:''"
		style="overflow: hidden;" >
		<div id="centerTabs">
			<div iconCls="icon-home" title="首页" border="false" style="overflow: hidden;">
				<iframe src="layout/portal.jsp" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>
			</div>
		</div>

		<div id="tabsMenu" style="width: 120px; display:none;">
			<div type="refresh">刷新</div>
			<div class="menu-sep"></div>
			<div type="close">关闭</div>
			<div type="closeOther">关闭其他</div>
			<div type="closeAll">关闭所有</div>
		</div>
	
		<div id="theme" class="easyui-menu" style="width:120px; display: none">
			<div id="default" data-options="iconCls:'icon-save'">default</div>
		
		</div>
	</div>
</div>