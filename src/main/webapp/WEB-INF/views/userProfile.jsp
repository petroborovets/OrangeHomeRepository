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
                <li><a href="#tab_3" data-toggle="tab"><i style="color:#428bca" class="fa fa-envelope"></i> Email template</a>
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
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Compose New Message</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <div class="form-group">
                                <input class="form-control" placeholder="To:"/>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Subject:"/>
                            </div>
                            <div class="form-group">
                    <textarea id="compose-textarea" class="form-control" style="height: 300px">
                      <h1><u>Heading Of Message</u></h1>
                      <h4>Subheading</h4>
                      <p>But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure? On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee</p>
                      <ul>
                          <li>List item one</li>
                          <li>List item two</li>
                          <li>List item three</li>
                          <li>List item four</li>
                      </ul>
                      <p>Thank you,</p>
                      <p>John Doe</p>
                    </textarea>
                            </div>
                            <div class="form-group">
                                <div class="btn btn-default btn-file">
                                    <i class="fa fa-paperclip"></i> Attachment
                                    <input type="file" name="attachment"/>
                                </div>
                                <p class="help-block">Max. 32MB</p>
                            </div>
                        </div><!-- /.box-body -->
                        <div class="box-footer">
                            <div class="pull-right">
                                <button class="btn btn-default"><i class="fa fa-pencil"></i> Draft</button>
                                <button type="submit" class="btn btn-primary"><i class="fa fa-envelope-o"></i> Send</button>
                            </div>
                            <button class="btn btn-default"><i class="fa fa-times"></i> Discard</button>
                        </div><!-- /.box-footer -->
                    </div><!-- /. box -->
                </div>
                <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
        </div>
        <!-- nav-tabs-custom -->
    </section>
</div>
