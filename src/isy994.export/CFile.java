import java.io.*;

/**
 * Created by Berlin on 2/18/2017.
 */
public class CFile {
    static void WriteXML( String sFileName, String sInput )
    {
        try {

            PrintWriter writer = new PrintWriter("./xml/" + sFileName, "UTF-8");
            writer.print( sInput );
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    static String GetAuthToken ( String sFileName ) {
        String sReturnToken = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(sFileName));
            sReturnToken = br.readLine();
            br.close();
        }
        catch (IOException e) {
            sReturnToken = "INVALID";
        }
        return sReturnToken;
    }
}
