<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 6/23/2021
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title><dec:title default="Admin Page"/></title>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value = '/templates/admin/css/bootstrap.min.css'/>" />
    <link rel="stylesheet" href="<c:url value = '/templates/admin/font-awesome/4.2.0/css/font-awesome.min.css'/>" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="<c:url value = '/templates/admin/fonts/fonts.googleapis.com.css'/>" />

    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value = '/templates/admin/css/ace.min.css'/>" class="ace-main-stylesheet" id="main-ace-style" />

    <!-- ace settings handler -->
    <script src="<c:url value = '/templates/admin/js/ace-extra.min.js'/>"></script>

    <dec:head/>
</head>
<body class="no-skin">
    <%@ include file="/common/admin/header.jsp" %>
<%--    <%@ include file="/common/admin/menu.jsp" %>--%>
    <dec:body/>
<%--    <%@ include file="/common/admin/footer.jsp" %>--%>

    <script src="<c:url value = '/templates/admin/js/bootstrap.min.js'/>"></script>

    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
    <script src="<c:url value = '/templates/admin/js/excanvas.min.js'/>"></script>
    <![endif]-->
    <script src="<c:url value = '/templates/admin/js/jquery-ui.custom.min.js'/>"></script>
    <script src="<c:url value = '/templates/admin/js/jquery.ui.touch-punch.min.js'/>"></script>
    <script src="<c:url value = '/templates/admin/js/jquery.easypiechart.min.js'/>"></script>
    <script src="<c:url value = '/templates/admin/js/jquery.sparkline.min.js'/>"></script>
    <script src="<c:url value = '/templates/admin/js/jquery.flot.min.js'/>"></script>
    <script src="<c:url value = '/templates/admin/js/jquery.flot.pie.min.js'/>"></script>
    <script src="<c:url value = '/templates/admin/js/jquery.flot.resize.min.js'/>"></script>

    <!-- ace scripts -->
    <script src="<c:url value = '/templates/admin/js/ace-elements.min.js'/>"></script>
    <script src="<c:url value = '/templates/admin/js/ace.min.js'/>"></script>
</body>
</html>
