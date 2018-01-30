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

                        <div class="match">
                            <div>Выберите тип ставки:</div>

                            <div class="coeff-info">
                                <div class="coeff" >
                                    <div>Победа <c:out value="${sessionScope.match.team1}"/>: </div>
                                    <div class="coeff-val" >
                                        Коэфф: <c:out value="${sessionScope.match.coefTeam1}"/>
                                    </div>
                                </div>
                                <div>
                                    <input type="radio" name="betType" value="team1" onclick="agreeForm(document.getElementById('form1'))" required>
                                </div>

                                <div class="coeff" >
                                    <div>Ничья: </div>
                                    <div class="coeff-val" >
                                        Коэфф: <c:out value="${sessionScope.match.coefDraw}"/>
                                    </div>
                                </div>
                                <div>
                                    <input type="radio" name="betType" value="draw" onclick="agreeForm(document.getElementById('form1'))" required>
                                </div>

                                <div class="coeff" >
                                    <div>Победа <c:out value="${sessionScope.match.team2}"/>: </div>
                                    <div class="coeff-val" >
                                        Коэфф: <c:out value="${sessionScope.match.coefTeam2}"/>
                                    </div>
                                </div>
                                <div>
                                    <input type="radio" name="betType" value="team2" onclick="agreeForm(document.getElementById('form1'))" required >
                                </div>

                                <div class="coeff" >
                                    <div>Точный счёт: </div>
                                    <div class="coeff-val">
                                        Коэфф: <c:out value="${sessionScope.match.coefExAcc}"/>
                                    </div>
                                </div>
                                <div class="ExAcc" >
                                    <input id="form1" type="radio" name="betType" value="exAcc" onclick="agreeForm(this)" required>
                                </div>

                                <div id="div1" class="ExAccVal">Голы <c:out value="${sessionScope.match.team1}"/></div>
                                <div>
                                    <input id="div3" class="exAccVal" type="text" name="exAccVal1" value="" size="1" pattern="^[0-9]?([0-9]+)?$" />
                                </div>

                                <div id="div2" class="ExAccVal"> : Голы <c:out value="${sessionScope.match.team2}"/></div>
                                <div>
                                    <input id="div4" class="ExAccVal" type="text" name="exAccVal2" value="" size="1" pattern="^[0-9]?([0-9]+)?$" />
                                </div>
                            </div>
                        </div>

                        <div class="match">
                            <div>Введите размер ставки: </div>
                            <div style="margin-left: 1.5%">
                                <input id="div5" class="betVal" type="text" name="betVal" value="" size="1" pattern="^[0-9]([0-9]+)?$" required/>
                            </div>
                        </div>

                        <div style="margin-left: 4.5%; margin-top: 0.5%; margin-bottom: 0.5%">
                            <input type="hidden" name="command" value="MAKE_BET"/>
                            <input type="submit" value="Отправить"/>
                        </div>

                    </div>
                </div>
            </form>
        </div>

        <div>
            <%@include file="jspf/footer.jspf"%>
        </div>
    </body>
</html>
