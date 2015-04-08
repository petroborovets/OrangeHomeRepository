<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 07/04/15
  Time: 2:35 PM
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

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-table.min.css" />">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap-table.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap-table-export.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.base64.js" />"></script>
    <script src="<c:url value="/resources/js/tableExport.js" />"></script>
    <script src="<c:url value="/resources/js/html2canvas.js" />"></script>
    <script src="<c:url value="/resources/js/sprintf.js" />"></script>
    <script src="<c:url value="/resources/js/jspdf.js" />"></script>
    <script src="<c:url value="/resources/js/base64.js" />"></script>


    <title><tiles:getAsString name="title"/></title>
</head>
<body>

<tiles:insertAttribute name="navBar"/>

<tiles:insertAttribute name="body"/>

<br>
<br>
</body>

</html>
