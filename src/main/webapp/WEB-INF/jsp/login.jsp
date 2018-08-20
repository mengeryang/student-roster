<%--
  Created by IntelliJ IDEA.
  User: wap-lvfai
  Date: 18-8-10
  Time: 下午5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Roster Management System Sign In</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
    <link href="/css/login.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <%--<script src="/js/login.js"></script>--%>
</head>
<body>
<div class="container">
    <div align="ce">
        <h1 class="form-signin-heading" align="center">Roster Management System</h1>
        <form:form class="form-signin" method="post" modelAttribute="user">
            <!--<label for="username">User Name</label>-->
            <%--<input type="text" id="userId" class="form-control" placeholder="User ID" required autofocus>--%>
            <form:input type="text" path="userId" class="form-control" placeholder="User ID" />
            <!--<label for="password">Password</label>-->
            <%--<input type="password" id="password" class="form-control" placeholder="Password" required>--%>
            <form:input type="password" path="password" class="form-control" placeholder="Password"/>
            <%--<div class="checkbox">--%>
                <%--<label>--%>
                    <%--<input type="checkbox" id="remember_me">Remember me--%>
                <%--</label>--%>
            <%--</div>--%>
            <button class="btn btn-primary btn-lg btn-block" type="submit">Sign in</button>
            <button class="btn btn-warning btn-lg btn-block" type="button" onclick="location.href='signup.html'">Sign up</button>
        </form:form>
    </div>
</div>
</body>
</html>
