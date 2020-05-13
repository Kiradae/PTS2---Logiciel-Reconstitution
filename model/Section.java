package model;

public class Section {
	private String content;
	private float start;
	private float end;
	
	public Section(String content, float start, float end) {
		this.content = content;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "Section : start= " + start + " end= " + end + "\n" + content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
