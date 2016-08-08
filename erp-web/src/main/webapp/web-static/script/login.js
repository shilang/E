/**
 * login javascript
 */
$(document).ready(function(){
	getCookie();
	
	$('#Kaptcha').click(
			function(){
				$(this).hide().attr('src','Kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();
	});
	
	$('#btn_login').click(function(){
		//login();
	});
});

//login
function login(){
	setCookie();
	//submit action path
	var action = $('form').attr('action');
	var formData = new Object();
	$(":input").each(function(){
		formData[this.name] = $("#" + this.name).val();
	});
	showMsg('正在登录...',true);
	$.ajax({
		cache: false,
		type: "POST",
		url: action,
		data: formData,
		error: function(){
			
		},
		success: function(data){
			var d = eval('(' + data +')');
			if(d.status){
				//loginSuccess();
				setTimeout("window.location.href='index.jsp'", 1000);
			}else{
				showMsg(d.message,false);
			}
		}
	});
}

//load information for successful
function loginSuccess(){
	$("#login").animate({
		opacity : 1,
		top : '40%'
	}, 200, function(){
		$('.userbox').show().animate({
			opacity : 1
		}, 500);
		$("#login").animate({
			opacity : 0,
			top : '60%'
		}, 500, function(){
			$(this),fadeOut(200, function(){
				$(".text_success").slideDown();
				$("#successLogin").animate({
					opacity : 1,
					height : "200px"
				}, 1000);
			});
		});
	});
}

//show error
function showMsg(str,status){
	var color;
	if(status){
		color = 'success';
	}else{
		color = 'error';
	}
	$('#alertMessage').attr('class','').addClass(color).show().html(str);
}

//set cookie
function setCookie(){
	if($('#remember').val() == "true"){
		$("input[iscookie='true']").each(function(){
			$.cookie(this.name, $('#' + this.name).val(), "/", 24);
			$.cookie("ISREMEMBER", "true", "/", 24);
		});
	}else{
		$("input[iscookie='true']").each(function(){
			$.cookie(this.name, null);
			$.cookie("ISREMEMBER", null);
		});
	}
}

//get cookie
function getCookie(){
	var isremember = $.cookie("ISREMEMBER");
	var $remember = $("#remember");
	if(isremember != null){
		$("input[iscookie='true']").each(function(){
			$('#' + this.name).val($.cookie(this.name));
		});
		$remember.attr("checked", true);
	}else{
		$remember.attr("checked", false);
	}
}