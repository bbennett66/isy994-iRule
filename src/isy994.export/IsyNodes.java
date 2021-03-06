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

		  try {

		  		String sAuth = CFile.GetAuthToken( "./auth/isyAuthToken");

		  	  	//node data
			  	String sNodeXML = cISYData.getISYData( sAuth, "http://192.168.0.4/rest/nodes" );
			  	//CFile.WriteXML("isy994_nodes.xml", sNodeXML);

   			  	//program data
			  	String sProgramXML = cISYData.getISYData( sAuth, "http://192.168.0.4/rest/programs?subfolders=true" );
			 	//CFile.WriteXML("isy994_programs.xml", sProgramXML );

			  	//int vars
			  	String sStateVarsXML = cISYData.getISYData( sAuth, "http://192.168.0.4/rest/vars/definitions/1" );
			  	CFile.WriteXML("isy994_int_vars.xml", sStateVarsXML );

			 	 //state vars
			  	String sIntVarsXML = cISYData.getISYData( sAuth, "http://192.168.0.4/rest/vars/definitions/2" );
			  	CFile.WriteXML("isy994_state_vars.xml", sIntVarsXML );

			 	//Build iRuleXML
			  	sIRuleNodeXML = CIRule.BuildXML( sNodeXML, sProgramXML );
			  	CFile.WriteXML("iRule_isy994_nodes.xml",sIRuleNodeXML );

		  }
		  catch (Exception e) {

			e.printStackTrace();

		  }
	}
}
