<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Weather
            <small>Statistics</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href='<c:url value="/" />'><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Weather</li>
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
    <section class="content" >
        <!-- Gismeteo Informer (begin) -->
        <div class="row">
            <div class="col-md-3">
                <div class="small-box bg-aqua">
                    <div class="inner">
                        <p>Temperature</p>

                        <h3><c:out value="${weather.temperature}"/> °c</h3>

                        <p>Wind speed</p>

                        <h3><i class="fa fa-location-arrow"> <c:out value="${weather.windSpeed}"/></i></h3>

                        <p>Humidity</p>

                        <h3><i class="fa fa-tint"></i> <c:out value="${weather.humidity}"/> %</h3>

                        <p>Pressure</p>

                        <h3><c:out value="${weather.pressure}"/> <i class="fa fa-line-chart"></i></h3>

                        <p>Water temperature</p>

                        <h3><i class="fa fa-tint"></i> <c:out value="${weather.waterTemperature}"/> °c</h3>

                    </div>
                    <div class="icon">
                        <img src='<c:out value="http://${weather.weatherImageUrl}"/>' width="100" height="100">
                    </div>
                    <a href='<c:out value="http://www.gismeteo.ua/ua/weather-lviv-4949/"/>' class="small-box-footer">Gismeteo
                        <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
            <div class="col-md-9">
                <div class="box box-info collapsed-box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Weather data</h3>

                        <div class="box-tools pull-right">
                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                        </div>
                        <!-- /.box-tools -->
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table id="entityTable" data-toggle="table" data-pagination="true" data-show-columns="true"
                               data-id-field="id" data-search="true" data-show-toggle="true" data-show-export="true"
                               data-show-refresh="true" data-cache="false" data-sort-order="desc" data-sort-name="createDate"
                               data-page-list="[10, 20, 50, 100, 1000]"
                               data-url="<spring:url value="${contextPath}/weather/getJson"/>">
                            <thead>
                            <tr>
                                <th data-width="10" data-field="id" data-sortable="true">#</th>
                                <th data-width="10" data-field="temperature" data-switchable="false"
                                    data-sortable="true">Temperature
                                </th>
                                <th data-width="10" data-field="windSpeed" data-sortable="true">Wind Speed</th>
                                <th data-width="10" data-field="pressure" data-sortable="true">Pressure</th>
                                <th data-width="10" data-field="humidity" data-sortable="true">Humidity</th>
                                <th data-width="10" data-field="waterTemperature" data-switchable="false"
                                    data-sortable="true">Water
                                    Temperature
                                </th>
                                <th data-field="createDate" data-sortable="true">Date</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>

    </section>
</div>

<!-- Activate sidebar tab -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        $('.weatherPageLi').addClass('active');
    });
</script>
