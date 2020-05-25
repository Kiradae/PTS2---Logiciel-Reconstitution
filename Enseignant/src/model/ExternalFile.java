package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ExternalFile {

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

	public void saveTeacherFile(String path, String name, Section[] file) {
		System.out.println("En cours d'enregistrement de \"" + path + name + ".xml\"");
		try {
			FileWriter writer = new FileWriter(new File(path + name + ".xml"));
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<!DOCTYPE document SYSTEM \"Format_save.dtd\">\n");
			writer.write("<texte>\n");

			for (int i = 0; i < file.length; i++)
				writer.write("	<section start=\"" + file[i].getStart() + "\" end=\"" + file[i].getEnd() + "\">"
						+ "</section>\n");

			writer.write("</texte>\n");
			writer.close();
			System.out.println("Document enregistré");
		} catch (IOException e) {
			System.out.println("Erreur, n'a pas pu enregistrer");
			e.printStackTrace();
		}
	}

}
