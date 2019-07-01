package com.sumitchauhan;

public class FileDetails {

	private  String filename;
	private  String category;
	private  String sourcefilename;

	public FileDetails(String filename, String category, String sourcefilename) {
		this.filename = filename;
		this.category = category;
		this.sourcefilename = sourcefilename;
	}
	
	public String toString() {
		return this.filename+" "+this.category+" "+this.sourcefilename; }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSourcefilename() {
		return sourcefilename;
	}

	public void setSourcefilename(String sourcefilename) {
		this.sourcefilename = sourcefilename;
	}

	

}
