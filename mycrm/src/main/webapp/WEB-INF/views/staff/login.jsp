<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>凯盛软件CRM</title>
  <%@include file="include/css.jsp"%>
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="#"><b>凯盛软件</b></a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <h4><p class="login-box-msg">员工登录</p></h4>
    <c:if test="${not empty error}">
      <p class="login-box-msg" style="color: #9c3328">账号或密码错误</p>
    </c:if>

    <form method="post">
      <div class="form-group has-feedback">
        <input name="userName" type="text" class="form-control" placeholder="工号或者手机号">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input name="password" type="password" class="form-control" placeholder="密码">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <a href="#">忘记密码</a><br>
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-offset-8 col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

    

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
