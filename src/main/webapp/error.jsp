<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 07.02.2018
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.pagecontent"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>FireBets</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleMain.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Dosis:400,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
</head>

<body>
<div>
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

    <div class="matches">
        ${requestScope.error.message}
    </div>
</div>

<div>
    <%@include file="WEB-INF/jsp/jspf/footer.jspf"%>
</div>
</body>
</html>
