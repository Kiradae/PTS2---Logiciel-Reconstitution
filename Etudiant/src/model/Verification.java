package model;

/**
 * Classe relative au texte, et aux methodes qui y sont lie.
 * 
 * @author Florian BENMAHDJOUB
 * @author Antoine PAUMIER
 * @author Emilie BOURRET
 * @author Lucie MASSON
 */
public class Verification {
	private Section section;
	private String motSaisie;

	/**
	 * Methode permettant de remplacer les caracteres speciaux par le mot trouve
	 * 
	 * @param Le mot recherche
	 * @return Le texte a  trou modifie avec le mot trouve dEcouvert
	 */
	public Verification(Section section, String motSaisie, boolean sensiCasse) {
		this.section = section;
		if (sensiCasse)
			motSaisie = motSaisie.toLowerCase();
		this.motSaisie = motSaisie;
	}

	// vrai si contient l'exacte mot ecrit
	public boolean isSaisieValid() {
		return section.getListeMot().contains(motSaisie.trim());
	}

	public void affichTexte() {
		String newHiddenContent = section.getContentHidden();

		for (int i = 0; i < section.getListeMot().size(); i++)
			if (section.getListeMot().get(i).equals(motSaisie))
				newHiddenContent = newHiddenContent.substring(0, section.getPositionMot().get(i)) + motSaisie
						+ newHiddenContent.substring(section.getPositionMot().get(i) + motSaisie.length());

		section.setContentHidden(newHiddenContent);
	}

}
