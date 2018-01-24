<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 22.01.2018
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Make Bet</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleMakeBet.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Dosis:400,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
    </head>

    <body>
        <div class="top">
            <div class="logo-top">
                FireBets
            </div>

                <%@include file="jspf/user_profile.jspf" %>
        </div>

        <%@include file="jspf/header.jspf"%>

        <form action="controller" method="get">
            <div>
                <div class="matches">
                    <div class="match">
                        <div>Матч:</div>
                        <div style="margin-left: 1.5%"><c:out value="${sessionScope.match.team1}"/>  -  <c:out value="${sessionScope.match.team2}"/> </div>
                    </div>

                    <div class="match" >
                        <div>Время:</div>
                        <div style="margin-left: 1.5%">
                            <div><c:out value="${sessionScope.match.time}"/></div>
                            <div><c:out value="${sessionScope.match.data}"/></div>
                        </div>
                    </div>

                    <div class="match">
                        <div>Выберите тип ставки:</div>

                        <div class="coeff-info">
                            <div class="coeff" >
                                <div>Победа <c:out value="${sessionScope.match.team1}"/>: </div>
                                <div class="coeff-val" >
                                    Коэфф: <c:out value="${sessionScope.match.coefTeam1}"/>
                                    <input type="radio" name="type" value="team1">
                                </div>
                            </div>

                            <div class="coeff" >
                                <div>Ничья: </div>
                                <div class="coeff-val" >
                                    Коэфф: <c:out value="${sessionScope.match.coefDraw}"/>
                                    <input type="radio" name="type" value="draw">
                                </div>
                            </div>

                            <div class="coeff" >
                                <div>Победа <c:out value="${sessionScope.match.team2}"/>: </div>
                                <div class="coeff-val" >
                                    Коэфф: <c:out value="${sessionScope.match.coefTeam2}"/>
                                    <input type="radio" name="type" value="team2">
                                </div>
                            </div>

                            <div class="coeff" >
                                <div>Точный счёт: </div>
                                <div class="coeff-val">
                                    Коэфф: <c:out value="${sessionScope.match.coefExAcc}"/>
                                    <input type="radio" name="type" value="exAcc">
                                </div>

                                <div>Голы <c:out value="${sessionScope.match.team1}"/></div>
                                <div>
                                    <input type="text" name="size" value="" size="1"/>
                                </div>

                                <div> : Голы <c:out value="${sessionScope.match.team2}"/></div>
                                <div>
                                    <input type="text" name="size" value="" size="1"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="match">
                        <div>Введите размер ставки: </div>
                        <div style="margin-left: 1.5%">
                            <input type="text" name="size" value="" size="10"/>
                        </div>
                    </div>

                    <div style="margin-left: 4.5%; margin-top: 0.5%; margin-bottom: 0.5%">
                        <input type="hidden" name="match" value="${sessionScope.match.id}"/>
                        <input type="hidden" name="command" value="make_bet"/>
                        <input type="submit" value="Отправить"/>
                    </div>

                </div>
            </div>
        </form>

        <%@include file="jspf/footer.jspf"%>
    </body>
</html>
