<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 19.01.2018
  Time: 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.page_content" var="tc"/>
<html>
    <head>
        <title>FireBets</title>
    </head>

    <body>
        <jsp:forward page="${pageContext.request.contextPath}/controller?command=go_to_main"/>
    </body>
</html>
