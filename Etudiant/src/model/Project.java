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
	 * Variable stockant les sections présentes dans le fichier chargé
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
	 * Variable stockant si le projet est évalué ou non
	 */
	private String mode;
	
	private int fautes;	
	
	/**
	 * Variable stockant le texte à trous
	 */
	String hide;

	/**
	 * Variable stockant le texte original
	 */
	String original;

	/**
	 * Variable stockant texte original modifié pour la sensibilite a la casse
	 */
	String originalCasse;

	/**
	 * Variable stockant le mode de sensibilite à la casse
	 */
	boolean sensiCasse;

	/**
	 * Variable stockant mode de l'exercice
	 */
	boolean evalue;

	/**
	 * Variable stockant l'index du mot cherche
	 */
	int id = 0;

	/**
	 * Constructeur utilisé pour instancier un projet.
	 * 
	 * @param Sections Sections présentes dans le fichier chargé
	 * @param Titre    Titre du projet
	 * @param Consigne Consigne du projet
	 * @param Mode     Mode du projet (Entrainement/Evaluation)
	 */
	public Project(Section[] sections, String titre, String consigne, String mode) {
		super();
		this.sections = sections;
		this.titre = titre;
		this.consigne = consigne;
		this.mode = mode;
	}

	/**
	 * Methode permettant de remplacer les caracteres speciaux par le mot trouve
	 * 
	 * @param Le mot recherché
	 * @return Le texte à trou modifié avec le mot trouvé découvert
	 */
	public String checkReponse(String word) {

		// Dans le cas ou la sensibilité à la casse est activée
		if (sensiCasse) {
			while (id != -1) {
				id = original.indexOf(word, id);
				hide = hide.substring(0, id - 1) + word + hide.substring(id + 1);
			}
		}

		// Dans le cas ou la sensibilite à la casse n'est pas activée
		if (!sensiCasse) {
			originalCasse = original.toLowerCase(); // met le texte original tout en minuscule

			while (id != -1) {
				id = originalCasse.indexOf(word, id);
				hide = hide.substring(0, id - 1) + word + hide.substring(id + 1);
			}
		}

		return hide;
	}
	
	public String getMode() {
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

	public int getFautes() {
		return fautes;
	}

	public void setFautes(int fautes) {
		this.fautes = fautes;
	}

	public boolean isSensiCasse() {
		return sensiCasse;
	}

	public void setSensiCasse(boolean sensiCasse) {
		this.sensiCasse = sensiCasse;
	}

	public boolean isEvalue() {
		return evalue;
	}

	public void setEvalue(boolean evalue) {
		this.evalue = evalue;
	}

}