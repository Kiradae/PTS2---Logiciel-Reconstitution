package model;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import org.w3c.dom.Node;

public class ExternalFileReader {

	private String path;

	public ExternalFileReader(String path) {
		this.path = path;
	}

	public Document openXml(String path) {
		Document reader = null;
		System.out.println("Fichier \"" + path + "\" en cour de chargement");

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			reader = builder.parse(path);
			reader.getDocumentElement().normalize();

			System.out.println("Chargement reussi");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reader;
	}

	public Project loadTeacherFile() {
		Section[] load_exercice;
		Document reader = openXml(path);

		NodeList array = reader.getElementsByTagName("section");
		load_exercice = new Section[array.getLength()];
		int k = 0;

		String titre = reader.getElementsByTagName("titre").item(0).getTextContent();
		String consigne = reader.getElementsByTagName("consigne").item(0).getTextContent();
		Element option = (Element) reader.getElementsByTagName("option").item(0);
		boolean mode = Boolean.parseBoolean(option.getAttribute("mode"));
		boolean sensiCasse = Boolean.parseBoolean(option.getAttribute("sensi"));

		for (int i = 0; i < array.getLength(); i++) {
			if (array.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element node = (Element) array.item(i);

				String start = "0";
				String end = "0";
				if (mode) {
					start = node.getAttribute("start").replace("\"", "");
					start = start.replace("start", "");
					end = node.getAttribute("end").replace("\"", "");
					end = end.replace("end", "");
				}

				String content = node.getElementsByTagName("content").item(0).getTextContent();
				String help = node.getElementsByTagName("help").item(0).getTextContent();
				String contentHidden = node.getElementsByTagName("contentHidden").item(0).getTextContent();
				load_exercice[k] = new Section(Float.valueOf(start), Float.valueOf(end), help, content, contentHidden);
				k++;
			}
		}

		Project project = new Project(load_exercice, titre, consigne, mode, sensiCasse);

		ExternalFileWriter write = new ExternalFileWriter();
		write.saveExo(new File("./src/Recent_file.xml"), project);

		return project;
	}

	public boolean isEval() {
		Document reader = openXml(path);
		Element option = (Element) reader.getElementsByTagName("option").item(0);
		return Boolean.parseBoolean(option.getAttribute("mode"));
	}

	public boolean isNull() {
		Document reader = openXml(path);
		return reader == null;
	}

	public void loadVideo(MediaView media) {
		Media new_media = null;
		try {
			File video = new File(path);
			String url = video.toURI().toURL().toString();
			System.out.println("Ressource url: " + url);
			new_media = new Media(url);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		if (new_media != null) {
			MediaPlayer player = new MediaPlayer(new_media);
			player.play();
			media.setMediaPlayer(player);
		} else
			System.out.println("Echec ressource not found");
	}

}
