package model;

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
	private double start;

	/**
	 * Variable stockant la valeur de la fin de la section
	 */
	private double end;

	/**
	 * Variable stockant le contenu de la section
	 */
	private String content;

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
	public Section(double start, double end, String help, String content) {
		this.start = start;
		this.end = end;
		this.help = help;
		this.setContent(content);

		System.out.println(content + "\n");
	}

	public Section(double d, double e) {
		this(d, e, "", "");
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

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public String hiddContent(String content) {
		String replace = "";
		for (int i = 0; i < this.getContent().length(); i++)
			if (containChar(content.charAt(i)))
				replace += content.charAt(i);
			else
				replace += "*";

		return replace;
	}

	private boolean containChar(char c) {
		for (int i = 0; i < charPossible.length(); i++)
			if (charPossible.charAt(i) == c)
				return true;

		return false;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
