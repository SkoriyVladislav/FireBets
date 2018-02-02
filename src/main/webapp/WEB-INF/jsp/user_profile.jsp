<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 01.02.2018
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

            <div>
                <div class="user-info">
                    <div class="user-info-step">
                        <div style=" margin-left: -0.5%; font-size: 18px; ">Информаця о пользователе: <c:out value="${requestScope.user.name}"/> <c:out value="${requestScope.user.surname}"/> </div>
                    </div>

                    <div class="user-info-step" >
                        <div>Логин:</div>
                        <div style="margin-left: 1.5%">
                            <div ><c:out value="${requestScope.user.login}"/></div>
                        </div>
                    </div>

                    <div class="user-info-step" >
                        <div>Е-мейл:</div>
                        <div style="margin-left: 1.5%">
                            <div><c:out value="${requestScope.user.email}"/></div>
                        </div>
                    </div>

                    <div class="user-info-step" >
                        <div>Баланс:</div>
                        <div style="margin-left: 1.5%">
                            <div><c:out value="${requestScope.user.balance}"/></div>
                        </div>
                    </div>

                    <div class="user-info-step" >
                        <div>Статус:</div>
                        <div style="margin-left: 1.5%">
                            <div style="display: block" id="playerStatus"><c:out value="${requestScope.user.role}"/></div>
                            <div id="newPlayerStatus" style="display: none">
                                <span>
                                    <select name="status" class="select" >
                                        <option value="player">player</option>

                                        <option value="banned">banned</option>

                                        <option value="bookmaker">bookmaker</option>

                                        <option value="admin">admin</option>
                                    </select>
                                </span>
                                <div >
                                    <input type="button" name="Изменить">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div>
            <%@include file="jspf/footer.jspf"%>
        </div>

    </body>
</html>