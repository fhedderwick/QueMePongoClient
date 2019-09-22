package managers;

import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionManager {

    final String exampleGet = "http://ip:port/handler?param=assaasdasd";
    final String examplePost = "https://selfsolve.apple.com/wcResults.do";

    private static String _host = "127.0.0.1";
    private static String _port = "8080";

    private static ConnectionManager _connectionManager = null;

    private static ConnectionManager getConnectionManager(){
        if(_connectionManager == null){
            _connectionManager = new ConnectionManager();
        }
        return _connectionManager;
    }

    private ConnectionManager(){}

    public static void setParams(final String host, final String port) {
        _host = host;
        _port = port;
    }

    private static URL createUrl(final String method, final String paramQuery) throws MalformedURLException {
        return new URL(_host + ":" + _port + "/" + method + paramQuery);
    }
    private static URL createUrl(final String method) throws MalformedURLException {
        return createUrl(method,"");
    }

    public static boolean testConnection(final String host, final String port){
        try {
            return !getConnectionManager().getRequest(new URL(host + ":" + port + "/test")).isEmpty();
        }catch(final Exception e){
            return false;
        }
    }
    public static String sendGet(final String apiMethod){
         try{
             return getConnectionManager().getRequest(createUrl(apiMethod,""));
         } catch(final Exception e){
             return e.toString();
         }
    }
    public static String sendGet(final String apiMethod, final Map<String,String> params){
        try{
            return getConnectionManager().getRequest(createUrl(apiMethod,createParamQuery(params)));
        } catch(final Exception e){
            return e.toString();
        }
    }
    public static String sendGet(final String apiMethod, final String paramQuery){
        try{
            return getConnectionManager().getRequest(createUrl(apiMethod,paramQuery));
        } catch(final Exception e){
            return e.toString();
        }
    }

    private static String createParamQuery(final Map<String, String> params) {
        final StringBuilder sb = new StringBuilder("?");
        for(final Map.Entry<String,String> entry : params.entrySet()){
            sb.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return sb.substring(0,sb.length()-1);
    }

    private String getRequest(final URL url) throws Exception{
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
    }

    public static String sendPost(final String apiMethod, final JsonObject jsonObject){
        try{
            return getConnectionManager().postRequest(createUrl(apiMethod,""),jsonObject);
        } catch(final Exception e){
            return e.toString();
        }
    }
    public static String sendPost(final String apiMethod, final JsonObject jsonObject, final Map<String,String> params){
        try{
            return getConnectionManager().postRequest(createUrl(apiMethod,createParamQuery(params)),jsonObject);
        } catch(final Exception e){
            return e.toString();
        }
    }
    public static String sendPost(final String apiMethod, final JsonObject jsonObject, final String paramQuery){
        try{
            return getConnectionManager().postRequest(createUrl(apiMethod,paramQuery),jsonObject);
        } catch(final Exception e){
            return e.toString();
        }
    }
    private String postRequest(final URL url, final JsonObject jsonObject) throws Exception {
        final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-type", "application/json");
        con.setRequestProperty("Content-Length", Integer.toString(jsonObject.toString().length()));
        con.setUseCaches( false );
        final DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonObject.toString());
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
    }

}
