<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	/* 此次访问后台使用ajax技术
	ajax技术:局部刷新技术,很大程度上节约了资源
	传统的表单提交的弊端：每次都要重新刷新页面,这样就很消耗资源。
	此次使用jQuery封装的ajax
	现在用到最流行的js框架：vue.js
	jQuery:js封装的框架
	*/
	//测试一下jQuery是否引入成功？
	//写一个文档加载就可以测试了，什么叫文档加载事件-->该jsp被加载后就会触发的函数
	/* $(function(){
		alert(123);
	}) */
	function checkLogin(){
		//拿到输入框的值,利用jquery方法
		var username = $("#exampleInputName2").val();
		var password = $("#exampleInputEmail2").val();
		//alert(username+"  "+password);
		//怎么判断输入框为空值呢？只要长度为0，就代表是空值
		if(username.length==0 || password.length==0){
			//在font标签里面显示输入框不能为空
			$("#fo").text("输入框不能为空！");
		}else{
			//需要将username和password传到后台去
			//利用ajax
			$.ajax({//这是jQuery的ajax写法,在ajax内部里面内容都是以键值对的形式存在,每一个键值对都是以逗号隔开
				//设置请求方式
				type:"post",
				//设置访问的url
				url:"userController?op=checkLogin",
				//传值:传值到后台也是通过键值对  左边（不管有没有引号引起来）是键，右边是值
				data:{
					un:username,
					pw:password
				},
				//设置返回值类型
				dataType:"text",
				//成功回调函数(ajax请求成功后,后台返回某些值回来后会执行的函数)
				success:function(info){
					//值返回回来后的操作
					//alert(info);
					//在js里面,==是比较内容;===表示比较地址
					if(info=="NOUSER"){
						$("#fo").text("账号不存在！");
					}else if (info=="ERRORPASSWORD") {
						$("#fo").text("密码错误！");
					}else{
						//跳转到主页
						window.location.href="userController?op=success"
					}
				},
				//失败的回调函数(ajax请求失败会调用的函数)
				error:function(){
					alert("ajax请求失败！");
				}
			})
		}
		
	}


</script>
</head>
<body style="background-color: #ccc">
	<center>
		<h1>登录页面</h1>
		<font color="red" id="fo"></font>
		<form class="form-inline">
		  <div class="form-group">
		    <label for="exampleInputName2">账号</label>
		    <input type="text" class="form-control" id="exampleInputName2" placeholder="请输入账号">
		  </div><br/>
		  <div class="form-group">
		    <label for="exampleInputEmail2">密码</label>
		    <input type="password" class="form-control" id="exampleInputEmail2" placeholder="请输入密码">
		  </div><br/>
		  <button type="button" class="btn btn-default" onclick="checkLogin()">登录</button>
		</form>
	
	</center>
</body>
</html>