package managers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionManager {

    final String exampleGet = "http://ip:port/handler?param=assaasdasd";
    final String examplePost = "https://selfsolve.apple.com/wcResults.do";

    private final String _host;
    private final String _port;

    private static ConnectionManager _connectionManager = null;

    public static ConnectionManager getConnectionManager(){
        if(_connectionManager == null){
            _connectionManager = new ConnectionManager("",""); //leerlos de un archivo de configuracion
        }
        return _connectionManager;
    }

    private ConnectionManager(final String host, final String port){
        _host = host;
        _port = port;
    }

    private URL createUrl(final String method) throws MalformedURLException {
        return new URL(_host + ":" + _port + "/" + method);
    }

    public String sendGet(final String apiMethod) {
        try{
            final URL obj = createUrl(apiMethod);
            final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            final int responseCode = con.getResponseCode();

            final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            final StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch(final Exception e){
            return e.toString();
        }

    }

    public String sendPost(final String apiMethod, final JsonObject jsonobject) {

        try{
            final URL obj = createUrl(apiMethod);
            final HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/json");

            final String urlParameters = jsonobject.toString();//"sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

            con.setDoOutput(true);
            final DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            final int responseCode = con.getResponseCode();

            final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            final StringBuffer response = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch(final Exception e){
            return e.toString();
        }
    }

}
