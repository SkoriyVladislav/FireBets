<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 18.12.2017
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Profile</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleProfile.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Dosis:400,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    </head>

    <body>

        <div class="top">
            <div class="logo-top">
                FireBets
            </div>
        </div>

        <%@include file="jspf/header.jspf"%>

        <div>
            <div class="user-info">
                <div class="user-info-step">
                    <div style="margin-left: 1.5%; font-size: 34px; "><c:out value="${sessionScope.user.name}"/> <c:out value="${sessionScope.user.surname}"/> </div>
                </div>

                <div class="user-info-step" >
                    <div>Ваш логин:</div>
                    <div style="margin-left: 1.5%">
                        <div ><c:out value="${sessionScope.user.login}"/></div>
                    </div>
                </div>

                <div class="user-info-step" >
                    <div>Ваш е-мейл:</div>
                    <div style="margin-left: 1.5%">
                        <div><c:out value="${sessionScope.user.email}"/></div>
                    </div>
                </div>

                <div class="user-info-step" >
                    <div>Ваш баланс:</div>
                    <div style="margin-left: 1.5%">
                        <div><c:out value="${sessionScope.user.balance}"/></div>
                    </div>
                </div>

                <div class="user-info-step" >
                    <div>Ваш статус:</div>
                    <div style="margin-left: 1.5%">
                        <div><c:out value="${sessionScope.user.role}"/></div>
                    </div>
                </div>

                <div style="margin-left: 4.5%; margin-top: 0.5%; margin-bottom: 0.5%">
                    <input type="hidden" name="command" value="MAKE_BET"/>
                    <input type="submit" value="Отправить"/>
                </div>
                <c:choose>
                    <c:when test="${sessionScope.user.role.role == 'bookmaker'}">
                        <%@include file="jspf/make_match.jspf" %>
                    </c:when>

                    <c:when test="${sessionScope.user.role.role == 'admin'}">
                        <%@include file="jspf/make_match.jspf" %>
                    </c:when>
                </c:choose>
            </div>
        </div>



        <%@include file="jspf/footer.jspf"%>


    </body>
</html>
