package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        /*
            final PrintWriter writer = response.getWriter();

            String htmlResponse = "<html>";
            htmlResponse += "<h2>Registrado \"" + username + "\"<br/>";
            htmlResponse += "El google ID es: \"" + googleId + "\"</h2>";
            htmlResponse += "</html>";

            writer.println(htmlResponse);
        */
    }

}