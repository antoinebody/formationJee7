package fr.dawan.project.ejb.model;

public class ResultatRecherche {

	private int line;
	private String fileName;
	private String lineContent;

	public ResultatRecherche(int line, String fileName, String lineContent) {
		super();
		this.line = line;
		this.fileName = fileName;
		this.lineContent = lineContent;
	}

	public ResultatRecherche() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLineContent() {	
		return lineContent;
	}

	public void setLineContent(String lineContent) {
		this.lineContent = lineContent;
	}

	@Override
	public String toString() {
		return "ResultatRecherche [line=" + line + ", fileName=" + fileName + ", lineContent=" + lineContent + "]";
	}

}
