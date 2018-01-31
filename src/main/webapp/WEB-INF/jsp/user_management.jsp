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

            <div class="matches" id="matches">
                <input type="text" name="login" class="inputPole" value="" placeholder=" " required id="ajLogin" onchange="ajaxreq()"/>
                <span id="responseLoginSpan" style="margin-left: 10px;"></span>

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
                    success: [function(responseJson) {    // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
                        var $table = $("<table width=\"100%\" border=\"0\" align=\"start\" id=\"results\">").appendTo($("#matches")); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
                        $("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
                            .append($("<td>").text('Логин'))       // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
                            .append($("<td>").text('Имя'))      // Create HTML <td> element, set its text content with name of currently iterated product and append it to the <tr>.
                            .append($("<td>").text('Фамилия'))
                            .append($("<td>").text('Статус'))
                            .append($("<td>").text('Баланс'));
                        $.each(responseJson, function(index, product) {    // Iterate over the JSON array.
                            $("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
                                .append($("<td>").text(product.login))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
                                .append($("<td>").text(product.name))      // Create HTML <td> element, set its text content with name of currently iterated product and append it to the <tr>.
                                .append($("<td>").text(product.surname))
                                .append($("<td>").text(product.role))
                                .append($("<td>").text(product.balance));    // Create HTML <td> element, set its text content with price of currently iterated product and append it to the <tr>.
                        });
                    }]
                });
            }
        </script>
    </body>
</html>