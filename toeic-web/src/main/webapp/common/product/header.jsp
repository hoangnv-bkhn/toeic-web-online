<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 8/2/2021
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="header">
    <div class="headertop_desc">
        <div class="call">
            <a href="/home.html" >Home</a>
        </div>
        <div class="account_desc">
            <ul>
                <c:if test="${not empty login_name}">
                    <li>Xin chào: ${login_name}</li>
                    <c:url var="logoutUrl" value="/logout.html">
                        <c:param name="action" value="logout"/>
                    </c:url>
                    <li><a href="${logoutUrl}"><fmt:message key="label.logout" bundle="${lang}"/></a></li>
                </c:if>
                <c:if test="${empty login_name}">
                    <c:url var="loginUrl" value="/login.html">
                        <c:param name="action" value="login"/>
                    </c:url>
                    <li><a href="${loginUrl}"><fmt:message key="label.login" bundle="${lang}"/></a></li>
                </c:if>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</div>
