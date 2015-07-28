package gui;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.Component.Alignment;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Table;
import com.googlecode.lanterna.gui.layout.LinearLayout;
import com.googlecode.lanterna.terminal.TerminalSize;

import data.SimpleWordWithTranslation;

public class WordWindow extends Window{
	
	
	Window self;
	Button btnNext;
	Button btnPrev;
	
    public WordWindow(SimpleWordWithTranslation word){
        super(word.getWord().trim().toUpperCase());
        self = this;     
        buildLayout(word);
    }

    
    public WordWindow(SimpleWordWithTranslation word, String flag){
        super(word.getWord().trim().toUpperCase());
        self = this;     
    }


	private void buildLayout(SimpleWordWithTranslation word) {
		Table table = new Table(2);
        table.setColumnPaddingSize(5);        
        btnNext = buildNextButton();        
        btnPrev = buildPrevButton();
        Component[] buttonsArray = new Component[2];
        buttonsArray[0] = btnPrev;
        buttonsArray[1] = btnNext;
        table.addRow(buttonsArray);        
        Panel translationPanel = buildTranslation(word);
        addComponent(translationPanel);        
        addComponent(table,  LinearLayout.GROWS_VERTICALLY);
        addComponent(buildButtonClose(), LinearLayout.GROWS_VERTICALLY);
        setFocus(btnNext);
	}    
    
	public void setPrevButtonFocused(){
		setFocus(btnPrev);
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
     * кнопка NEXT
     * */
    private Button buildNextButton(){
    	Action action = new Action() {
			public void doAction() {
				MainView.mode = "LOOK";
				self.close();
			}
		};
    	Button btn = new Button("NEXT", action);
    	return btn;
    }
   
    /**
     * кнопка NEXT
     * */
    private Button buildPrevButton(){
    	Action action = new Action() {
			public void doAction() {
				MainView.mode = "BACK";
				self.close();
			}
		};
    	Button btn = new Button("BACK", action);
    	return btn;
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
    


}
