package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ExternalFileWriter {

	public ExternalFileWriter() {
	}

	public File chooseWhere() {
		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();

		fileChooser.setTitle("Choisissez une destination");
		fileChooser.setSelectedExtensionFilter(null);
		fileChooser.setInitialFileName("ExoSave.xml");

		return fileChooser.showSaveDialog(stage);
	}

	public void saveExo(Project project) {
		if (project.getFileEmplacement() != null) {
			try {
				Element sauvegarde = new Element("sauvegarde");
				Document doc = new Document(sauvegarde);

				Element titre = new Element("titre").setText(project.getTitre());
				doc.getRootElement().addContent(titre);

				Element consigne = new Element("consigne").setText(project.getConsigne());
				doc.getRootElement().addContent(consigne);

				Element option = new Element("option");
				option.setAttribute(new Attribute("mode", String.valueOf(project.isModeEval())));
				option.setAttribute(new Attribute("sensi", String.valueOf(project.isSensiCasse())));
				doc.getRootElement().addContent(option);

				for (Section Section : project.getSections()) {
					Element section = new Element("section");
					section.setAttribute(new Attribute("start", String.valueOf(Section.getStart())));
					section.setAttribute(new Attribute("end", String.valueOf(Section.getEnd())));
					section.addContent(new Element("help").setText(Section.getHelp()));
					section.addContent(new Element("content").setText(Section.getContent()));
					section.addContent(new Element("contentHidden").setText(Section.hiddContent(Section.getContent())));

					doc.getRootElement().addContent(section);
				}

				// Write console for Debug
				new XMLOutputter().output(doc, System.out);

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter(project.getFileEmplacement()));

				System.out.println("File Saved!");
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

}
