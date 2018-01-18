<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 18.12.2017
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Profile</title>
</head>
<body>
    <c:forEach var="user" items="${requestScope.user}" >
        <c:out value="${user.login}"/><br>
        <c:out value="${user.name}"/><br>
        <c:out value="${user.surname}"/><br>
        <c:out value="${user.email}"/><br>
        <c:out value="${user.balance}"/><br>
        <c:out value="${user.type}"/><br>
    </c:forEach>

    <c:if test="${requestScope.users.isEmpty()}">
        <c:out value="User is not in database"/>
    </c:if>
</body>
</html>
