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
		System.out.println("Fichier \"" + path + "\" en cour de chargement ...");

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			reader = builder.parse(new File(path));
			reader.getDocumentElement().normalize();

			System.out.println("Chargement réussi");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reader;
	}

	public Section[] loadTeacherFile(String path) {
		Section[] load_exercice;
		Document reader = openXml(path);

		NodeList array = reader.getElementsByTagName("section");
		load_exercice = new Section[array.getLength()];
		int k = 0;
		for (int i = 0; i < array.getLength(); i++) {
			if (array.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element node = (Element) array.item(i);
				
				String start = node.getAttribute("start").replace("\"", "");
				start = start.replace("start", "");
				String end = node.getAttribute("end").replace("\"", "");
				end = end.replace("end", "");
				String content = node.getTextContent();

				load_exercice[k] = new Section(Float.valueOf(start), Float.valueOf(end), content);
				k++;
			}
		}

		return load_exercice;
	}

}
