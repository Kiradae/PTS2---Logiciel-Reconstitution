package model;

import java.io.File;
import java.util.ArrayList;

/**
 * Classe relative au projets, et aux méthodes qui y sont lié.
 * 
 * @author Florian BENMAHDJOUB
 * @author Antoine PAUMIER
 * @author Emilie BOURRET
 * @author Lucie MASSON
 */
public class Project {
	private File fileEmplacement;
	/**
	 * Variable stockant les sections presentes dans le fichier charge
	 */
	private ArrayList<Section> sections;
	/**
	 * Variable stockant le titre du projet
	 */
	private String titre;
	/**
	 * Variable stockant la consigne du projet
	 */
	private String consigne;

	/**
	 * Variable stockant le mode de sensibilite a� la casse (sensible ou non)
	 */
	private boolean sensiCasse;

	/**
	 * Variable stockant le mode de l'exercice (Entrainement/Evaluation)
	 */
	private boolean modeEval;

	public Project(String titre) {
		this.titre = titre;
		sensiCasse = false;
		modeEval = false;
		this.sections = new ArrayList<>();
	}

	/**
	 * Constructeur utilise pour instancier un projet.
	 * 
	 * @param Sections presentes dans le fichier charge
	 * @param Titre    du projet
	 * @param Consigne du projet
	 * @param Mode     du projet (Entrainement/Evaluation)
	 */
	public Project(ArrayList<Section> sections, String titre, String consigne, boolean modeEval, boolean sensiCasse) {
		this.sections = sections;
		this.titre = titre;
		this.consigne = consigne;
		this.modeEval = modeEval;
		this.sensiCasse = sensiCasse;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	public ArrayList<Section> getSections() {
		return sections;
	}

	public String getTitre() {
		return titre;
	}

	public boolean isSensiCasse() {
		return sensiCasse;
	}

	public void setSensiCasse(boolean sensiCasse) {
		this.sensiCasse = sensiCasse;
	}

	public boolean isModeEval() {
		return modeEval;
	}

	public void setModeEval(boolean modeEval) {
		this.modeEval = modeEval;
	}

	public File getFileEmplacement() {
		return fileEmplacement;
	}

	public void setFileEmplacement(File fileEmplacement) {
		this.fileEmplacement = fileEmplacement;
	}

}