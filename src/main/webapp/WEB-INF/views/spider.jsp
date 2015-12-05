<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 7/15/15
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Spider
            <small>Page - create a task</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href='<c:url value="/" />'><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Start spider</li>
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
            <div class="col-md-5">
                <!-- Custom Tabs -->
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab_1" data-toggle="tab">
                                <i style="color:#5cb85c" class="fa fa-terminal"></i> New task</a>
                        </li>
                        <li>
                            <a href="#tab_2" data-toggle="tab">
                                <i style="color:#f0ad4e" class="fa fa-list"></i> Run again</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <!-- /.tab-pane -->
                        <div class="tab-pane active" id="tab_1">
                            <form:form action="/spiderByURL" method="post">
                                <div>
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label>URL <span class="text-red">*</span></label>
                                            <input type="url" name="url" class="form-control" id="companyURL"
                                                   placeholder="Enter URL to crawl">
                                            <label>Name <span class="text-red">*</span></label>
                                            <input type="text" name="taskName" class="form-control" id="taskName"
                                                   placeholder="Enter task name">
                                            <label>Description <span class="text-red">*</span></label>
                                            <input type="text" name="taskDescription" class="form-control" id="taskDescription"
                                                   placeholder="Enter task description">
                                            <div class="form-group">
                                                <label>Select crawling time</label>
                                                <select id="crawlingTime" name="crawlingTime" class="form-control">
                                                    <option value="20">Quick (20 min)</option>
                                                    <option value="60">Normal (Hour)</option>
                                                    <option value="300">High quality (5 hours)</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.box-body -->

                                    <div class="box-footer">
                                        <button id="startSpiderByCompanyURL" class="btn btn-primary">Submit</button>
                                        <span id="spanWrongURL" class="text-red"
                                              style="display: none"> URL is not valid.</span>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="tab-pane" id="tab_2">
                            Coming soon...
                        </div>
                    </div>
                    <!-- /.tab-content -->
                </div>
            </div>
            <div class="col-md-7">
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

<spring:url value="/spider/" var="spiderDetails"/>
<!-- Activate sidebar tab -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $('.spiderPageLi').addClass('active');
        $('.spiderStarterLi').addClass('active');
        // Fires when user click a row
        $('#entityTable').on('click-row.bs.table', function (e, row, $element) {
            var spiderTaskId = row.id;
            var spiderDetPageUrl = "${spiderDetails}";
            window.location.href = spiderDetPageUrl + spiderTaskId;
        });
    });

</script>



