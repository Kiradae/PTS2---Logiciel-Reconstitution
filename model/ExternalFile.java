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
				String content = array.item(i).getTextContent();

				String start = ((Element) array.item(i)).getAttribute("start").replace("\"", "");
				start = start.replace("start", "");
				String end = ((Element) array.item(i)).getAttribute("end").replace("\"", "");
				end = end.replace("end", "");

				load_exercice[k] = new Section(content, Float.valueOf(start), Float.valueOf(end));
				k++;
			}
		}

		return load_exercice;
	}

	public void saveTeacherFile(String path, String name, Section[] file) {
		System.out.println("En cours d'enregistrement de \"" + path + name + ".xml\"");
		try {
			FileWriter writer = new FileWriter(new File(path + name + ".xml"));
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<!DOCTYPE document SYSTEM \"Format_save.dtd\">\n");
			writer.write("<texte>\n");

			for (int i = 0; i < file.length; i++)
				writer.write("	<section start=\"" + file[i].getStart() + "\" end=\"" + file[i].getEnd() + "\">"
						+ file[i].getContent() + "</section>\n");

			writer.write("</texte>\n");
			writer.close();
			System.out.println("Document enregistré");
		} catch (IOException e) {
			System.out.println("Erreur, n'a pas pu enregistrer");
			e.printStackTrace();
		}
	}

}
