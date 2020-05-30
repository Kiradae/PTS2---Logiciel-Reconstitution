package model;

/**
 * Classe relative au sections, et aux méthodes qui y sont lié.
 * 
 * @author Florian BENMAHDJOUB
 * @author Antoine PAUMIER
 * @author Emilie BOURRET
 * @author Lucie MASSON
 */
public class Section {
	
	/**
	 * Variable stockant la valeur du début de la section
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
	
	/**
	 * Variable stockant l'aide disponible dans la section
	 */
	private String help;

	/**
	 * Constructeur permétant de créer une instance d'une section
	 * @param content Hidden 
	 * 
	 * @param Valeur  du début de la section
	 * @param Valeur  de la fin de la section
	 * @param Contenu de la section
	 * @param Aide    disponible pour la section
	 */
	public Section(float start, float end, String content, String contentHidden, String help) {
		this.start = start;
		this.end = end;
		this.content = content;
		this.contentHidden = contentHidden;
		this.help = help;
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

	public String getContentHidden() {
		return contentHidden;
	}

	public void setContentHidden(String contentHidden) {
		this.contentHidden = contentHidden;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
