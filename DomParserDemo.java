import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class DomParserDemo {

  public static void main(String argv[]) {

    try {

	File fXmlFile =  new File("toll_Total.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("TollPlazaInfo");
	String TollPlazaID ="";
	String latitude ="";
	String longitude ="";
	String TollName ="";
	String SearchLoc ="";
	String HtmlPopup ="";
	String[] a ={};
	
	String start ="{" + "\n" ;
	String end = "}," + "\n" ;
	File file = new File("test1.json");
    FileWriter fileWriter = new FileWriter(file);
		  

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	    Element eElement = (Element) nNode;
			
		TollPlazaID =  	"\"TollPlazaID\" : "   + "\""+ eElement.getElementsByTagName("TollPlazaID").item(0).getTextContent() + "\""  +"\n";
		latitude =   	"\"Latitude\" : " + "\""+ eElement.getElementsByTagName("latitude").item(0).getTextContent() + "\""+"\n";
		longitude =   	"\"Longitude\" : " + "\""+ eElement.getElementsByTagName("longitude").item(0).getTextContent() + "\""+"\n";
		TollName =      "\"TollName\" : " + "\""+ eElement.getElementsByTagName("TollName").item(0).getTextContent() + "\""+"\n";
		SearchLoc =  	"\"SearchLoc\" : " + "\""+ eElement.getElementsByTagName("SearchLoc").item(0).getTextContent() + "\""+"\n";
		
		a =  eElement.getElementsByTagName("HtmlPopup").item(0).getTextContent().split("table");
		String aa = a[1].replace("cellpadding='5' class='tollinfotbl' cellspacing='0' width='100%' >" , "<table>");
		String b  = aa.substring(0, aa.lastIndexOf("</")) + "</table>";
		
		HtmlPopup =  	"\"Table\" : " + "\""+ b  + "\""+"\n";
		
		String row = "Record No :" + temp + "\n";
		String fin = start + TollPlazaID + latitude +longitude + TollName + SearchLoc + HtmlPopup + end ;
        fileWriter.write(fin);
		
		}
	}
	//System.out.println(a[0]);
    fileWriter.flush();
    fileWriter.close();
    } 
	catch (Exception e) {
	e.printStackTrace();
    }
  }

}