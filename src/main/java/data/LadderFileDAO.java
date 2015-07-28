package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class LadderFileDAO {

	
	private ArrayList<SimpleWordWithTranslation> words;
	
	
	public LadderFileDAO(){
		if(!loadFile()){
			try {
				createFile();
			} catch (Exception e) {}
		}
	}
	
	
	private boolean loadFile(){
		File file = new File("wordstolearn.txt");
		if(!file.exists()){
			return false;
		}
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)) {
			words = parseLadderFile(reader);
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return true;
	}
	
	
	private void createFile() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("wordstolearn.txt", "UTF-8");
		writer.close();
		System.out.println("file created! Fill it with words now, mortal!");
	}
	

	private ArrayList<SimpleWordWithTranslation> parseLadderFile(BufferedReader reader) throws IOException{
		ArrayList<SimpleWordWithTranslation> words = new ArrayList<SimpleWordWithTranslation>();		
	    String line = null;
	    SimpleWordWithTranslation currentWord = null;
	    while ((line = reader.readLine()) != null) {
	    	if(line.length() == 0) continue;
	    	if(Character.codePointAt(line, 0) != 9){
	    		if(currentWord != null){
	    			words.add(currentWord);
	    		}	    		
	    		currentWord = new SimpleWordWithTranslation(line);
	    	} else {
	    		currentWord.addTranslation(line);
	    	}
	    }		
	    System.out.println("loaded " + words.size() + " words");
		return words;
	}


	public ArrayList<SimpleWordWithTranslation> getWords() {
		return words;
	}
	
}
