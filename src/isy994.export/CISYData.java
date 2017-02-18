import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bbennett on 2/14/17.
 */
public class CISYData {
    public String getISYNodes ( String sAuth, String sURL){

        String sISYXML = "";

        try {

            URL url = new URL( sURL );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", sAuth);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sISYXML += output;
            }
            conn.disconnect();
        }
        catch (MalformedURLException e) {

            e.printStackTrace();

        }
        catch (IOException e) {

            e.printStackTrace();

        }
        return sISYXML;
    }
}
