package model;

public class Section {
	private float start;
	private float end;

	private String content;
	private String help;

	public Section(float start, float end, String content, String help) {
		this.start = start;
		this.end = end;
		this.content = content;
		this.help = help;
	}

	@Override
	public String toString() {
		return "Section : start= " + start + " end= " + end + "	" + content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

}
