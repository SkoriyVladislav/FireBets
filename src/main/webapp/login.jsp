<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 21.01.2018
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'default'}"/>
<fmt:setBundle basename="textcontent.pagecontent"/>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleLogin.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Dosis:400,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
    </head>

    <body>
        <div>
            <div class="top">
                <div class="logo-top">
                    FireBets
                </div>
            </div>

            <%@include file="WEB-INF/jsp/jspf/header.jspf"%>

            <div class="login">
                <form action="controller" method="post" >
                    <div class="login-step">
                        <h2 class="massageH2">Пожалуйста авторизуйтесь.</h2>
                    </div>

                    <div class="login-step">
                        <h3 class="massage"><fmt:message key="yLogin"/> : </h3>
                        <input type="text" name="login" placeholder=' <fmt:message key="login"/>' value="" required/>
                    </div>

                    <div class="login-step">
                        <h3 class="massage"><fmt:message key="password"/> : </h3>
                        <input type="password" name="password" class="formlogin" placeholder='<fmt:message key="password"/>' autocomplete="off" value="" required/>
                    </div>

                    <div class="login-step" style="margin-left: 10%">
                        <input type="hidden" name="command" value="login"/>
                        <input type="hidden" name="from" value="${param.from != null ? fn:escapeXml(param.from) : null}" />
                        <input id="enter" type="submit" value="<fmt:message key="enter"/>" style="margin-left: 1.5%"/>
                        <span>${requestScope.errorMsg}</span>
                    </div>

                    <div class="remember-pass" style="margin-left: 7.5%">
                        <a href="#" class="link-block-remember-pass text-center"> <fmt:message key="fPassword"/> </a>
                    </div>

                    <div class="input-left input-registr" style="margin-left: 3%">
                        <h3 class="massageH2"><fmt:message key="account"/> </h3>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_registration" class="link-block-registration"><fmt:message key="registration"/> </a>
                    </div>
                </form>
            </div>
        </div>

        <div>
            <%@include file="WEB-INF/jsp/jspf/footer.jspf"%>
        </div>

    </body>
</html>
