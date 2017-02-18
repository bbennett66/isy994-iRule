import java.io.IOException;
import java.io.PrintWriter;

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
}
