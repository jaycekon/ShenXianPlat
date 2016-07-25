<%-- 
    Document   : addUser
    Created on : 2015-9-8, 20:44:30
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>神鲜-欢迎注册</title>
		<meta http-equiv="content-type" content="text/html; chaset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/components/css/font-awesome.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/components/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/components/css/regular.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/page/register/register.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/app/components/js/jquery.min.js"></script>
	</head>

	<body>
		<div class="re-top">
			<a href="#"><img src="<%=request.getContextPath()%>/app/page/register/logo222.png" height="90px" width="200px"></a>
			<h5>已有账号，直接<a href="<%=request.getContextPath()%>/login"> 登录 <span class="fa fa-angle-right"></span></a></h5>
		</div>
		<div class="content">
			<div class="adver">
				<a href="#"><img src="<%=request.getContextPath()%>/app/page/register/adver2.png" width="500px" /></a>
			</div>
			<div class="content-register">
                            <form action="<%=request.getContextPath()%>/addUser" method="POST">
					<div class="input-group margin-bottom-sm">
						<span class="input-group-addon"><i class="fa fa-mobile fa-fw"></i></span>
						<input class="form-control" type="text" placeholder="请输入手机号码" name="phone">
					</div>
					<div class="input-group margin-bottom-sm">
						<span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
						<input class="form-control" type="text" placeholder="请输入昵称" name="nickname">
					</div>

					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
						<input class="form-control" type="password" placeholder="请输入4-16位密码" name="password">
					</div>
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
						<input class="form-control" type="password" placeholder="请再次输入密码">
					</div>
					<div class="input-group">
						<input type="text" class="form-control" placeholder="请输入验证码">
						<span class="input-group-btn">
                      <button class="btn btn-primary" type="button">获取短信验证码<%=request.getContextPath()%></button>
                    </span>
					</div>
					<div class="re-agree">
						<label>
							<input type="checkbox" checked="checked">我已阅读并同意<a href="#">《农汇服务条款》</a>
						</label>
					</div>
					<div class="re-submit">
						<input type="submit" class="btn btn-primary" value="同意协议并注册" />
					</div>
				</form>
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

		<script type="text/javascript" src="<%=request.getContextPath()%>/app/page/register/register.js"></script>
	</body>

</html>
