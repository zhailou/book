<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8">
<title>主界面</title>
<!-- 告诉浏览器不要缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">

						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">图书管理系统</a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">

							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">书籍管理<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="bookAdd.jsp">添加</a></li>
									<li><a href="#">删除</a></li>
									<li><a href="#">修改</a></li>
									<li><a href="#">查找</a></li>



								</ul></li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<li><a href="#">修改密码</a></li>
							<li><a href="#">登录</a></li>

						</ul>
					</div>

				</nav>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="carousel slide" id="carousel-950296">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0"
							data-target="#carousel-950296"></li>
						<li data-slide-to="1" data-target="#carousel-950296"></li>
						<li data-slide-to="2" data-target="#carousel-950296"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img alt="Carousel Bootstrap First"
								src="https://www.layoutit.com/img/sports-q-c-1600-500-1.jpg" />
							<div class="carousel-caption">
								<h4>First Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
						<div class="item">
							<img alt="Carousel Bootstrap Second"
								src="https://www.layoutit.com/img/sports-q-c-1600-500-2.jpg" />
							<div class="carousel-caption">
								<h4>Second Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
						<div class="item">
							<img alt="Carousel Bootstrap Third"
								src="https://www.layoutit.com/img/sports-q-c-1600-500-3.jpg" />
							<div class="carousel-caption">
								<h4>Third Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-950296"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="right carousel-control" href="#carousel-950296"
						data-slide="next"><span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
<br>

				<p>&copy南阳理工学院</p>

			</div>
		</div>
	</div>


	<script type="text/javascript"
		src="bower_components/jquery/dist/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/bootstrap/dist/js/bootstrap.min.js">
		
	</script>
</body>
</html>