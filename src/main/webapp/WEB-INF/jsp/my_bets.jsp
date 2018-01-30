<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 29.01.2018
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.pagecontent"/>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Мои ставки</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleMain.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Dosis:400,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
    </head>

    <body>
        <div class="upfuter">
            <div class="top">
                <div class="logo-top">
                    FireBets
                </div>
                <%@include file="jspf/user_profile.jspf" %>
            </div>

            <%@include file="jspf/header.jspf"%>

            <div class="matches">
                <c:forEach var="matchBetMap" items="${sessionScope.matchBetMap}" >
                    <div style="margin-left: 1.5%; margin-right: 1.5%" >
                        <div class="match" >
                            <div style="margin-left: 1.5%">
                                <div><a href="${pageContext.request.contextPath}/controller?command=go_to_make_bet&match=${matchBetMap.key.id}" class="link-block-match"><c:out value="${matchBetMap.key.team1}"/> <c:out value=" ${matchBetMap.key.goalsTeam1 != null ? matchBetMap.key.goalsTeam1 : ''}"/> - <c:out value=" ${matchBetMap.key.goalsTeam2 != null ? matchBetMap.key.goalsTeam2 : ''}"/> <c:out value="${matchBetMap.key.team2}"/> </a></div>
                            </div>

                            <div >
                                <div style="margin-right: 1.5%"><c:out value="${matchBetMap.value.status}"/></div>
                            </div>

                            <div >
                                <div style="margin-right: 1.5%"><c:out value="${matchBetMap.value.type.type}"/></div>
                            </div>

                            <div >
                                <div style="margin-right: 1.5%"><c:out value="${matchBetMap.value.size}"/></div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>

        <div class="downfuter">
            <%@include file="jspf/footer.jspf"%>
        </div>

    </body>
</html>
