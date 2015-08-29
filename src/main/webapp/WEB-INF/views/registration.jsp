<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 5/10/15
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <title>OrangeHome | Registration</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link rel="stylesheet" href='<c:url value="/resources/LTE/css/AdminLTE.min.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/LTE/plugins/iCheck/square/blue.css" />'>
</head>
<body class="register-page">
<div class="register-box">
    <div class="register-logo">
        <a href='<spring:url value="/"/>'><b>Black</b>Widow</a>
    </div>

    <div class="register-box-body">
        <p class="login-box-msg">Register a new membership</p>
        <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-ban"></i> Try again</h4>
            <c:out value="${errorMessage}"/>
        </div>
        </c:if>
        <form:form action="registerUser" method="post" commandName="userDTO">
            <div class="form-group has-feedback">
                <form:input type="text" class="form-control" placeholder="First name" path="firstName" spellcheck="false"/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <form:input type="text" class="form-control" placeholder="Last name" path="lastName" spellcheck="false"/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <form:input type="email" class="form-control" placeholder="Email" path="email" spellcheck="false"/>
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <form:input type="password" class="form-control" placeholder="Password" path="password"/>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <form:input type="password" class="form-control" placeholder="Retype password" path="confirmPassword"/>
                <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox"> I agree to the <a href="#">terms</a>
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Register</button>
                </div>
                <!-- /.col -->
            </div>
        </form:form>

        <div class="social-auth-links text-center">
            <p>- OR -</p>
            <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign up
                using Facebook</a>
            <a href="#" class="btn btn-block btn-social btn-google-plus btn-flat"><i class="fa fa-google-plus"></i> Sign
                up using Google+</a>
        </div>

        <a href='<spring:url value="/loginForm"/>' class="text-center">I already have a membership</a>
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 2.1.3 -->
<script src='<c:url value="/resources/LTE/plugins/jQuery/jQuery-2.1.4.min.js" />'></script>
<!-- Bootstrap 3.3.2 JS -->
<script src='<c:url value="/resources/bootstrap/js/bootstrap.min.js" />' type="text/javascript"></script>
<!-- iCheck -->
<script src='<c:url value="/resources/LTE/plugins/iCheck/icheck.min.js" />' type="text/javascript"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>

