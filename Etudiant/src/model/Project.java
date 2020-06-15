package model;

/**
 * Classe relative au projets, et aux méthodes qui y sont lié.
 * 
 * @author Florian BENMAHDJOUB
 * @author Antoine PAUMIER
 * @author Emilie BOURRET
 * @author Lucie MASSON
 */
public class Project {
	/**
	 * Variable stockant les sections presentes dans le fichier charge
	 */
	private Section[] sections;
	/**
	 * Variable stockant le titre du projet
	 */
	private String titre;
	/**
	 * Variable stockant la consigne du projet
	 */
	private String consigne;

	/**
	 * Variable stockant le nombre de fautes
	 */
	private int fautes;

	/*
	 * Variable stockant les mots non valide
	 */
	private String stockFautes;

	/**
	 * Variable stockant le mode de sensibilite a� la casse (sensible ou non)
	 */
	private boolean sensiCasse;

	/**
	 * Variable stockant le mode de l'exercice (Entrainement/Evaluation)
	 */
	private boolean modeEval;

	/**
	 * Constructeur utilise pour instancier un projet.
	 * 
	 * @param Sections presentes dans le fichier charge
	 * @param Titre    du projet
	 * @param Consigne du projet
	 * @param Mode     du projet (Entrainement/Evaluation)
	 */
	public Project(Section[] sections, String titre, String consigne, boolean modeEval, boolean sensiCasse) {
		this.sections = sections;
		this.titre = titre;
		this.consigne = consigne;
		this.modeEval = modeEval;

		stockFautes = "";
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

	public int getFautes() {
		return fautes;
	}

	public void setFautes(int fautes) {
		this.fautes = fautes;
	}

	public String getStockFautes() {
		return stockFautes;
	}

	public void setStockFautes(String faute) {
		this.stockFautes += "	-" + faute + "\n";
	}

	public boolean isSensiCasse() {
		return sensiCasse;
	}

	public boolean isModeEval() {
		return modeEval;
	}

}