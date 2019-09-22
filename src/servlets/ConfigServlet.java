package servlets;

import managers.ConnectionManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pages/config")
public class ConfigServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // read form fields
        final String host = request.getParameter("host");
        final String port = request.getParameter("port");
        final boolean saveConfig = request.getParameter("saveConfig") != null;

        final String htmlResponse = saveConfig ? save(host,port) : test(host,port);
        response.getWriter().println(htmlResponse);
    }

    private String save(final String host, final String port){
        ConnectionManager.setParams(host,port);
        String htmlResponse = "<html>";
        htmlResponse += "<h2>Se guardó la configuración<br/>";
        htmlResponse += "La URL de acceso al servidor \"Qué Me Pongo\" es: " + host + ":" + port + "</h2>";
        htmlResponse += "</html>";
        return htmlResponse;
    }

    private String test(final String host, final String port){
        return ConnectionManager.testConnection(host,port) ? "Test exitoso" : "Test fallido";
    }

}