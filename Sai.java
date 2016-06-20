import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Sai {

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
	
	String start ="["  ;
	String end = "]," + "\n" ;
	File file = new File("test1.txt");
    FileWriter fileWriter = new FileWriter(file);
		  

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	    Element eElement = (Element) nNode;
			
		latitude =   	 eElement.getElementsByTagName("latitude").item(0).getTextContent() ;
		longitude =   	 eElement.getElementsByTagName("longitude").item(0).getTextContent();
		TollName =       "\""+ eElement.getElementsByTagName("TollName").item(0).getTextContent() + "\"";
		 
		 
		
	
		String fin = start + TollName +","+ latitude+"," +longitude + end ;
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