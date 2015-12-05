<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 5/10/15
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<c:url value="/resources/img/avatar5.png" />" class="img-circle" alt="User Image"/>
            </div>
            <div class="pull-left info">
                <p><c:out value="${userFirstName}"/> <c:out value="${userLastName}"/></p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search..."/>
              <span class="input-group-btn">
                <button type='submit' name='search' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="homePageLi"><a href="<c:url value="/" />"><span>Home</span></a><</li>
            <li class="spiderPageLi">
                <a href="#"><span>Spider</span> <i class="fa fa-angle-left pull-right"></i></a>
                <ul class="treeview-menu">
                    <li class="spiderStarterLi"><a href="<c:url value="/spider" />"><span>Starter</span></a></li>
                    <li class="spiderTasksLi"><a href="<c:url value="/spiderTasks" />"><span>Tasks</span></a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#"><span>Multilevel2</span> <i class="fa fa-angle-left pull-right"></i></a>
                <ul class="treeview-menu">
                    <li><a href="#">Link in level 2</a></li>
                    <li><a href="#">Link in level 2</a></li>
                </ul>
            </li>
            <li class="weatherPageLi"><a href="<c:url value="/weather" />"><span>Weather Statistics</span></a></li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
