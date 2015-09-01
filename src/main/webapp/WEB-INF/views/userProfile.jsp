<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 6/20/15
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- Content Wrapper. Contains page content -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Profile
            <small>Page</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href='<c:url value="/" />'><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">My profile</li>
        </ol>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">
                <strong>Success!</strong> ${successMessage}
            </div>
        </c:if>
        <c:if test="${not empty infoMessage}">
            <div class="alert alert-info">
                <strong>Info!</strong> ${infoMessage}
            </div>
        </c:if>
        <c:if test="${not empty warningMessage}">
            <div class="alert alert-warning">
                <strong>Warning!</strong> ${warningMessage}
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                <strong>Error!</strong> ${errorMessage}
            </div>
        </c:if>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Custom Tabs -->
        <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#tab_1" data-toggle="tab"><i style="color:#5cb85c" class="fa fa-user"></i>
                    Profile</a></li>
                <li><a href="#tab_2" data-toggle="tab"><i style="color:#f0ad4e" class="fa fa-comment"></i> Activity Feed</a>
                </li>
                <li><a href="#tab_3" data-toggle="tab"><i style="color:#428bca" class="fa fa-users"></i> Friends</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="tab_1">
                    <div class="row">
                        <div class="col-md-3 center-block">
                            <img class="img-responsive img-thumbnail" src='<c:url value="/resources/img/avatar5.png" />'
                                 width="200"
                                 height="200"
                                 style="display: block; margin-left: auto; margin-right: auto">

                            <button class="btn btn-primary btn-block" style="margin-top: 5px">Edit profile</button>
                        </div>
                        <div class="col-md-9">
                            <p class="text-light-blue">

                            <h3><c:out value="${userFirstName}"/> <c:out value="${userLastName}"/></h3>
                            </p>
                            <div class="box">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tbody>
                                        <tr>
                                            <td>E-mail address</td>
                                            <td align="left">
                                                <c:if test="${not empty pageContext.request.userPrincipal}">
                                                    <c:out value="${pageContext.request.userPrincipal.name}"/>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Location</td>
                                            <td align="left">
                                                Lviv, Ukraine
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Age</td>
                                            <td align="left">
                                                21
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Joined</td>
                                            <td align="left">
                                                <c:out value="${userCreationDate}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Website</td>
                                            <td align="left">
                                                www.blackwidow.com
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><i class="fa fa-fw fa-facebook"></i></td>
                                            <td align="left">
                                                <a href="http://facebook.com"> Facebook </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><i class="fa fa-fw fa-linkedin"></i></td>
                                            <td align="left">
                                                <a href="http://linkedin.com"> Linked In </a>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.tab-pane -->
                <div class="tab-pane" id="tab_2">
                    The European languages are members of the same family. Their separate existence is a myth.
                    For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ
                    in their grammar, their pronunciation and their most common words. Everyone realizes why a
                    new common language would be desirable: one could refuse to pay expensive translators. To
                    achieve this, it would be necessary to have uniform grammar, pronunciation and more common
                    words. If several languages coalesce, the grammar of the resulting language is more simple
                    and regular than that of the individual languages.
                </div>
                <!-- /.tab-pane -->
                <div class="tab-pane" id="tab_3">
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                    when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                    It has survived not only five centuries, but also the leap into electronic typesetting,
                    remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset
                    sheets containing Lorem Ipsum passages, and more recently with desktop publishing software
                    like Aldus PageMaker including versions of Lorem Ipsum.
                </div>
                <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
        </div>
        <!-- nav-tabs-custom -->
    </section>
</div>
