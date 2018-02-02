<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 02.02.2018
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="margin-left: 1.5%">
    Ваша ставка: <c:out value="${requestScope.bet.size}"/>
</div>
<div class="match" style="margin-bottom: 5px; margin-top: 0">
    <div style="margin-left: 1.5%">Тип ставки: <c:out value="${requestScope.bet.type}"/></div>

    <div class="coeff-info">
        <div class="coeff">
            <div>
                <c:choose>
                    <c:when test="${requestScope.bet.type.type == 'team 1'}">
                        Коэфф: <c:out value="${sessionScope.match.coefTeam1}"/>
                    </c:when>

                    <c:when test="${requestScope.bet.type.type == 'team 2'}">
                        Коэфф: <c:out value="${sessionScope.match.coefTeam2}"/>
                    </c:when>

                    <c:when test="${requestScope.bet.type.type == 'draw'}">
                        Коэфф: <c:out value="${sessionScope.match.coefDraw}"/>
                    </c:when>

                    <c:when test="${requestScope.bet.type.type == 'exact score'}">
                        <span>
                            Счёт: <c:out value="${requestScope.bet.goalsTeam1}"/> <c:out value="${requestScope.bet.goalsTeam2}"/>
                        </span><br/>

                        <span>
                            Коэфф: <c:out value="${sessionScope.match.coefExAcc}"/>
                        </span>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>
