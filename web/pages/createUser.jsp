<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<SCRIPT LANGUAGE="JavaScript">
  <!--
  function button1()
  {
    document.form1.buttonName.value = "yes";
    form1.submit();
  }
  // -->
</SCRIPT>
<html>
  <head>
    <title>Cliente Web de "Que Me Pongo"</title>
  </head>
  <body>
    Crear un usuario
    <br>
    <form name="newuserForm" method="post" action="newuser">
      Username: <input type="text" name="username"/> <br/>
      Google ID: <input type="text" name="googleId"/> <br/>
      <input type="submit" value="Registrar" />
    </form>
  </body>
</html>
