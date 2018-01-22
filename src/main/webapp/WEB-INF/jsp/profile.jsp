<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 18.12.2017
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.page_content"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Profile</title>
    </head>

    <body>
        <c:out value="${sessionScope.user.login}"/><br>
        <c:out value="${sessionScope.user.name}"/><br>
        <c:out value="${sessionScope.user.surname}"/><br>
        <c:out value="${sessionScope.user.email}"/><br>
        <c:out value="${sessionScope.user.balance}"/><br>
        <c:out value="${sessionScope.user.role}"/><br>
    </body>
</html>
