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

            <form action="controller" method="post">
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

                        <c:choose>
                            <c:when test="${requestScope.bet == null}">
                                <%@include file="jspf/make_bets.jspf" %>
                            </c:when>

                            <c:otherwise>
                                <%@include file="jspf/my_bet.jsp" %>
                            </c:otherwise>
                        </c:choose>


                    </div>
                </div>
            </form>
        </div>

        <div>
            <%@include file="jspf/footer.jspf"%>
        </div>



        <script>
            function ajaxreq() {
                var data = {"betVal":$("#div5").val(), "command":"CHECK_SIZE_BETS_AJAX"};

                $.ajax({
                    type: "POST",
                    data: data,
                    url: 'ajax_controller',
                    success: [function(serverData) { //Если запрос удачен
                        if (serverData.serverInfo === "true") {
                            $("#responseBetValSpan").text("");
                            $("#makeBetButton").prop('disabled', false);
                        } else {
                            $("#responseBetValSpan").text("Недостаточно средств для ставки");
                            $("#makeBetButton").prop('disabled', true);
                        }
                    }]
                });
            }
        </script>


        <script>
            function agreeForm(box) {
                // Если поставлен флажок, снимаем блокирование кнопки
                var vis = (box.checked) ? "block" : "none";
                var req = (box.checked) ? "true" : "false";
                // В противном случае вновь блокируем кнопку
                document.getElementById('div1').style.display = vis;
                document.getElementById('div2').style.display = vis;
                document.getElementById('div3').style.display = vis;
                document.getElementById('div4').style.display = vis;

                if (req == "true") {
                    document.getElementById('div3').setAttribute('required', "true");
                    document.getElementById('div4').setAttribute('required', "true");
                } else {
                    document.getElementById('div3').removeAttribute('required');
                    document.getElementById('div3').removeAttribute('required');
                }
            }
        </script>
    </body>
</html>
