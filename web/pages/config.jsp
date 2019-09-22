<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>Cliente Web de "Que Me Pongo"</title>
  </head>
  <body>
    Configuración
    <br>
    <form name="configForm" method="post" action="config">
      IP del servidor "Qué Me Pongo": <input type="text" id="host" name="host"/> <br/>
      Puerto del servidor "Qué Me Pongo": <input type="text" id="port" name="port"/> <br/>
      <input type="button" id="testButton" onclick="test()" name="testConfig" value="Probar configuración" />
      <input type="submit" name="saveConfig" value="Guardar configuración" />
    </form>
  <div id="testing"></div>
  </body>
</html>

<script>
  function test(){
    var host = document.getElementById("host").value;
    var port = document.getElementById("port").value;
    if(host.length == 0 || port.length == 0){
      alert('Por favor, complete ambos campos.');
      return;
    }
    document.getElementById("testing").innerHTML = "Por favor espere, se esta probando la conexión.";
    document.getElementById("testButton").disabled = true;
    $.ajax({
      type: "POST",
      url: "config",
      data: {host:host,port:port},
      timeout: 3000,
      error: function(){
        document.getElementById("testing").innerHTML = "";
        document.getElementById("testButton").disabled = false;
        alert('No se pudo establecer la conexión.');
      },
      success: function(data) {
        document.getElementById("testing").innerHTML = "";
        document.getElementById("testButton").disabled = false;
        alert(data);
      }
    });
  };
</script>