<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 07/04/15
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <a href='<spring:url value="/"/>' class="navbar-brand">Orange Home</a>

        <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="home"><a href='<spring:url value="/"/>'>Home</a></li>
                <li class="crawler"><a href='#'>Tab 1</a></li>
                <li class="jobolizer"><a href='#'>Tab 2</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="googleAPI"><a href='#'>1</a></li>
                        <li class="jobborse"><a href='#'>2</a></li>
                        <li class="stellenAtlas"><a href='#'>3</a></li>
                    </ul>
                </li>
                <li class="utilities"><a href="#">Utilities</a></li>
                <li class="settings"><a href="#">Settings</a></li>
                <li class="about"><a href="#">About</a></li>
                <li class="contacts"><a href="#">Contacts</a></li>
            </ul>
        </div>
    </div>
</div>