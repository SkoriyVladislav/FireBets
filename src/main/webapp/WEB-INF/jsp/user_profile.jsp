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
        <script src="${pageContext.servletContext.contextPath}/web/lib/jquery-3.2.1.min.js"></script>
        <script>
            function disp(form, form2) {
                if (form.style.display == "none") {
                    form.style.display = "flex";
                    form2.style.display = "none";
                } else {
                    form.style.display = "none";
                    form2.style.display = "block";
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
                        <div style=" margin-left: -0.5%; font-size: 18px; "><fmt:message key="iUser"/> <c:out value="${requestScope.user.name}"/> <c:out value="${requestScope.user.surname}"/> </div>
                    </div>

                    <div class="user-info-step" >
                        <div><fmt:message key="login"/></div>
                        <div style="margin-left: 1.5%">
                            <div ><c:out value="${requestScope.user.login}"/></div>
                        </div>
                    </div>

                    <div class="user-info-step" >
                        <div>E-mail:</div>
                        <div style="margin-left: 1.5%">
                            <div><c:out value="${requestScope.user.email}"/></div>
                        </div>
                    </div>

                    <div class="user-info-step" >
                        <div><fmt:message key="balance"/></div>
                        <div style="margin-left: 1.5%">
                            <div><c:out value="${requestScope.user.balance}"/></div>
                        </div>
                    </div>

                    <div class="user-info-step" >
                        <div><fmt:message key="status"/></div>
                        <div style="margin-left: 1.5%">
                            <div style="display: block" id="playerStatus"><c:out value="${requestScope.user.role}"/></div>
                        </div>

                        <form id="newPlayerStatus" style="display: none; margin-left: 0.5%; margin-top: 1px" >
                             <div>
                                 <select name="status" class="select" >
                                     <option value="player">player</option>

                                     <option value="banned">banned</option>

                                     <option value="bookmaker">bookmaker</option>

                                     <option value="admin">admin</option>
                                 </select>
                             </div>

                             <div style="margin-left: 1.5%">
                                 <input type="hidden" name="login" value="${requestScope.user.login}"/>
                                 <input type="hidden" name="command" value="change_users_role"/>
                                 <input type="submit" value=<fmt:message key="change_bet.confirm"/>' >
                             </div>

                             <div style="margin-left: 1.5%">
                                 <input type="button" value='<fmt:message key="cancel"/>' onclick="disp(document.getElementById('changeBut'), document.getElementById('newPlayerStatus'))">
                             </div>

                        </form>

                        <div style="margin-left: 1.5%">
                            <input id="changeBut" type="button" value='<fmt:message key="change"/>' onclick="disp(document.getElementById('newPlayerStatus'), this)">
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