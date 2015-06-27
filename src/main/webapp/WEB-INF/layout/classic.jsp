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
    <link rel="stylesheet" href="<c:url value="/resources/LTE/css/bootstrap-table.min.css" />">
    <!-- iCheck
    <link href="<c:url value="/resources/LTE/plugins/iCheck/flat/blue.css" />" rel="stylesheet" type="text/css"/>
    -->

    <title><tiles:getAsString name="title"/></title>
</head>

<body class="skin-purple">
<div class="wrapper">
    <tiles:insertAttribute name="navBar"/>
    <tiles:insertAttribute name="sideBar"/>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="footer"/>
</div>

<!-- scripts -->
<!-- jQuery 2.1.4 -->
<script src="<c:url value="/resources/LTE/plugins/jQuery/jQuery-2.1.4.min.js"/>"></script>

<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>-->
<!-- Bootstrap 3.3.2 JS -->
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/resources/LTE/js/app.min.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/LTE/js/bootstrap-table.min.js" />"></script>
<script src="<c:url value="/resources/LTE/js/bootstrap-table-export.min.js" />"></script>
<!-- jQuery UI 1.11.2 -->
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>


</body>
</html>
