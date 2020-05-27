package model;

import java.io.File;

public class Project {
	private Section[] sections;
	private String titre;
	private String consigne;
	private boolean mode; // evaluer ou non

	public Project(Section[] sections, String titre, String consigne, String mode) {
		super();
		this.sections = sections;
		this.titre = titre;
		this.consigne = consigne;
		if (mode == "Evaluer") {
			this.mode = false;
		} else
			this.mode = true;
	}

	public boolean isMode() {
		return mode;
	}

	public String getConsigne() {
		return consigne;
	}

	public Section[] getSections() {
		return sections;
	}

	public String getTitre() {
		return titre;
	}

}
