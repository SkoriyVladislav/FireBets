<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 01.12.2017
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Bets</title>
  </head>

  <body>
    <form action="controller" method="post" >
      Enter login:<br/>
      <input type="text" name="login" value="" size="30" /><br /> <br />
      Enter password:<br/>
      <input type="text" name="password" value="" size="30" /><br /> <br />
      <input type="submit" value="Enter" /><br />
    </form>
    <a href="${pageContext.request.contextPath}/controller?command=go_to_registration">Go to Registration!</a><br>
</body>
</html>
