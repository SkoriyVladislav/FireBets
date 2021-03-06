<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 01.12.2017
  Time: 16:06
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
                <div class="match-info">
                    <div style="margin-left: 1.5%">
                        <fmt:message key="timeMatch"/>
                    </div>

                    <div>
                        <fmt:message key="match"/>
                    </div>

                    <div class="coeff">
                        <span style="margin-right: 1.5%;">
                            <fmt:message key="change_bet.win"/> 1
                        </span>

                        <span style="margin-left: 1.5%; margin-right: 1.5%; ">
                            <fmt:message key="change_bet.draw"/>
                        </span>

                        <span style="margin-left: 1.5%; margin-right: 1.5%; ">
                            <fmt:message key="change_bet.win"/> 2
                        </span>
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
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_make_bet&match=${match.id}" class="link-block-match"><c:out value="${match.team1}"/> <strong> <c:out value=" ${match.goalsTeam1 != null ? match.goalsTeam1 : ''}"/> - <c:out value="${match.goalsTeam2 != null ? match.goalsTeam2 : ''} "/> </strong> <c:out value="${match.team2}"/></a>
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
        </div>

        <div>
            <%@include file="WEB-INF/jsp/jspf/footer.jspf"%>
        </div>
    </body>
</html>
