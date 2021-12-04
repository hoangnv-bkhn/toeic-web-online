<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="label.home" bundle="${lang}"/></title>
</head>
<body>
<div class="row">
    <div class="container">
        <div class="row">
            <div class="span6">
                <div class="carousel-caption">
                    <h1>Nguyễn Việt Hoàng</h1>
                    <p class="lead">Luyện thi TOEIC Online</p>
                    <a class="btn btn-large btn-primary" href="#">Sign up today</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <h2><fmt:message key="label.guideline.listen" bundle="${lang}"/></h2>
    <a href="<c:url value="/danh-sach-huong-dan-nghe.html"/>">Read More &rarr;</a>

</div>

<div class="row">
    <h2><fmt:message key="label.exercise" bundle="${lang}"/></h2>
    <c:url var="listExercise" value="/danh-sach-bai-tap.html">
        <c:param name="pojo.type" value="listening"/>
    </c:url>
    <a href="${listExercise}">Read More &rarr;</a>
</div>

<div class="row">
    <h2><fmt:message key="label.examination" bundle="${lang}"/></h2>
    <a href="<c:url value="/danh-sach-bai-thi.html"/>">Read More &rarr;</a>
</div>
</body>
</html>