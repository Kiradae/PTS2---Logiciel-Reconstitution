package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class ExternalFile {

	public ExternalFile() {
	}

	private Document openXml(String path) {
		Document reader = null;
		System.out.println("Fichier \"" + path + "\" en cour de chargement");

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			reader = builder.parse(path);
			reader.getDocumentElement().normalize();

			System.out.println("Chargement réussi");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reader;
	}

	public Project loadTeacherFile(String path) {
		Section[] load_exercice;
		Document reader = openXml(path);
		
		NodeList array = reader.getElementsByTagName("section");
		load_exercice = new Section[array.getLength()];
		int k = 0;
		String titre = reader.getElementsByTagName("titre").item(0).getTextContent();
		String consigne = reader.getElementsByTagName("consigne").item(0).getTextContent();
		String mode = reader.getElementsByTagName("mode").item(0).getTextContent();
		for (int i = 0; i < array.getLength(); i++) {
			if (array.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element node = (Element) array.item(i);
				
				String start = node.getAttribute("start").replace("\"", "");
				start = start.replace("start", "");
				String end = node.getAttribute("end").replace("\"", "");
				end = end.replace("end", "");
				String content = node.getElementsByTagName("content").item(0).getTextContent();
				String help = node.getElementsByTagName("help").item(0).getTextContent();

				load_exercice[k] = new Section(Float.valueOf(start), Float.valueOf(end), content, help);
				k++;
			}
		}

		return new Project(load_exercice, titre, consigne, mode);
	}

}
