package com.example.demo;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

@Component
public class PrepareArchetypeGenerateCommand {
	public static void main(String argv[]) {
		PrepareArchetypeGenerateCommand cmd=new PrepareArchetypeGenerateCommand();
		//cmd.prepareArchetypeGenerateCmd("tmo.ordermanagement","do-orders-test","com.tmobile.orders.test","1.0");
	}

	public String prepareArchetypeGenerateCmd(String groupId, String artifactId, String pkg, String version) {
		StringBuffer sb = new StringBuffer("mvn -B archetype:generate ");
		try {
			// creating a constructor of file class and parsing an XML file
			File file = new File("C:\\Users\\kmohamed\\.m2\\repository\\archetype-catalog.xml");
			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("archetype");
			// nodeList is not iterable, so we are using for loop
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				System.out.println("\nNode Name :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					sb.append(
							"-DarchetypeGroupId=" + eElement.getElementsByTagName("groupId").item(0).getTextContent());
					System.out.println("groupId: " + eElement.getElementsByTagName("groupId").item(0).getTextContent());

					sb.append(" -DarchetypeArtifactId="
							+ eElement.getElementsByTagName("artifactId").item(0).getTextContent());
					System.out.println(
							"artifactId: " + eElement.getElementsByTagName("artifactId").item(0).getTextContent());

					sb.append(
							" -DarchetypeVersion=" + eElement.getElementsByTagName("version").item(0).getTextContent());
					System.out.println("version: " + eElement.getElementsByTagName("version").item(0).getTextContent());
					
					sb.append(" -DgroupId="+groupId+" -DartifactId="+artifactId+" -Dpackage="+pkg+" -Dversion="+version);
					
					System.out.println("sb.toString():" + sb.toString());
					return sb.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void checkCloneProjectDirectory(String path) {
		File theDir = new File(path);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
	}
	
	public void checkCodeTemplateProjectDirectory(String path) {
		File theDir = new File(path);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
	}
}