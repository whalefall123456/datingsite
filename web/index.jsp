<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="text.jsp">Hello Servlet</a>
<a href="index?method=showUser">aaaaa</a>
<a href="guest.html">留言测试</a>
<a href="user?method=userInfo&uid=10000001">用户信息展示</a>
<a href="user?method=myselfInfo&uid=10000001">个人信息展示</a>
<a href="login.html">登录页面</a>
<a href="index.html">首页</a>
<a href="findfriend.html">寻友界面</a>
<a href="news.html">新闻</a>
<a href="bbs.html?type=1">论坛</a>
<a href="test2.html">添加好友测试</a>
<a href="posttest.html">帖子测试</a>
<a href="myview.html">用户管理页面</a>
</body>
</html>