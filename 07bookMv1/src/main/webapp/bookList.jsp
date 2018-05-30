<%@page import="cn.edu.nyist.bookMv1.vo.TypeVo"%>
<%@page import="cn.edu.nyist.bookMv1.vo.BookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8">
<title>图书列表</title>
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
				<!-- 此处应添加表格 -->
				<table class="table table-hover table-bordered">

					<thead>
						<tr>
							<td colspan="9">
								<form class="form-inline" id="searchFrom">
									<div class="form-group">
										<label for="inputName">书名</label>
										<!-- 书名回填-->
										<input type="text" class="form-control" id="inputName"
											name="name"
											value='<%=request.getAttribute("name") == null ? "" : request.getAttribute("name")%>'>
									</div>
									<div class="form-group">
										<label for="selTid">类型</label> <select id="selTid" name="tid"
											class="form-control">
											<option value="-1">--请选择--</option>

											<c:forEach items="${requestScope.types}" var="typeVo">
												<c:choose>
													<c:when test="${requestScope.tid==requestScope.id}">
														<option selected="selected" value="${typeVo.id }">${typeVo.name }</option>
													</c:when>
													<c:otherwise>
														<option value="${typeVo.id }">${typeVo.name }</option>
													</c:otherwise>
												</c:choose>

											</c:forEach>



										</select>


									</div>
									<button type="submit" class="btn btn-default">搜索</button>
								</form>
							</td>
						</tr>
						<tr>
							<th>id</th>
							<th>tid</th>
							<th>name</th>
							<th>descri</th>
							<th>photo</th>
							<th>price</th>
							<th>author</th>
							<th>pubDate</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody>
						
						
						<c:forEach items="${requestScope.ls }" var="bookVo">
						<tr>
							<td>${bookVo.id}</td>
							<td>${bookVo.tid}</td>
							<td>${bookVo.name}</td>
							<td>${bookVo.descri}</td>
							<td><img alt="" src="upload/${bookVo.photo}"
								style="height: 150px"></td>
							<td>${bookVo.price}</td>
							<td>${bookVo.author}</td>
							<td>${bookVo.pubDate}</td>
							<td><a href="bookDel?id=${v.id}"
								class="glyphicon glyphicon-trash" title="删除"
								onclick="conFormDel(event)"></a>&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="toBookEdit?id=${bookVo.id}"
								class="glyphicon glyphicon-pencil" title="修改"></a></td>
						</tr>
						</c:forEach>
						
						
						
						
						<tr>
							<td colspan="9" class="text-center">
								<ul class="pagination" style="margin-top: -8px;">
								
								<c:choose>
								<c:when test="${requestScope.pageNo==1 }">
								<li class="disabled"><a href="#">&lt&lt</a></li>
								</c:when>
								<c:otherwise>
								<li><a href="bookList?pageNo=${requestScope.pageNo-1 }">&lt&lt</a></li>
								</c:otherwise>
								</c:choose>
								
								
								<c:choose>
								<c:when test="${requestScope.totalPage<=5}">
								<c:forEach begin="1" end="${requestScope.totalPage}" var="i">
								<li><a href="bookList?pageNo=${i}">${i}</a></li>
								</c:forEach>
								</c:when>
								<c:when test="${requestScope.pageNo<=3}">
								<li><a href="bookList?pageNo=1">1</a></li>
									<li><a href="bookList?pageNo=2">2</a></li>
									<li><a href="bookList?pageNo=3">3</a></li>
									<li><a href="bookList?pageNo=4">4</a></li>
									<li><a href="bookList?pageNo=${requestScope.totalPage}">${requestScope.totalPage}</a></li>
								</c:when>
								<c:when test="${requestScope.pageNo>=requestScope.totalPage-2}">
								<li><a href="bookList?pageNo=1">1..</a></li>
									<li><a href="bookList?pageNo=${requestScope.totalPage-3}">${requestScope.totalPage-3}</a></li>
									<li><a href="bookList?pageNo=${requestScope.totalPage-2}">${requestScope.totalPage-2}</a></li>
									<li><a href="bookList?pageNo=${requestScope.totalPage-1}">${requestScope.totalPage-1}</a></li>
									<li><a href="bookList?pageNo=${requestScope.totalPage}">${requestScope.totalPage}</a></li>
								</c:when>
								<c:otherwise><li><a href="bookList?pageNo=1">1..</a></li>
									<li><a href="bookList?pageNo=${requestScope.pageNo-1}">${requestScope.pageNo<=3}</a></li>
									<li><a href="bookList?pageNo=${requestScope.pageNo}">${requestScope.pageNo}</a></li>
									<li><a href="bookList?pageNo=${requestScope.pageNo+1}">${requestScope.pageNo+1}</a></li>
									<li><a href="bookList?pageNo=${requestScope.totalPage}">..${requestScope.totalPage}</a></li></c:otherwise>
								</c:choose>
								
								
								<c:choose>
								<c:when test="${requestScope.pageNo==requestScope.totalPage}">
								<li class="disabled"><a href="#">&gt&gt</a></li>
								</c:when>
								<c:otherwise>
								<li><a href="bookList?pageNo=${requestScope.pageNo+1 }">&gt&gt</a></li>
								</c:otherwise>
								</c:choose>
									

								</ul>
							</td>
						</tr>


					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<br>

				<p>&copy;南阳理工学院</p>

			</div>
		</div>
	</div>


	<script type="text/javascript"
		src="bower_components/jquery/dist/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/bootstrap/dist/js/bootstrap.min.js">
		
	</script>
	<script type="text/javascript">
	$(function() {
		$("a[href='bookList?pageNo=${requestScope.pageNo}']").parent("li").addClass("active");
			//修改链接，在搜索后面追加name和id
			$(".pagination a[href^='bookList?pageNo=']").click(function() {
				this.href += "&" + $("#searchFrom").serialize();
			});
		});
		function conFormDel(event) {
			//取消默认删除行为
			if (!confirm("确定删除!")) {
				event.preventDefault();
			}
		};
	</script>
</body>
</html>