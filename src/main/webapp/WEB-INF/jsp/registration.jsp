<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 17.12.2017
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.pagecontent"/>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
        <script src="${pageContext.servletContext.contextPath}/web/js/jsScripts.js"></script>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleRegistr.css" type="text/css">
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
                    <%@include file="jspf/user_profile.jspf" %>
                </c:when>

                <c:otherwise>
                    <%@include file="jspf/user_login.jspf" %>
                </c:otherwise>
            </c:choose>
        </div>

        <%@include file="jspf/header.jspf"%>

        <div class="head-title">
            <div class="middle">
                <h1>Регистрация: </h1>
                <span class="regInfoSpan">Все поля формы являются обязательными к заполнению.</span>
            </div>
        </div>


        <div class="registerSteps">

            <form onsubmit="return validateForm()" name="reg-form" action="controller" method="get">
                <div class="label">Логин:</div>
                <div class="field">
                    <input type="text" name="login" class="inputPole" value="" placeholder=" " required />

                </div>

                <div class="label">Имя:</div>
                <div class="field-fname field">
                    <input type="text" name="name" class="inputPole" value="" placeholder=" " pattern="^([A-Z])+([a-z])+$" required /> <span></span>

                </div>

                <div class="label">Фамилия:</div>
                <div class="field field-fname field-sname">
                    <input type="text" name="surname" class="inputPole" value="" placeholder=" " pattern="^([A-Z])+([a-z])+$" required /> <span></span>
                </div>

                <div class="label">Номер телефона:</div>

                <div class="field field-fname field-phone">
                    <input type="text" name="phone" placeholder="+(*) (**) *** ** **" autocomplete="off" value="" pattern="^[\\+][0-9]{11}([0-9]+)?$" class="inputPole" required/> <span></span>
                    <div class="errorMsg" id="phoneError"></div>
                </div>

                <div class="label">E-mail:</div>
                <div class="field field-fname field-email">
                    <input type="email" name="email" class="inputPole" placeholder=" " value="" maxlength="45" pattern=".+@.+\..+" required/> <span></span>
                    <div class="errorMsg" id="emailError"></div>
                </div>


                <div class="">
                    <div class="label">Пароль:</div>
                    <div class="field field-fname field-pass">
                        <input type="password" name="pwd1" class="inputPole" placeholder=" " required pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" id="err-pwd1"> <span></span>
                    </div>
                </div>

                <div class="overflow">
                    <div class="label">Подтвердите пароль:</div>
                    <div class="field">
                        <input type="password" name="pwd2" class="inputPole" > <span class="err" id="err-pwd2"></span>
                    </div>
                </div>


                <div class="but">
                    <input type="hidden" name="command" value="registration"/>
                    <input type="submit" value="Зарегистрироваться">
                </div>
            </form>

        </div>

        <%@include file="jspf/footer.jspf"%>

    </body>
</html>
