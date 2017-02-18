#**Export isy994 data to iRule Format**
##
#Initial Setup
Create a directory under the project called auth.  
Create a file called isyAuthToken.   
Place a basic auth code on the top line.  

#Nodes
Exports the nodes under /rest/nodes

Filters - ZW, Zone, and Honeywell prefixes

    if ( !sName.startsWith("ZW") &&
        !sName.startsWith("Zone") &&
            !sName.startsWith("Honeywell")) 
            
Creates On and Off by default.  If Name ends with Dim, adds Bright and Dim

	<Code name="FrontChanDim - On">
	    rest/nodes/ZW005_1/cmd/DON
	</Code>
	<Code name="FrontChanDim - Off">
	    rest/nodes/ZW005_1/cmd/DOF
	</Code>
	<Code name="FrontChanDim - Bright">
	    rest/nodes/ZW005_1/cmd/BRT
	</Code>
	<Code name="FrontChanDim - Dim">
	    rest/nodes/ZW005_1/cmd/DIM
	</Code>

#Programs
Filters out any program that have never been run.  Creates Run, RunThen, RunElse

	<Code name="Sunrise - RunIf">
	    rest/programs/0006/run
	</Code>
	<Code name="Sunrise - RunThen">
	    rest/programs/0006/runThen
	</Code>
	<Code name="Sunrise - runElse">
	    rest/programs/0006/runThen
	</Code>
	

