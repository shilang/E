/**
 *  menu.js
 *  author: bollen
 */
	var $mainMenuContainer;
	var $itemMenuContainer;
	
	function loadMenuData(){
		return $.erp.ajax("findMenus.action");
	}
	
	function loadMenuItemData(id){
		return $.erp.ajax("findMenus.action", {menuId:id});
	}
	
	function createMenu(data){
		$.each(data,function(i,e){
			var $menu = $("<a id=\"" + e.id + "\" href=\"javascript:void(0);\" style=\"color:#fff;\" >" + e.name + "</a>");
			$menu.linkbutton({
				plain:true,
				iconCls: e.iconCls,
				onClick: function(){
					createMenuItem($menu);
				}
			});
			$menu.appendTo($mainMenuContainer);
		});
	}
	
	function createMenuItem(jqMenu){
		if(jqMenu[0] && jqMenu[0].id){
			$.when(loadMenuItemData(jqMenu[0].id)).done(function(data){
				$itemMenuContainer.empty();
				$.each(data,function(i,e){
					var effort=e.name+"||"+e.iconCls+"||"+e.url;
					var $menuItem = $("<a href=\"javascript:void(0);\" onclick=\"addTab('"+effort+"');\">"+e.name+"</a><br/>");
					$menuItem.linkbutton({
						plain: true,
						iconCls: e.iconCls
					});					
					$($menuItem).appendTo($itemMenuContainer);
				});
			});
		}
	}
		
	function initMenu(){
		$mainMenuContainer = $("#mainMenuItem");
		$itemMenuContainer = $("#menuItem");
		$.when(loadMenuData()).done(function(data){
			createMenu(data);
		}).done(function(){
			var $menu = $mainMenuContainer.find('a').first();
			createMenuItem($menu);
		});
	}