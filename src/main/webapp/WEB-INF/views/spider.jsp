<%--
  Created by IntelliJ IDEA.
  User: petroborovets
  Date: 7/15/15
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
            <li class="active">Spider</li>
        </ol>
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
                                <i style="color:#5cb85c" class="fa fa-terminal"></i> By Company URL</a>
                        </li>
                        <li>
                            <a href="#tab_2" data-toggle="tab">
                                <i style="color:#f0ad4e" class="fa fa-list"></i> By Existing companies</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <!-- /.tab-pane -->
                        <div class="tab-pane active" id="tab_1">
                            <div>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label >Company URL <span class="text-red">*</span></label>
                                        <input type="companyURL" class="form-control" id="companyURL" placeholder="Enter company URL">
                                    </div>
                                </div><!-- /.box-body -->

                                <div class="box-footer">
                                    <button id="startSpiderByCompanyURL" class="btn btn-primary">Submit</button>
                                    <span id="spanWrongURL" class="text-red" style="display: none"> URL is not valid.</span>
                                    <!--c:if test="">
                                        <span id="spanWrongURL" class="text-red" style="display: none"> ${errorMessage}</span>
                                    -->
                                </div>
                            </div>
                        </div>
                        <!-- /.tab-pane -->
                        <div class="tab-pane" id="tab_2">

                        </div>
                    </div>
                    <!-- /.tab-content -->
                </div>
            </div>
            <div class="col-md-7">
                <table id="entityTable" data-toggle="table" data-pagination="true"
                       data-id-field="id" data-show-refresh="true" data-sort-order="desc" data-sort-name="createDate"
                       data-page-list="[10, 20, 50, 100, 1000]"
                       data-url="<spring:url value="${contextPath}/"/>">
                    <thead>
                    <tr>
                        <th data-width="10" data-field="id" data-sortable="true">#</th>
                        <th data-width="10" data-field="taskName" data-switchable="false"
                            data-sortable="true">Task name
                        </th>
                        <th data-width="10" data-field="compaies" data-sortable="true">Company(s)</th>
                        <th data-width="10" data-field="emailsFound" data-sortable="true">Emails found</th>
                        <th data-width="10" data-field="progress" data-sortable="true">Progress</th>
                        <th data-field="createDate" data-sortable="true">Time started</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </section>
</div>
<spring:url value="/spiderByURL" var="ajaxURL" />
<!-- Activate sidebar tab -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $('.spiderPageLi').addClass('active');
    });
    $("#startSpiderByCompanyURL").bind("click", function () {
        var companyURL = $("#companyURL").val();
        if(isValidURL(companyURL)){
            var ajaxUrl = "${ajaxURL}";
            $.ajax({
                type: 'POST',
                url: ajaxUrl,
                data: {
                    'url': companyURL
                },
                success: function(result){
                    alert("Response received.");
                }
            });
            alert("Request sent.");
        } else {
            $("#spanWrongURL").show();
        }
    });
    function isValidURL(textval) {
        var urlregex = new RegExp(
                "^(http|https|ftp)\://([a-zA-Z0-9\.\-]+(\:[a-zA-Z0-9\.&amp;%\$\-]+)*@)*((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\-]+\.)*[a-zA-Z0-9\-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(\:[0-9]+)*(/($|[a-zA-Z0-9\.\,\?\'\\\+&amp;%\$#\=~_\-]+))*$");
        return urlregex.test(textval);
    }
</script>
