<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 5/10/15
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<c:url value="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" />">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/resources/LTE/css/AdminLTE.min.css" />">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="<c:url value="/resources/LTE/css/skins/skin-purple.min.css" />">


    <title><tiles:getAsString name="title"/></title>
</head>

<tiles:insertAttribute name="body"/>