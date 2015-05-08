<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 07/04/15
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <a href='<spring:url value="/"/>' class="navbar-brand">Orange Home</a>

        <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <div class="collapse navbar-collapse navHeaderCollapse">
            <sec:authorize access="!hasAnyRole('USER','ADMIN')">
                <ul class="nav navbar-nav navbar-right">
                    <li class="flag"><a href="<spring:url value="/loginForm"/>">Log In</a></li>
                    <li class="flag"><a id="register" href="<spring:url value="/loginForm"/>">Sign Up</a></li>
                </ul>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
                <ul class="nav navbar-nav navbar-right">
                    <li class="flag">
                        <a href="<spring:url value="/profile"/>">
                            <i class="glyphicon glyphicon-user"></i>
                            <security:authentication
                                    property="principal.username"/>
                        </a>
                    </li>
                    <li><a href="/j_spring_security_logout"> <i
                            class="glyphicon glyphicon-off"></i> Exit
                    </a></li>
                </ul>
            </sec:authorize>
            <ul class="nav navbar-nav navbar-right">
                <li class="home"><a href='<spring:url value="/"/>'>Home</a></li>
                <li class="news"><a href='<spring:url value="/news"/>'>News</a></li>
                <li class="web-spider"><a href='<spring:url value="/crawler"/>'>Web Spider</a></li>
                <li class="utilities"><a href="#">Utilities</a></li>
                <li class="settings"><a href="<spring:url value="/settings"/>">Settings</a></li>
                <li class="about"><a href="<spring:url value="/about"/>">About</a></li>
            </ul>
        </div>
    </div>
</div>