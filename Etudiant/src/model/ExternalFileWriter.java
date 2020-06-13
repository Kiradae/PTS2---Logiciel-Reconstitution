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

	public void saveExo(Project project) {

		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();
		
		fileChooser.setTitle("Choisissez une destination");
		fileChooser.setSelectedExtensionFilter(null);
		fileChooser.setInitialFileName("ExoSave.xml");
		
		File file = fileChooser.showSaveDialog(stage);
		
		if (file != null) {
			try {
				Element sauvegarde = new Element("sauvegarde");
				Document doc = new Document(sauvegarde);
				
				Element titre = new Element("titre").setText(project.getTitre());
				doc.getRootElement().addContent(titre);
				
				Element consigne = new Element("consigne").setText(project.getConsigne());
				doc.getRootElement().addContent(consigne);
				
				Element mode = new Element("mode").setText(project.getMode());
				doc.getRootElement().addContent(mode);
				
				Element document = new Element("document").setText(null);
				doc.getRootElement().addContent(document);
				
				for (Section Section : project.getSections()) {
					Element section = new Element("section");
					section.setAttribute(new Attribute("start", String.valueOf(Section.getStart())));
					section.setAttribute(new Attribute("end", String.valueOf(Section.getEnd())));
					section.addContent(new Element("help").setText(Section.getHelp()));
					section.addContent(new Element("content").setText(Section.getContent()));
					section.addContent(new Element("contentHidden").setText(Section.getContentHidden()));

					doc.getRootElement().addContent(section);
				}

				// Write console for Debug
				new XMLOutputter().output(doc, System.out);

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter(file));

				System.out.println("File Saved!");
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
