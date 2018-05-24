<%@page import="cn.edu.nyist.bookMv1.vo.TypeVo"%>
<%@page import="cn.edu.nyist.bookMv1.vo.BookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
						<td colspan="8">
							<form class="form-inline" id="searchFrom">
								<div class="form-group">
									<label for="inputName">书名</label> 
									<!-- 书名回填-->
									<input type="text" class="form-control" id="inputName" name="name" value='<%=request.getAttribute("name") == null ? "" : request.getAttribute("name")%>'>
								</div>
								<div class="form-group">
									<label for="selTid">类型</label> 
									<select id="selTid" name="tid" class="form-control">
									<option value="-1">--请选择--</option>
									<% 
									List<TypeVo> ls2=(List<TypeVo>)request.getAttribute("types");
									int tid = (Integer) request.getAttribute("tid");
									for(TypeVo typeVo:ls2){
										if(tid==typeVo.getId()){
											%>
											<option selected="selected" value="<%=typeVo.getId() %>" ><%=typeVo.getName() %></option>
											<%
										}else{
											%>
											<option value="<%=typeVo.getId() %>" ><%=typeVo.getName() %></option>
											<%
										}
									}		
									%>
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
						</tr>
					</thead>
					
					<tbody>
						<%
							List<BookVo> ls = (List<BookVo>) request.getAttribute("ls");
							for (BookVo bookVo : ls) {
						%>
						<tr>
							<td><%=bookVo.getId()%></td>
							<td><%=bookVo.getTid()%></td>
							<td><%=bookVo.getName()%></td>
							<td><%=bookVo.getDescri()%></td>
							<td><img alt="" src="upload/<%=bookVo.getPhoto()%>"
								style="width: 80px"></td>
							<td><%=bookVo.getPrice()%></td>
							<td><%=bookVo.getAuthor()%></td>
							<td><%=bookVo.getPubDate()%></td>
						</tr>

						<%
							}
						%>
						<tr>
							<td colspan="8" class="text-center">
								<ul class="pagination" style="margin-top: -8px;">
									<%
										int pageNo = (Integer) request.getAttribute("pageNo");//获取当前页
										if (pageNo == 1) {
									%>
									<li class="disabled"><a href="#">&lt&lt</a></li>
									<%
										} else {
									%>
									<li><a href="bookList?pageNo=<%=pageNo - 1%>">&lt&lt</a></li>
									<%
										}
									%>

									<%
										int totalPage = (Integer) request.getAttribute("totalPage");//获取总页数
										if (totalPage <= 5) {
											for (int i = 1; i <= totalPage; i++) {
									%>
									<li><a href="bookList?pageNo=<%=i%>"><%=i%></a></li>
									<%
										}

										} else if (pageNo <= 3) {
									%>
									<li><a href="bookList?pageNo=1">1</a></li>
									<li><a href="bookList?pageNo=2">2</a></li>
									<li><a href="bookList?pageNo=3">3</a></li>
									<li><a href="bookList?pageNo=4">4</a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>"><%=totalPage%></a></li>
									<%
										} else if (pageNo >= totalPage - 2) {
									%>
									<li><a href="bookList?pageNo=1">1..</a></li>
									<li><a href="bookList?pageNo=<%=totalPage - 3%>"><%=totalPage - 3%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage - 2%>"><%=totalPage - 2%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage - 1%>"><%=totalPage - 1%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>"><%=totalPage%></a></li>
									<%
										} else {
									%>
									<li><a href="bookList?pageNo=1">1..</a></li>
									<li><a href="bookList?pageNo=<%=pageNo - 1%>"><%=pageNo - 1%></a></li>
									<li><a href="bookList?pageNo=<%=pageNo%>"><%=pageNo%></a></li>
									<li><a href="bookList?pageNo=<%=pageNo + 1%>"><%=pageNo + 1%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>">..<%=totalPage%></a></li>
									<%
										}
									%>
									
									<%
										if (pageNo == totalPage) {
									%>
									<li class="disabled"><a href="#">&gt&gt</a></li>
									<%
										} else {
									%>
									<li><a href="bookList?pageNo=<%=pageNo + 1%>">&gt&gt</a></li>
									<%
										}
									%>

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
		$("a[href='bookList?pageNo=<%=pageNo%>']").parent("li").addClass(
					"active");
		//修改链接，在搜索后面追加name和id
		$(".pagination a[href^='bookList?pageNo=']").click(function () {
			this.href+="&"+$("#searchFrom").serialize();
		});
		});
	</script>
</body>
</html>