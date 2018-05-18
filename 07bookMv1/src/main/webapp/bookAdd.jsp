<%@page import="cn.edu.nyist.bookMv1.vo.TypeVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书本添加</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
<style>
</style>
</head>
<!-- 书本添加 -->
<body>
	<div class="container-fluid well ">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" role="form" method="post"
					action="bookAdd" id="loginForm" enctype="multipart/form-data">
					<!-- 使用包含文件上传的表单时，必须使用multipart/form-data -->
					<div class="form-group">
						<%
							if (request.getAttribute("msg") != null) {
						%>
						<div class="alert alert-warning" role="alert"><%=request.getAttribute("msg")%></div>
						<%
							}
						%>
						<label for="inputName" class="col-sm-2 control-label"> 书名：
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputName"
								name="name" />
						</div>
					</div>
					<div class="form-group">

						<label for="textareadescri" class="col-sm-2 control-label">
							描述： </label>
						<div class="col-sm-10">
							<textarea type="text" class="form-control" id="textareadescri"
								name="descri"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="inputphoto" class="col-sm-2 control-label">
							图片： </label>
						<div class="col-sm-10">
							<input type="file" class="form-control" id="inputphoto"
								name="photo" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputPrice" class="col-sm-2 control-label">
							价格： </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPrice"
								name="price" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputpubDate" class="col-sm-2 control-label">
							出版日期： </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputpubDate"
								name="pubDate" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputAuthor" class="col-sm-2 control-label">
							作者： </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputAuthor"
								name="author" />
						</div>
					</div>

					<div class="form-group">

						<label for="selectTid" class="col-sm-2 control-label"> 类型:
						</label>
						<div class="col-sm-10">
							<select name="tid" class="form-control" id="selectTid">
								<!--  
								<option value="1">编程</option>
								<option value="2">杂志</option>
								<option value="3">烹饪</option>
								-->
								<!-- 动态生成option，先获取所有类型，再到jsp显示 -->
								<%
								List<TypeVo> ls=(List<TypeVo>)request.getAttribute("ls");
								for(TypeVo typeVo:ls){
									%>
									<option value=<%=typeVo.getId()%>><%=typeVo.getName() %></option>
									<%
								}
								
								%>>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group">

							<label for="inputvcode" class="col-sm-2 control-label">
								验证码： </label>
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

								<button type="submit" class="btn btn-default">添加</button>
							</div>
						</div>
				</form>

			</div>
		</div>
	</div>
	<!--  因为bootstrp完全依赖于jquety,所以jquery要放在前面-->
	<script type="text/javascript"
		src="bower_components/jquery/dist/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/bootstrap/dist/js/bootstrap.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript"
		src="bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript"
		src="bower_components/jquery-validation/dist/jquery.validate.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/jquery-validation-bootstrap-tooltip/jquery-validate.bootstrap-tooltip.js">
		
	</script>
	<script type="text/javascript">
		//点击验证码，换验证码图片
		$(function() {
			$("#vcodeImg").click(function(evt) {//此处参数可为任意参数
				this.src = "vcode.png?t=" + Math.random();//加 Math.random() 使每次请求地址不相同 服务器每次都去做不同的响应
			});
			$('#inputpubDate').datepicker({
				format : 'yyyy-mm-dd',
				language : 'zh-CN',
				autoclose:true
			});

		});
	</script>

</body>
</html>