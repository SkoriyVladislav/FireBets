<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 01.12.2017
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>FireBets</title>
        <script src="${pageContext.servletContext.contextPath}/web/js/jsScripts.js"></script>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleFireBets.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Dosis:400,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
    </head>

    <body>
        <div class="top">
            <div class="logo-top">
                FireBets
            </div>

            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <%@include file="WEB-INF/jsp/jspf/user_profile.jspf" %>
                </c:when>

                <c:otherwise>
                    <%@include file="WEB-INF/jsp/jspf/user_login.jspf" %>
                </c:otherwise>
            </c:choose>
        </div>

        <%@include file="WEB-INF/jsp/jspf/header.jspf"%>

        <%@include file="WEB-INF/jsp/jspf/footer.jspf"%>
    </body>
</html>
