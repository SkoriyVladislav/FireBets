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
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleIndex.css" type="text/css">
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

        <div class="matches">
            <div class="match-info">
                <div style="margin-left: 1.5%">
                    Время
                </div>

                <div>
                    Событие
                </div>

                <div class="coeff">
                    <div style="margin-right: 1.5%;">
                        Победа 1
                    </div>

                    <div style="margin-left: 1.5%; margin-right: 1.5%">
                        Ничья
                    </div>

                    <div style="margin-left: 1.5%; margin-right: 1.5%">
                        Победа 2
                    </div>
                </div>
            </div>

            <c:forEach var="match" items="${sessionScope.matches}" >
                <div style="margin-left: 1.5%; margin-right: 1.5%" >
                    <div class="match" >
                        <div style="margin-left: 1.5%">
                            <div><c:out value="${match.time}"/></div>
                            <div><c:out value="${match.data}"/></div>
                        </div>

                        <div style="margin-left: -2.5%">
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_make_bet&match=${match.id}" class="link-block-match"><c:out value="${match.team1}"/> - <c:out value="${match.team2}"/></a>
                        </div>

                        <div class="coeff-info">
                            <div style="margin-right: 1.5%"><c:out value="${match.coefTeam1}"/></div>
                            <div style="margin-left: 1.5%; margin-right: 1.5%"><c:out value="${match.coefDraw}"/></div>
                            <div style="margin-right: 1.5%"><c:out value="${match.coefTeam2}"/></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <%@include file="WEB-INF/jsp/jspf/footer.jspf"%>
    </body>
</html>
