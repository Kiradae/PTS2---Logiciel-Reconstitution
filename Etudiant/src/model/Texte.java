package model;

public class Texte {

	private static String titreTexte;

	private static String consigneTexte = "test";

	private static String contenuTexte;

	String hide;// texte a trous
	
	String original;// texte original
	
	String originalCasse;// texte original modifie pour la sensibilite a la casse
	
	static boolean sensiCasse; // sensibilite a la casse
	
	static boolean evalue; // mode evaluation ou non

	int id = 0;// id est l index du mot cherche

	// methode permettant de remplacer les caracteres speciaux par le mot trouve
	public String checkReponse(String word) {

		// dans le cas ou la sensibilite a la casse est activee
		if (sensiCasse) {
			while (id != -1) {
				id = original.indexOf(word, id);
				hide = hide.substring(0, id - 1) + word + hide.substring(id + 1);
			}
		}

		// dans le cas ou la sensibilite a la casse n est pas activee
		if (!sensiCasse) {
			originalCasse = original.toLowerCase();// met le texte original tout en minuscule

			while (id != -1) {
				id = originalCasse.indexOf(word, id);
				hide = hide.substring(0, id - 1) + word + hide.substring(id + 1);
			}
		}

		return hide;
	}
	
	public static boolean isSensiCasse() {
		return sensiCasse;
	}

	public static void setSensiCasse(boolean sensiCasse) {
		Texte.sensiCasse = sensiCasse;
	}
	
	public static boolean isEvalue() {
		return evalue;
	}

	public static void setEvalue(boolean evalue) {
		Texte.evalue = evalue;
	}

	public static String getConsigneTexte() {
		return consigneTexte;
	}
}
