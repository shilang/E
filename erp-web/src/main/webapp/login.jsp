<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<% String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
	
<!DOCTYPE html>
<html>
<head>
<title></title>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="web-static/style/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="web-static/style/common.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="web-static/script/jquery-2.1.3.js"></script>
<script type="text/javascript" src="web-static/script/bootstrap.js"></script>
<script type="text/javascript" src="web-static/script/jquery.cookie.js"></script>  
<script type="text/javascript" src="web-static/script/login.js"></script>
</head>
<body class="background">

	<div id="alertMessage"></div>
	<div id="successLogin"></div>
	<!-- -
	<div class="text_success">
		<img alt="Please wait" src="images/loader_green.gif" /> <span>登录成功！请稍候...</span>
	</div>
 -->
	<div id="login" class="container">
		<div class="row"></div>
		
		<div class="row">
			<div class="col-md-offset-3 col-md-6">

				<div class="login-panel" style="display: block;">
					<div class="block">

					  <div class="title-login">
					  	<h2 class="form-signin-heading">请登录</h2>
					  	<div class="logo-login">
					  	<img alt="" src="web-static/images/orbita.png">
					  	</div>
					  	
					  </div>

						<div class="container-login">
						
							
							<form class="form-horizontal" role="form" action="loginAction!load.action" method="post">
																						
								<div class="form-group">
									<label for="username" class="col-md-3 control-label">用户名:</label>
									<div class="col-md-9">
										<input type="text" class="form-control input-width" id="username"
											placeholder="username" name="username" iscookie="true">
									</div>
								</div>
								
								<div class="form-group">
									<label for="password" class="col-md-3 control-label">密 &nbsp; &nbsp;码:</label>
									<div class="col-md-9">
										<input type="password" class="form-control input-width"
											id="password" placeholder="password" name="password">
									</div>
								</div>
								
								<div class="form-group">
									<label for="captcha" class="col-md-3 control-label">验证码:</label>
									<div class="col-md-9">
										<input type="text" class="form-control input-width-captcha" style="display:inline;" id="captcha" name="captcha">
										<img alt="" id="Kaptcha" src="Kaptcha.jpg" style="width:85px;height:35px;margin-top: -3px; margin-left: 10px;">
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-offset-3 col-md-9">
										<div class="checkbox">
											<label> <input type="checkbox" id="remember" name="rememberMe" > 记住我
											</label>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn btn-primary btn-lg btn-block" id="btn_login" onclick="return false;">登录</button>
									</div>
								</div>
							</form>
							
						</div>
						
					</div>
					
				</div>

			</div>
		</div>

		<div class="row"></div>

	</div>

</body>
</html>