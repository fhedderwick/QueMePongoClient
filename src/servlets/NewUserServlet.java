package servlets;

import managers.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pages/newuser")
public class NewUserServlet extends HttpServlet {

    private final String apiMethod = "user";

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final String username = request.getParameter("username");
        final String googleId = request.getParameter("googleId");

        final JsonObject body = new JsonObject();
        body.addProperty("username",username);
        body.addProperty("googleId",googleId);

        final String reply = ConnectionManager.getConnectionManager().sendPost(apiMethod,body);
        final PrintWriter writer = response.getWriter();

        String htmlResponse = "<html>";
        htmlResponse += "<h2>Registrado \"" + username + "\"<br/>";
        htmlResponse += "El google ID es: \"" + googleId + "\"</h2>";
        htmlResponse += "</html>";

        writer.println(htmlResponse);

    }

}