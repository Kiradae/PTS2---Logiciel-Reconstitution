package model;

public class Section {
	private float start;
	private float end;
	
	private String content;

	public Section(float start, float end, String content) {
		this.start = start;
		this.end = end;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Section : start= " + start + " end= " + end;
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
