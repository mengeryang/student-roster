<%--
  Created by IntelliJ IDEA.
  User: wap-lvfai
  Date: 18-8-14
  Time: 下午3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-theme.min.css">

    <!-- Website CSS style -->
    <link rel="stylesheet" type="text/css" href="/css/signup.css">

    <!-- Website Font style -->
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">--%>

    <!-- Google Fonts -->
    <%--<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>--%>
    <%--<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>--%>
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <%--<script src="/js/validator.min.js"></script>--%>
    <script src="/js/signup.js"></script>


    <title>Sign Up</title>
</head>
<body>
<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Sign Up</h1>
                <hr />
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post">

                <div class="form-group">
                    <label for="id" class="cols-sm-2 control-label">Your ID</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="id" id="id" placeholder="Enter your ID" required autofocus/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">Your Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="name" id="name" placeholder="Enter your name" required/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Enter your Password" required/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" class="form-control" name="confirm" id="confirm" placeholder="Confirm your Password" required/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="button" class="btn btn-primary btn-lg btn-block login-button" id="register">Register</button>
                </div>
                <%--<div class="form-group ">--%>
                    <%--<button type="submit" class="btn btn-primary btn-lg btn-block login-button" id="register">Register</button>--%>
                <%--</div>--%>
                <div class="login-register">
                    <a href="/login.html">Login</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
