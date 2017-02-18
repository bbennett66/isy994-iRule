/**
 *  Default comment
 */

/**
 * @author bbennett
 *
 */
public class IsyNodes {

	public static void main(String[] args) {
		  String sIRuleNodeXML;
		  CISYData cISYData = new CISYData();
		  String sAuth = "";

		  try {

		  	  	//node data
			  	String sNodeXML = cISYData.getISYNodes( sAuth, "http://192.168.0.4/rest/nodes" );
			  	//CFile.WriteXML("isy994_nodes.xml", sNodeXML);


   			  	//program data
			  	String sProgramXML = cISYData.getISYNodes( sAuth, "http://192.168.0.4/rest/programs?subfolders=true" );
			 	//CFile.WriteXML("isy994_programs.xml", sProgramXML );

			 	//Build iRuleXML
			  	sIRuleNodeXML = CIRule.BuildXML( sNodeXML, sProgramXML );
			  	CFile.WriteXML("iRule_isy994_nodes.xml",sIRuleNodeXML );

		  }
		  catch (Exception e) {

			e.printStackTrace();

		  }
	}
}
