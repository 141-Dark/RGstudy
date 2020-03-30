<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--使用自定义标签c标签 cif cforecah  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目主页</title>
</head>
<body>
			<center>
					<h1>主页面</h1>
					<h3>欢迎${USERNAME }登录成功</h3>
					<table class="table table-bordered">
  						<thead>
  							<th>账号</th>
  							<th>密码</th>
  						</thead>
  						<tbody>
  							<!--循环来显示查询出来的数据  -->
  							<c:forEach items="${userList }" var="user">
  								<tr>
  									<td>${user.username }</td>
  									<td>${user.password }</td>
  								</tr>
  							</c:forEach>
  						</tbody>
					</table>
			</center>
</body>
</html>