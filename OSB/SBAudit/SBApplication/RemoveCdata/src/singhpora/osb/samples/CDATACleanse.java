package singhpora.osb.samples;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.stream.Collectors;

public class CDATACleanse {
    public CDATACleanse() {
        super();
    }
    
    public static String cleanseCDATA(String serializedXML) {
        CharSequence charsCDATAStart = "&lt;![CDATA[";
        CharSequence charsCDATAEnd = "]]>";
        if(serializedXML != null)
            return serializedXML.replace(charsCDATAStart, "").replace(charsCDATAEnd, "");
        return 
            "<blank/>";
    }
    
    public static void main(String[] args) throws Exception{
        
        File file = new File("B:\\tmp\\SBFlows\\Error\\cdata_message.xml");
        String serializedXML 
            = Files.lines(file.toPath()).collect(Collectors.joining("\n"));
        System.out.println(">>>>>>    "+cleanseCDATA(serializedXML));

    }
}
