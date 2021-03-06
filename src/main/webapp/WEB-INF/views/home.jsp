<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Black Widow
            <small>home page</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href='<c:url value="/" />'><i class="fa fa-dashboard"></i> Home</a></li>
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
        <div class="row">
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-aqua">
                    <div class="inner">
                        <h3><c:out value="${companiesSavedCount}"/></h3>

                        <p>Companies</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-bag"></i>
                    </div>
                    <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-green">
                    <div class="inner">
                        <h3><c:out value="${emailsSavedCount}"/></h3>

                        <p>Emails</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-stats-bars"></i>
                    </div>
                    <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-yellow">
                    <div class="inner">
                        <h3><c:out value="${usersRegisteredCount}"/></h3>

                        <p>User Registrations</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-person-add"></i>
                    </div>
                    <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-red">
                    <div class="inner">
                        <h3>65</h3>

                        <p>Running tasks</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-pie-graph"></i>
                    </div>
                    <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
            <!-- ./col -->
        </div>

        <div class="row">
            <div class="col-lg-12 col-xs-6">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Recent tasks</h3>

                        <div class="box-tools pull-right">
                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                        </div>
                        <!-- /.box-tools -->
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table id="entityTable" data-toggle="table" data-pagination="true"
                               data-id-field="id" data-show-refresh="true" data-sort-order="desc"
                               data-sort-name="createDate"
                               data-page-list="[10, 20, 50, 100, 1000]" data-search="true" toolbarAlign="left"
                               data-url="<spring:url value="${contextPath}/spider/info"/>">
                            <thead>
                            <tr>
                                <th data-width="10" data-field="id" data-sortable="true">#</th>
                                <th data-width="10" data-field="name" data-switchable="false"
                                    data-sortable="true">Task name
                                </th>
                                <th data-width="10" data-field="numberOfEmails" data-sortable="true">Emails found</th>
                                <th data-width="10" data-field="progress" data-sortable="true">Progress</th>
                                <th data-field="startDate" data-sortable="true">Time started</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>

    </section>
</div>


<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="/resources/LTE/js/bug-min.js"></script>
<script>
    $(document).ready(function () {
        $('.homePageLi').addClass('active');
        $("body").hide().fadeIn(2000);
        // default spiders:
        new SpiderController({
            'maxBugs': 5
        });
    });
</script>


