<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 29.01.2018
  Time: 0:54
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
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/web/lib/jquery-3.2.1.min.js"></script>
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

            <div id="matches" class="matches">
                <div> <fmt:message key="eData"/>
                    <input style="margin-left: 1%" type="text" name="login" class="inputPole" value="" placeholder=" " required id="ajLogin" onchange="ajaxreq()"/>
                </div>


            </div>
        </div>

        <div class="downfuter">
            <%@include file="jspf/footer.jspf"%>
        </div>

        <script>
            function ajaxreq() {
                var data = {"criteria":$("#ajLogin").val(), "command":"GET_USERS_AJAX"};

                $.ajax({
                    type: "POST",
                    data: data,
                    url: 'ajax_controller',
                    success: function(responseJson) {
                        $('#results').remove();
                        var $table = $("<table width=\"100%\" border=\"0\" align=\"start\" id=\"results\">").appendTo($("#matches"));
                        $("<tr>").appendTo($table)
                            .append($("<td>").text('Логин'))
                            .append($("<td>").text('Имя'))
                            .append($("<td>").text('Фамилия'))
                            .append($("<td>").text('Статус'))
                            .append($("<td>").text('Баланс'));
                        $.each(responseJson, function(index, product) {
                            $("<tr>").appendTo($table)
                                .append($("<td>").html("<a class='link-block-registration link-block-in-table' href='${pageContext.request.contextPath}/controller?command=go_to_user_profile&login=" + product.login + "'>" + product.login + "</a>")) // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
                                .append($("<td>").text(product.name))
                                .append($("<td>").text(product.surname))
                                .append($("<td>").text(product.role))
                                .append($("<td>").text(product.balance));
                                $("<a>").attr("href", product.login).appendTo("#loginTd")
                        });
                    }
                });
            }
        </script>
    </body>
</html>