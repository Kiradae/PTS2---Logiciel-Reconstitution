package model;

public class Main {
	public final static String ABSOLUTE_PATH = System.getProperty("user.dir") + "\\src\\model\\";

	public static void main(String[] args) {
		ExternalFile loader = new ExternalFile();
		Section[] file = loader.loadTeacherFile(ABSOLUTE_PATH + "Texte_save.xml");
		for (int i=0; i<file.length; i++)
			System.out.println(file[i]);
		
		file[1].setContent("randooooooooooooooooooooomestcequecamarche?onverraapres");
		loader.saveTeacherFile(ABSOLUTE_PATH, "essai", file);
	}

}
