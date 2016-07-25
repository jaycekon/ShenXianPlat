<%-- 
    Document   : login
    Created on : 2015-9-10, 17:15:34
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>神鲜-欢迎登陆</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/components/css/font-awesome.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/components/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/components/css/regular.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/page/login/login.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/app/components/js/jquery.min.js"></script>
	</head>

	<body>
		<div class="top"></div>
		<div class="center">
			<div class="container">
				<div class="adver">
					<img src="<%=request.getContextPath()%>/app/page/login/login.png" width="440px" height="225px" />
				</div>
				<div class="loginBox">
					<form action="<%=request.getContextPath()%>/login" method="POST">
						<h3>用户登陆
                                                    <span><a href="<%=request.getContextPath()%>/addUser"><i class="fa fa-chevron-circle-right"></i> 立即注册</a></span>
						</h3>
						<div class="input-group">
							<span class="input-group-addon lo-color"><i class="fa fa-user fa-fw"></i></span>
							<input class="form-control" type="text" placeholder="邮箱/用户名/已验证手机" name="phone">
						</div>
						<div class="input-group">
							<span class="input-group-addon lo-color"><i class="fa fa-lock fa-fw"></i></span>
							<input class="form-control" type="password" placeholder="密码" name="password">
						</div>
						<div class="lo-agree">
							<label>
								<input type="checkbox" checked="checked">记住密码
							</label>
							<a href="#">忘记密码？</a>
						</div>
						<div>
							<input type="submit" class="btn btn-success" value="登陆" />
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="b-bar">
			<div>
				<span><a href="#">免费条款 |</a></span>
				<span><a href="#">诚聘英才 |</a></span>
				<span><a href="#">咨询热点 |</a></span>
				<span><a href="#">商家入驻 |</a></span>
				<span><a href="#">网站地图 |</a></span>
				<span><a href="#">友情链接 |</a></span>
				<span><a href="#">农商联盟</a></span>
			</div>
		</div>
		<p style="text-align: center;color: gray;">经营许可证编号：广ICP证222222号</p>

		<script type="text/javascript" src="<%=request.getContextPath()%>/app/page/login/login.js"></script>
	</body>

</html>