package data;

import java.util.ArrayList;

public class SimpleWordWithTranslation {

	private String word;
	private ArrayList<String> translation;
	
	
	public SimpleWordWithTranslation(String word){
		this.word = word;
		this.translation = new ArrayList<String>();
	}
	
	
	public String getWord() {
		return word;
	}
	
	
	public void setWord(String word) {
		this.word = word;
	}
	
	
	public void addTranslation(String t){
		translation.add(t);
	}


	public ArrayList<String> getTranslation() {
		return translation;
	}
	
	
	
}
