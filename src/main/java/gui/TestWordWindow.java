package gui;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.Component.Alignment;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.gui.layout.LinearLayout;
import com.googlecode.lanterna.terminal.TerminalSize;

import data.SimpleWordWithTranslation;

public class TestWordWindow extends Window{

	TestWordWindow self;
	Panel txtInput;
	SimpleWordWithTranslation word;

	
    public TestWordWindow(SimpleWordWithTranslation word){
    	super(replaceAllLettersWithStars(word.getWord().trim().toUpperCase()));
        self = this;     
        this.word = word;
        buildLayout(word);
    }
    
    
	private void buildLayout(SimpleWordWithTranslation word) {
		txtInput = buildInputPanel();
        Panel translationPanel = buildTranslation(word);
        addComponent(translationPanel);        
        addComponent(txtInput,  LinearLayout.GROWS_HORIZONTALLY);
        addComponent(buildButtonClose(), LinearLayout.GROWS_VERTICALLY);
	}


	private Panel buildTranslation(SimpleWordWithTranslation word){
        Panel pnlWord = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
        
        for(String i : word.getTranslation()){
        	Label lbl = new Label(i + "    ");
        	lbl.setAlignment(Alignment.LEFT_CENTER);
        	pnlWord.addComponent(lbl, LinearLayout.GROWS_HORIZONTALLY);
        }
                
        pnlWord.setPreferredSize(new TerminalSize(1000,20));  
        return pnlWord;
	}
	
	
    /**
     * кнопка CLOSE
     * */
    private Button buildButtonClose(){
    	Action action = new Action() {
			public void doAction() {
				MainView.mode = "MENU";
				self.close();
			}
		};
    	Button btn = new Button("EXIT TO MAIN MENU", action);
    	return btn;
    }
    
    
    private Panel buildInputPanel(){
    	Panel inputPanel = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
    	Label lbl = new Label("INPUT:");
    	final TextBox box = new TextBox();
    	box.addBorder(new Border.Standard(), "поле ввода");
    	Action action = new Action() {
			public void doAction() {
				onInput(box.getText());
			}
		};
		setFocus(box);
    	Button btn = new Button("ENTER",action);
    	action = new Action(){
			public void doAction() {
				onGiveUp();				
			}    		
    	};
    	Button btnGiveUp = new Button("GIVE UP",action);
    	inputPanel.addComponent(lbl);
    	inputPanel.addComponent(box,LinearLayout.GROWS_HORIZONTALLY);
    	inputPanel.addComponent(btn);    	
    	inputPanel.addComponent(btnGiveUp);    	
    	inputPanel.setPreferredSize(new TerminalSize(1000,2));
    	return inputPanel;
    }
    
    
    private void onInput(String input){
    	if(input.trim().toUpperCase().equals(word.getWord().trim().toUpperCase())){
    		MessageBox.showMessageBox(getOwner(), "CORRECT", "This is correct!");
			MainView.mode = "TEST";
			self.close();
    	}    	
    }

    
    private static String replaceAllLettersWithStars(String str){
    	String result = "";
    	for(@SuppressWarnings("unused") char i: str.toCharArray()){
    		result += "*";
    	}
    	return result;
    }
    
    
    private void onGiveUp(){
		MessageBox.showMessageBox(getOwner(), "TOO BAD!", "The word was: " + word.getWord().trim().toUpperCase());
		MainView.mode = "TEST";
		self.close();
    }
	
}
