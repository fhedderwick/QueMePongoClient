<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 16/09/2019
  Time: 09:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Cliente Web de "Que Me Pongo"</title>
  </head>
  <body>
    Login
    <br>
    <form name="loginForm" method="post" action="loginServlet">
      Username: <input type="text" name="username"/> <br/>
      Password: <input type="password" name="password"/> <br/>
      <input type="submit" value="Login" />
    </form>
    </body>
</html>
