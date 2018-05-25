<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<style>
.container-fluid {
	width: 50%;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -304px;
	margin-top: -118px;
}
</style>
</head>
<body>
	<div class="container-fluid well ">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" role="form" method="post"
					action="login" id="loginForm">
					<div class="form-group">
					<%if(request.getAttribute("msg") != null ) {%>
						<div class="alert alert-warning" role="alert"><%=request.getAttribute("msg")%></div>
						<%} %>
						<label for="inputName" class="col-sm-2 control-label"> 用户名：
						 </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputName"
								name="name"
								value="<%=request.getAttribute("name") == null ? "" : request.getAttribute("name")%>" />
						</div>
					</div>
					<div class="form-group">

						<label for="inputpwd" class="col-sm-2 control-label"> 密码：
						</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="inputpwd"
								name="pwd" />
						</div>
					</div>
					<div class="form-group">

						<label for="inputvcode" class="col-sm-2 control-label"> 验证码：
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputvcode"
								name="vcode" maxlength="4" />
						</div>
						<div class="col-sm-2">
						<img alt="" src="vcode.png" id="vcodeImg" title="点击更换图片">
						</div>
					</div>
					
					

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">

							<button type="submit" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	<!--  因为bootstrp完全依赖于jquety,所以jquery要放在前面-->
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js">
	</script>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.min.js">
	</script>
	<script type="text/javascript" src="bower_components/jquery-validation/dist/jquery.validate.min.js">
	</script>
	<script type="text/javascript" src="bower_components/jquery-validation-bootstrap-tooltip/jquery-validate.bootstrap-tooltip.js">
	</script>
<script type="text/javascript">
//点击验证码，换验证码图片
$(function(){
$("#vcodeImg").click(function(evt) {//此处参数可为任意参数
	this.src="vcode.png?t="+Math.random();//加 Math.random() 使每次请求地址不相同 服务器每次都去做不同的响应
});
$("#loginForm").validate({
	//添加验证
    rules: {
       name: { required: true },
       pwd: { required: true }
    },
    messages : {
		name:"必须填写",
		pwd:"必须填写"
	},
    tooltip_options: {
       name: { placement: 'bottom' },
       pwd: { placement: 'bottom' }
    }
 });

	});

</script>

</body>
</html>