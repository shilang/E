<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<jsp:include page="bsinc.jsp"></jsp:include>
<script type="text/javascript" src="web-static/script/login.js"></script>
</head>
<body class="background">
	<div class="box">
		<div class="login-box">
			<form class="form" role="form" action="login.action" method="post">
					<div class="form-group">
						<div class="col-xs-12">
								<span id="alertMessage" style="color:red;">${loginMsg}</span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-12">
							<div class="input-group input-group-lg">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</span>
								<input type="text" class="form-control" id="username"
										placeholder="用户名" name="username">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-12">
							<div class="input-group input-group-lg">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</span>
								<input type="password" class="form-control"
										id="password" placeholder="密码" name="password">
							</div>
						</div>
					</div>
	<!-- 				<div class="form-group">
						<div class="col-xs-12">
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-check"></span>
								</span>
								<input type="text" id="captcha" name="captcha" class="form-control" placeholder="验证码">
								<span class="input-group-addon">
									<img alt="" class="Kaptcha" id="Kaptcha" src="Kaptcha.jpg">
								</span>
							</div>
						</div>								
					</div> -->
					<div class="form-group">
						<!-- <div class="col-xs-8">
							<div class="checkbox">
								<label> <input type="checkbox" id="remember" name="rememberMe" > 记住我
								</label>
							</div>
						</div>	 -->					
						<div class="col-xs-12">
							<button type="submit" class="btn btn-primary btn-lg btn-block" id="btn_login" >登录</button>
<!-- 							<button type="submit" class="btn btn-primary btn-md btn-block" id="btn_login" onclick="return false;">登录</button> -->
						</div>
					</div>
					 
					<div class="form-group">
						<div class="col-xs-12">
							<div style="color: silver;">请使用Firefox浏览器登录系统.<a href="http://download.firefox.com.cn/releases/stub/official/zh-CN/Firefox-latest.exe">下载Firefox</a></div>
							<div style="margin:25px 0 0 70px;"><a href="http://www.miitbeian.gov.cn" target="_blank">粤ICP备17005416号</a></div>
						</div>
					</div>
				
			</form>
		</div>
		<div class="footer">
			
		</div>
	</div>	
	
	 
</body>
</html>