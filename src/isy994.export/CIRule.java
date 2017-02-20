
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CIRule {
    static private String sHeadXML =    "<Device id=\"0\" name=\"ISY994\" fixed=\"false\" shared=\"false\" type=\"Home Control\" vendor=\"Universal Devices\" model=\"isy994\">\n"+
            "<CodesType name=\"Global Cache Codes\"/>\n"+
            "<CodesType name=\"Hex Codes\"/>\n"+
            "<CodesType name=\"Network Codes\">\n"+
            "\t<Code name=\"Query All Nodes\">\n\t\trest/nodes\n\t</Code>\n"+
            "\t<Code name=\"Query Int Vars\">\n\t\trest/vars/definitions/1\n\t</Code>\n"+
            "\t<Code name=\"Query State Vars\">\n\t\trest/vars/definitions/2\n\t</Code>\n";

    static private String sTailXML =    "</CodesType>\n"+
            "<CodesType name=\"Database Codes\"/>\n"+
            "</Device>";

    static String BuildXML( String sNodeXML, String sProgramXML)
    {
        String sReturnXML = "";

		try {

		    sReturnXML = sHeadXML;
            sReturnXML += BuildNodeXML( sNodeXML );
            sReturnXML += BuildProgramXML( sProgramXML );
            sReturnXML += sTailXML;

        }
        catch (Exception e) {

            e.printStackTrace();

        }
        return sReturnXML;
    }
    private static String BuildNodeXML( String sXML ){

        String sReturnXML = "";

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader( sXML ));
            Document doc = dBuilder.parse(is);
            NodeList nList = doc.getElementsByTagName("node");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String sName     = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String sAddress  = eElement.getElementsByTagName("address").item(0).getTextContent();
                    String sCommand  = "";

                    if ( !sName.startsWith("ZW") &&
                            !sName.startsWith("Zone") &&
                            !sName.startsWith("Honeywell")) {
                        sCommand += "\t<Code name=\"" + sName +     " - On\">\n";
                        sCommand += "\t\trest/nodes/" + sAddress + "/cmd/DON\n";
                        sCommand += "\t</Code>\n";

                        sCommand += "\t<Code name=\"" + sName +     " - Off\">\n";
                        sCommand += "\t\trest/nodes/" + sAddress + "/cmd/DOF\n";
                        sCommand += "\t</Code>\n";

                        if ( sName.endsWith("Dim")) {

                            sCommand += "\t<Code name=\"" + sName + " - Bright\">\n";
                            sCommand += "\t\trest/nodes/" + sAddress + "/cmd/BRT\n";
                            sCommand += "\t</Code>\n";

                            sCommand += "\t<Code name=\"" + sName + " - Dim\">\n";
                            sCommand += "\t\trest/nodes/" + sAddress + "/cmd/DIM\n";
                            sCommand += "\t</Code>\n";
                        }
                    }
                    sReturnXML += sCommand;
                }
            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }
        return sReturnXML;
    }
    private static String BuildProgramXML( String sXML ){

        String sReturnXML = "";

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader( sXML ));
            Document doc = dBuilder.parse(is);
            NodeList nList = doc.getElementsByTagName("program");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement    = (Element) nNode;
                    String sName        = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String sProgramId   = eElement.getAttribute("id");
                    String sLastRun     = eElement.getElementsByTagName("lastRunTime").item(0).getTextContent();
                    String sCommand     = "";

                    //format /rest/programs/<pgm>/<cmd>
                    // <cmd> run|runThen|runElse|stop|enable|disable|enableRunAtStartup|disableRunAtStartup

                    if (!sLastRun.isEmpty()) {

                        sCommand += "\t<Code name=\"" + sName + " - RunIf\">\n";
                        sCommand += "\t\trest/programs/" + sProgramId + "/run\n";
                        sCommand += "\t</Code>\n";

                        sCommand += "\t<Code name=\"" + sName + " - RunThen\">\n";
                        sCommand += "\t\trest/programs/" + sProgramId + "/runThen\n";
                        sCommand += "\t</Code>\n";

                        sCommand += "\t<Code name=\"" + sName + " - RunElse\">\n";
                        sCommand += "\t\trest/programs/" + sProgramId + "/runThen\n";
                        sCommand += "\t</Code>\n";

                        sReturnXML += sCommand;
                    }
                }
            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }
        return sReturnXML;
    }
}
