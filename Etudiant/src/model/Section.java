package model;

import java.util.ArrayList;

/**
 * Classe relative au sections, et aux methodes qui y sont lie.
 * 
 * @author Florian BENMAHDJOUB
 * @author Antoine PAUMIER
 * @author Emilie BOURRET
 * @author Lucie MASSON
 */
public class Section {
	private final static String charPossible = " .,;:!?";

	/**
	 * Variable stockant la valeur du debut de la section
	 */
	private float start;

	/**
	 * Variable stockant la valeur de la fin de la section
	 */
	private float end;

	/**
	 * Variable stockant le contenu de la section
	 */
	private String content;

	/**
	 * Variable stockant le contenu de la section
	 */
	private String contentHidden;

	private ArrayList<String> listeMot;

	private ArrayList<Integer> positionMot;

	/**
	 * Variable stockant l'aide disponible dans la section
	 */
	private String help;

	/**
	 * Constructeur permetant de creer une instance d'une section
	 * 
	 * @param content Hidden
	 * 
	 * @param Valeur  du debut de la section
	 * @param Valeur  de la fin de la section
	 * @param Contenu de la section
	 * @param Aide    disponible pour la section
	 */
	public Section(float start, float end, String help, String content, String contentHidden) {
		this.start = start;
		this.end = end;
		this.help = help;
		this.setContent(content);
		this.setContentHidden(contentHidden);
		this.setListeMot();

		System.out.println(listeMot);
		System.out.println(content + "\n");
	}

	@Override
	public String toString() {
		return "Section : start= " + start + " end= " + end + "	" + content;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public float getStart() {
		return start;
	}

	public void setStart(float start) {
		this.start = start;
	}

	public float getEnd() {
		return end;
	}

	public void setEnd(float end) {
		this.end = end;
	}

	public void setContentHidden(String contentHidden) {
		this.contentHidden = contentHidden;
	}

	public String getContentHidden() {
		return contentHidden;
	}

	public ArrayList<String> getListeMot() {
		return listeMot;
	}

	public void setListeMot() {
		listeMot = new ArrayList<>();
		positionMot = new ArrayList<>();

		String mot = "";
		for (int i = 0; i < content.length(); i++) {
			if (charPossible.contains(String.valueOf(content.charAt(i)))) {
				if (!(mot.equals("") /* || array.contains(mot) */) || (content.length() - 1) == i) {
					listeMot.add(mot);
					positionMot.add(i - mot.length());
				}
				mot = "";
			} else
				mot += content.charAt(i);
		}
	}

	public ArrayList<Integer> getPositionMot() {
		return positionMot;
	}

	public void setContent(String content) {
		boolean verifPresence = false;
		for (int i = 0; i < charPossible.length(); i++)
			if (content.trim().endsWith("" + charPossible.charAt(i)))
				verifPresence = true;

		if (!verifPresence)
			content = content.trim() + ".";

		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
