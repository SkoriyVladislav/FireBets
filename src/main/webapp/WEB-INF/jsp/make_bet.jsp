<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 22.01.2018
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.pagecontent"/>
<html>
    <head>
        <title>Make Bet</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleMakeBet.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Dosis:400,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
        <script src="${pageContext.servletContext.contextPath}/web/lib/jquery-3.2.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    </head>

    <body>
        <div>
            <div class="top">
                <div class="logo-top">
                    FireBets
                </div>

                <%@include file="jspf/user_profile.jspf" %>
            </div>

            <%@include file="jspf/header.jspf"%>


            <div class="matches">
                <form action="controller" method="post" style="width: 100%" class="form0">
                    <div>
                        <div class="match">
                            <div><fmt:message key="match"/></div>
                            <div style="margin-left: 1.5%"><c:out value="${sessionScope.match.team1}"/> <strong> <c:out value=" ${sessionScope.match.goalsTeam1 != null ? sessionScope.match.goalsTeam1 : ''}"/>  -  <c:out value=" ${sessionScope.match.goalsTeam2 != null ? sessionScope.match.goalsTeam2 : ''}"/> </strong> <c:out value="${sessionScope.match.team2}"/> </div>
                        </div>

                        <div class="match" >
                            <div><fmt:message key="tMatch"/></div>
                            <div style="margin-left: 1.5%">
                                <div><c:out value="${sessionScope.match.time}"/></div>
                                <div><c:out value="${sessionScope.match.data}"/></div>
                            </div>
                        </div>


                            <c:choose>
                                <c:when test="${requestScope.bet == null}">
                                    <%@include file="jspf/make_bets.jspf" %>
                                </c:when>

                                <c:otherwise>
                                    <%@include file="jspf/my_bet.jspf" %>
                                </c:otherwise>
                            </c:choose>

                    </div>
                </form>

                <c:choose>
                    <c:when test="${sessionScope.user.role.role == 'bookmaker'}">
                        <%@include file="jspf/change_coeff.jspf" %>
                        <%@include file="jspf/set_results.jspf" %>
                    </c:when>
                </c:choose>

                <div >
                    <span id="resultCheckTime"></span>
                </div>
            </div>
        </div>

        <div>
            <%@include file="jspf/footer.jspf"%>
        </div>



        <script>
            function ajaxreqReq(form) {
                var data = {"matchId":"${sessionScope.match.id}", "command":"CHECK_TIME_AJAX"};

                $.ajax({
                    type: "POST",
                    data: data,
                    url: 'ajax_controller',
                    success: function(serverData) {
                        if (serverData.serverInfo === "true") {
                            form.submit();
                        } else {
                            $("#resultCheckTime").text('<fmt:message key="tBet"/>');
                        }
                    }
                });
            }

            function confirmSubm(form) {
                if (confirm('<fmt:message key="change_bet.confirm"/>')) {
                    ajaxreqReq(form);
                }
                else
                    return false;
            }
        </script>
    </body>
</html>
