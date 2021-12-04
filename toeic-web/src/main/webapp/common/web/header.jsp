<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--HEADER ROW-->
<div id="header-row">
    <div class="container">
        <div class="row">
            <!-- MAIN NAVIGATION -->
            <ul>
                <li class="col-12 col-sm-2"><a href="home.html">TOEIC ONLINE</a></li>
                <li class="col-12 col-sm"><a href="about.html">Hướng dẫn</a></li>
                <li class="col-12 col-sm"><a href="service.html">Luyện tập</a></li>
                <li class="col-12 col-sm"><a href="service.html">Thi thử</a></li>
                <c:if test="${not empty login_name}">
                    <li class="col-12 col-sm">Xin chào: ${login_name}</li>
                    <c:url var="logoutUrl" value="/logout.html">
                        <c:param name="action" value="logout"/>
                    </c:url>
                    <li class="col-12 col-sm"><a href="${logoutUrl}"><fmt:message key="label.logout" bundle="${lang}"/></a></li>
                </c:if>
                <c:if test="${empty login_name}">
                    <c:url var="loginUrl" value="/login.html">
                        <c:param name="action" value="login"/>
                    </c:url>
                    <li class="col-12 col-sm"><a href="${loginUrl}"><fmt:message key="label.login" bundle="${lang}"/></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>